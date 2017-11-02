(ns usps.dev
  (:require
    [clojure.data.xml :as xml]
    [clojure.edn :as edn]
    [clojure.java.io :as io]
    [clojure.pprint :refer [pprint]]
    [clojure.string :as string]
    [clojure.tools.namespace.repl :as repl]
    [clojure.walk :refer [macroexpand-all]]
    [usps.api.address]
    [usps.models.address :as address]
    [usps.models.util :as models-util]
    [usps.util :as util]))

;;; Aliases

(def reload #'repl/refresh)
(def reset #'repl/refresh)
