(ns usps.models.core
  (:require
    [clojure.data.xml :as xml]
    [clojure.walk :as walk]
    [usps.models.address :as address]
    [usps.models.address-validation :as address-validation]
    [usps.models.error :as error]
    [usps.models.util :as util])
  (:import
    (usps.models.address AddressConverter)
    (usps.models.address_validation ValidationRequestConverter)
    (usps.models.error ErrorConverter)))

(defprotocol ConverterAPI
  (apply-constraints [this record-map])
  (element->field [this element-obj])
  (elements->fields [this])
  (extract [this])
  (field->element [this kv-pair])
  (fields->elements [this])
  (key-order [this])
  (post-parse [this element-obj])
  (pre-parse [this data-map]))

(extend AddressConverter
        ConverterAPI
        address/behaviour)

(extend ErrorConverter
        ConverterAPI
        error/behaviour)

(extend ValidationRequestConverter
        ConverterAPI
        address-validation/behaviour)

(defn key-order
  [converter]
  (vals (fields->elements converter)))

(defn record->xml-phase-1
  [converter]
  (->> (extract converter)
       (apply-constraints converter)
       (walk/postwalk-replace (fields->elements converter))))

(defn record->xml-phase-2
  [converter data]
  (->> converter
       key-order
       (map #(when (contains? data %) [% (% data)]))
       (remove nil?)))

(defn record->xml-phase-3
  [converter data]
  (let [tag (:wrapping-tag converter)
        attrs (or (:wrapping-attrs converter) {})]
    (->> data
         (pre-parse converter)
         (apply xml/sexps-as-fragment)
         (xml/element tag attrs)
         (post-parse converter))))

(defn record->xml
  "Note that this is split up into phases due to different data being
  passed through each major phase.

  The code was changed from a staight-forward thrushing to this once we
  realized that the USPS API requires XML elements to be in a fixed order (!?).
  Phase 2 was introduced for this, but since the data that is thrushes isn't
  the same as that passed through the others, it had to be split out."
  [converter]
  (->> (record->xml-phase-1 converter)
       (record->xml-phase-2 converter)
       (record->xml-phase-3 converter)))

(defn xml->record
  [converter]
  (->> converter
       :element-obj
       :content
       (map (partial element->field converter))
       (into {})
       (walk/postwalk-replace (elements->fields converter))
       (:constructor converter)))

(defn parse
  [str-data]
  (let [element-obj (xml/parse-str str-data)]
    (case (:tag element-obj)
      :Error (-> element-obj
                 error/create
                 xml->record)
      ; :AddressValidateResponse (-> element-obj
      ;                              address-validation/create
      ;                              xml->record)
      element-obj)))
