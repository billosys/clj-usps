(ns usps.models.address-validation
  (:require
    [usps.models.base :as base]
    [usps.models.util :as util]))

(defrecord ValidationRequest
  [user-id
   payload
   revision])

(def fields->elements
  {:revision :Revision})

(defn apply-constraints
  "Remove option items that are empty, shouldn't be in the XML, etc."
  [this record-map]
  (dissoc record-map :user-id :payload))

(defn post-parse
  [this element-obj]
  (assoc element-obj :attrs {:USERID (get-in this [:record :user-id])}
                     :content (get-in this [:record :payload])))

(defrecord ValidationRequestConverter
  [record
   wrapping-tag])

(def behaviour
  (merge base/behaviour
         {:fields->elements (fn [_] fields->elements)
          :apply-constraints apply-constraints
          :post-parse post-parse}))

(defn create
  ([user-id payload]
    (create user-id 1 payload))
  ([user-id revision payload]
    (map->ValidationRequestConverter
      {:record (->ValidationRequest user-id payload revision)
       :wrapping-tag :AddressValidateRequest})))
