(defproject systems.billo/clj-usps "0.2.0-SNAPSHOT"
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
        [environ "0.4.0"]]}
    :test {
      :plugins [
        [lein-ancient "0.6.14"]
        [jonase/eastwood "0.2.5"]
        [lein-bikeshed "0.5.0" :exclusions [org.clojure/tools.namespace]]
        [lein-kibit "0.1.5"]
        [venantius/yagni "0.1.4"]]}})
