(ns usps.models.base
  (:require
    [clojure.data.xml :as xml]))

(defrecord Converter
  [record
   element-obj
   wrapping-tag
   wrapping-attrs])

(defn extract
  [this]
  (into {} (:record this)))

(defn apply-constraints
  [this record-map]
  record-map)

(defn fields-map
  [this]
  {})

(defn field->element
  [this [k v]]
  (xml/element k {} v))

(defn element->field
  [this element-obj]
  [(:tag element-obj) (first (:content element-obj))])

(defn pre-parse
  [this data-map]
  data-map)

(defn post-parse
  [this element-obj]
  element-obj)

(def behaviour
  {:extract extract
   :apply-constraints apply-constraints
   :fields-map fields-map
   :field->element field->element
   :element->field element->field
   :pre-parse pre-parse
   :post-parse post-parse})
