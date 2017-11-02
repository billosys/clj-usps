(ns usps.util
  (:require
    [clojure.data.xml :as xml]))

(defn pretty-xml
  [element-obj]
  (xml/indent-str element-obj))

(defn pprint-xml
  [element-obj]
  (print (pretty-xml element-obj))
  :ok)
