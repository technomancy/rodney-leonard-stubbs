(ns rodney.leonard.stubbs
  "Replace all vars in all namespaces  with their :stub metadata."
  (:use [robert.hooke :only [add-hook]]
        [leiningen.compile :only [eval-in-project]]))

(def stubbery
  `(fn [f# & args#]
     (doseq [n# (all-ns)
             [_# v#] (ns-publics n#)
             :when (:stub (meta v#))]
       (alter-var-root v# (fn [f# v#] (with-meta (:stub (meta v#))
                                       (assoc (dissoc (meta v#) :stub)
                                         :stubbs/original f#))) v#))
     (apply f# args#)))

(defn add-stub-form [eval-in-project project form & [handler]]
  (let [form `(do (require '~'clojure.test)
                  (require '~'robert.hooke)
                  (#'robert.hooke/add-hook #'~'clojure.test/run-tests
                                           ~stubbery)
                  ~form)]
    (eval-in-project project form handler)))

(add-hook #'eval-in-project add-stub-form)
