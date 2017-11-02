(ns usps.models.address
  (:require
    [clj-http.client :as http]
    [clojure.data.xml :as xml]
    [clojure.string :as string]
    [clojure.walk :as walk]
    [usps.models.util :as util]))

(defrecord Address
  [firm-name
   address
   address-other
   city
   state
   urbanization
   zip
   zip4])

(def fields->elements
  {:firm-name :FirmName
   :address :Address2
   :address-other :Address1
   :city :City
   :state :State
   :urbanization :Urbanization
   :zip :Zip5
   :zip4 :Zip4})

(defn apply-address-constraints
  "Remove option items that are empty, etc."
  [address-map]
  address-map)

(defn record->xml
  [record]
  (util/record->xml
    record apply-address-constraints fields->elements :Address))

