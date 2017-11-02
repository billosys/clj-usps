(ns ^:unit usps.tests.unit.models.address
  (:require
    [clojure.test :refer :all]
    [usps.models.address :as address]
    [usps.util :as util]))

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
         (util/pretty-xml (address/record->xml addr1)))))
