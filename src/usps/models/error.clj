(ns usps.models.error
  (:require
    [clojure.data.xml :as xml]
    [usps.models.base :as base]
    [usps.models.util :as util]))

(defrecord ServiceError
  [number
   source
   description
   help-file
   help-context])

(def fields->elements
  {:number :Number
   :source :Source
   :description :Description
   :help-file :HelpFile
   :help-context :HelpContext})

(def elements->fields (util/elements->fields fields->elements))

(defrecord ErrorConverter
  [element-obj
   constructor])

(def behaviour
  (merge base/behaviour
         {:fields->elements (fn [_] fields->elements)
          :elements->fields (fn [_] elements->fields)}))

(defn create
  [element-obj]
  (->ErrorConverter
    element-obj
    map->ServiceError))
