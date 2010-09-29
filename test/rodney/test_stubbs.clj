(ns rodney.test-stubbs
  (:use [clojure.test]))

(defn ^{:stub (constantly true)} stubbed? []
  false)

(deftest test-stubbed
  (is (stubbed?)))
