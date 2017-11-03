(ns usps.dev
  (:require
    [clojure.data.xml :as xml]
    [clojure.edn :as edn]
    [clojure.java.io :as io]
    [clojure.pprint :refer [pprint]]
    [clojure.string :as string]
    [clojure.tools.namespace.repl :as repl]
    [clojure.walk :refer [macroexpand-all postwalk-replace]]
    [usps.api.address]
    [usps.models.address :as address-model]
    [usps.models.address-response :as address-response-model]
    [usps.models.core :as models]
    [usps.models.error :as error-model]
    [usps.models.util :as models-util]
    [usps.util :as util]))

;;; Aliases

(def reload #'repl/refresh)
(def reset #'repl/refresh)
