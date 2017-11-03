(ns usps.models.address-response
  (:require
    [clojure.data.xml :as xml]
    [usps.models.base :as base]
    [usps.models.util :as util]))

(defrecord ResponseAddress
  [firm-name
   street-other
   street
   street-abbr
   city
   city-abbr
   state
   urbanization
   zip
   zip4
   delivery-point
   carrier-route
   footnotes
   dpv-confirmation
   dpv-cmra
   dpv-false
   dpv-footnotes
   business
   central-delivery-point
   vacant
   return-text])

(def fields->elements
  {:firm-name :FirmName
   :street-other :Address1
   :street :Address2
   :street-abbr :Address2Abbreviation
   :city :City
   :city-abbr :CityAbbreviation
   :state :State
   :urbanization :Urbanization
   :zip :Zip5
   :zip4 :Zip4
   :delivery-point :DeliveryPoint
   :carrier-route :CarrierRoute
   :footnotes :Footnotes
   :dpv-confirmation :DPVConfirmation
   :dpv-cmra :DPVCMRA
   :dpv-false :DPVFalse
   :dpv-footnotes :DPVFootnotes
   :business :Business
   :central-delivery-point :CentralDeliveryPoint
   :vacant :Vacant
   :return-text :ReturnText})

(def elements->fields (util/elements->fields fields->elements))

(defrecord AddressResponseConverter
  [element-obj
   constructor])

(def behaviour
  (merge base/behaviour
         {:fields->elements (fn [_] fields->elements)
          :elements->fields (fn [_] elements->fields)}))

(defn create
  [element-obj]
  (->AddressResponseConverter
    element-obj
    map->ResponseAddress))
