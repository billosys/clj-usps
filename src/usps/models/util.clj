(ns usps.models.util
  (:require
    [clojure.data.xml :as xml]
    [clojure.walk :as walk]))

(defn field->element
  [[k v]]
  (xml/element k {} v))

(defn record->xml
  ([record constraints-fn fields-map wrapping-tag]
    (record->xml record constraints-fn fields-map wrapping-tag {}))
  ([record constraints-fn fields-map wrapping-tag wrapping-attrs]
    (->> record
         (into {})
         constraints-fn
         (walk/postwalk-replace fields-map)
         (map field->element)
         (xml/element wrapping-tag wrapping-attrs))))
