(ns usps.models.util
  (:require
    [clojure.set :as set]))

(defn elements->fields
  [fields-map]
  (set/map-invert fields-map))
