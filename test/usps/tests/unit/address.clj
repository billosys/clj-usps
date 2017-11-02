(ns ^:unit usps.tests.unit.address
  (:require
    [clojure.data.xml :as xml]
    [clojure.test :refer :all]
    [usps.address :as address]))

(def addr1 (address/map->Address
            {:address ""
             :city ""
             :state ""}))

(def addr1-xml (str
"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Address>
  <FirmName/>
  <Address2/>
  <Address1/>
  <City/>
  <State/>
  <Urbanization/>
  <Zip5/>
  <Zip4/>
</Address>
"))

(deftest record->xml
  (is (= addr1-xml
         (xml/indent-str (address/record->xml addr1)))))
