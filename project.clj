(defn get-prompt
  [ns]
  (str "\u001B[35m[\u001B[34m"
       ns
       "\u001B[35m]\u001B[33m λ\u001B[m=> "))

(defproject systems.billo/clj-usps "1.0.0"
  :description "Clojure interface to the USPS Web Tools API"
  :url "https://github.com/billosys/clj-usps"
  :license {
    :name "Eclipse Public License"
    :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [clj-http "3.7.0"]
    [clojusc/env-ini "0.4.1"]
    [org.clojure/clojure "1.8.0"]
    [org.clojure/data.xml "0.0.8"]]
  :profiles {
    :ubercompile {:aot :all}
    :custom-repl {
      :repl-options {
        :init-ns usps.dev
        :prompt ~get-prompt}}
    :dev {
      :source-paths ["dev-resources/src"]
      :dependencies [
        [environ "1.1.0"]
        [org.clojure/tools.namespace "0.2.11"]]}
    :test {
      :plugins [
        [lein-ancient "0.6.14"]
        [jonase/eastwood "0.2.5"]
        [lein-bikeshed "0.5.0" :exclusions [org.clojure/tools.namespace]]
        [lein-kibit "0.1.5"]
        [venantius/yagni "0.1.4"]]
      :test-selectors {
        :default :unit
        :unit :unit
        :system :system
        :integration :integration}}}
  :aliases {
    "repl"
      ["with-profile" "+test,+custom-repl" "repl"]
    "check-deps"
      ^{:doc (str "Check if any deps have out-of-date versions")}
      ["with-profile" "+test" "ancient" "check" ":all"]
    "lint"
      ^{:doc (str "Perform lint checking")}
      ["with-profile" "+test" "kibit"]
    "ubercompile"
      ["with-profile" "+ubercompile" "compile"]
    "build"
      ^{:doc (str "Perform build tasks for CI/CD & releases\n\n"
                 "Additional aliases:")}
      ["with-profile" "+test" "do"
        ["check-deps"]
        ["lint"]
        ["test"]
        ["ubercompile"]
        ["clean"]
        ["uberjar"]]})
