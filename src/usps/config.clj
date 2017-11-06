(ns usps.config
  (:require
    [clojusc.env-ini.core :as env-ini]))

(defn get-config-data
  []
  (env-ini/load-data "~/.usps/client.ini"))

(defn user-id
  ([]
    (user-id (get-config-data)))
  ([config-data]
    "Allow for the user-id to be provided by either the config/INI file
    or by setting the `USPS_USER_ID` environment variable."
    (env-ini/get config-data :usps-user-id :default :user-id)))
