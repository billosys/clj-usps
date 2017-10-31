(defproject systems.billo/clj-usps "0.3.0-SNAPSHOT"
  :description "Clojure interface to the USPS Web Tools API"
  :url "https://github.com/billosys/clj-usps"
  :license {
    :name "Eclipse Public License"
    :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.5.1"]
    [org.clojure/data.xml "0.0.7"]
    [clj-http "0.7.2"]]
  :profiles {
    :dev {
      :dependencies [
        [environ "0.4.0"]]}})
