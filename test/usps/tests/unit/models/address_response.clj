(ns ^:unit usps.tests.unit.models.address-response
  (:require
    [clojure.data.xml :as xml]
    [clojure.test :refer :all]
    [usps.models.address-response :as address-response]
    [usps.models.core :as models]
    [usps.util :as util])
  (:import
    (usps.models.address_response ResponseAddress)))

(def resp1-xml-str (str
"<AddressValidateResponse>
  <Address ID=\"0\">
    <Address2>29851 AVENTURA STE K</Address2>
    <City>RANCHO SANTA MARGARITA</City>
    <CityAbbreviation>RCHO STA MARG</CityAbbreviation>
    <State>CA</State>
    <Zip5>92688</Zip5>
    <Zip4>2014</Zip4>
    <DeliveryPoint>83</DeliveryPoint>
    <CarrierRoute>C###</CarrierRoute>
    <Footnotes>N</Footnotes>
    <DPVConfirmation>Y</DPVConfirmation>
    <DPVCMRA>N</DPVCMRA>
    <DPVFootnotes>AABB</DPVFootnotes>
    <Business>Y</Business>
    <CentralDeliveryPoint>N</CentralDeliveryPoint>
    <Vacant>N</Vacant>
  </Address>
</AddressValidateResponse>"))

(def resp1-xml
  (-> resp1-xml-str
      (xml/parse-str)
      :content
      first))

(deftest xml->record
  (let [result (models/xml->record (address-response/create resp1-xml))]
    (is (= ResponseAddress (type result)))
    (is (= "29851 AVENTURA STE K" (:street result)))
    (is (= "RCHO STA MARG" (:city-abbr result)))
    (is (= "C###" (:carrier-route result)))))
