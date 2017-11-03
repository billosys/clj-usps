(ns usps.models.address
  (:require
    [usps.models.base :as base]
    [usps.models.util :as util]))

(defrecord Address
  [firm-name
   street
   street-other
   city
   state
   urbanization
   zip
   zip4])

(def fields->elements
  (array-map
   :firm-name :FirmName
   :street-other :Address1
   :street :Address2
   :city :City
   :state :State
   :urbanization :Urbanization
   :zip :Zip5
   :zip4 :Zip4))

(def elements->fields (util/elements->fields fields->elements))

(defn apply-constraints
  "Remove optional items that are empty, add missing, etc."
  [this address-map]
  address-map
  (if (nil? (:urbanization address-map))
    (dissoc address-map :urbanization)
    address-map))

(defrecord AddressConverter
  [record
   element-obj
   wrapping-tag
   wrapping-attrs
   constructor])

(def behaviour
  (merge base/behaviour
         {:fields->elements (fn [_] fields->elements)
          :elements->fields (fn [_] elements->fields)
          :apply-constraints apply-constraints}))

(defn create
  [data]
  (map->AddressConverter
    {:record (map->Address data)
     :wrapping-tag :Address
     :constructor map->Address}))
