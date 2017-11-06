(ns usps.api.address
  (:require
    [clj-http.client :as http]
    [clojure.data.xml :as xml]
    [clojure.pprint :refer [pprint]]
    [clojure.string :as string]
    [usps.config :as config]
    [usps.models.address :as address]
    [usps.models.address-validation :as address-validation]
    [usps.models.core :as models]
    [usps.models.error :as error])
  (:import
    (usps.models.address Address)
    (usps.models.error ServiceError)))

(def endpoint "http://production.shippingapis.com/")

(defn address-request
  [address user-id]
  (->> address
       (address/create)
       (models/record->xml)
       (address-validation/create user-id)
       (models/record->xml)
       (xml/emit-str)
       (str endpoint "?API=Verify&XML=")))

(defmulti handle-response
  type)

(defmethod handle-response ServiceError
  [response]
  (println (format "\nERROR: %s\n" (:description response)))
  (->> response
       (filter second)
       (into {})
       (hash-map :error)))

(defmethod handle-response :default
  [response]
  (->> response
       (filter second)
       (into {})))

(defn validate
  "Validate an address with USPS."
  [address]
  (->> (address-request address (config/user-id))
       (http/get)
       :body
       (models/parse)
       (handle-response)))
