(ns ^:system usps.tests.system.address
  (:require
    [clojure.test :refer :all]
    [environ.core :refer :all]
    [usps.api.address :as address]))

(deftest addresses
  (let [usps-user-id (env :usps-user-id)]

    (testing "verify address"
      (is (= {:street "6406 IVY LN" :city "GREENBELT" :state "MD" :zip "20770"
              :zip_four "1441"}
             (address/validate
               {:street "6406 ivy lane" :city "Greenbelt" :state "MD" :zip ""}
               usps-user-id))))

    (testing "remove bad characters"
      (is (= {:street "6406 IVY LN" :city "GREENBELT" :state "MD" :zip "20770"
              :zip_four "1441"}
             (address/validate
               {:street "6406 IVY & LANE" :city "Greenbelt" :state "MD" :zip ""}
               usps-user-id))))

    (testing "address has an error"
      (is (= nil (address/validate {:street "No Way Out"}
                                   usps-user-id))))

    (testing "address missing zip4"
      (is (= nil (address/validate
                   {:street "963 E 970 N" :state "UT" :city "Orem" :zip "84097"}
                   usps-user-id))))))
