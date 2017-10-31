(ns usps.dev
  (:require
    [clojure.edn :as edn]
    [clojure.java.io :as io]
    [clojure.pprint :refer [pprint]]
    [clojure.string :as string]
    [clojure.tools.namespace.repl :as repl]
    [clojure.walk :refer [macroexpand-all]]
    [trifl.java :refer [show-methods]]
    [usps.address :as address]))

;;; Aliases

(def reload #'repl/refresh)
(def reset #'repl/refresh)
