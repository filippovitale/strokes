(ns blade
  (:require [mrhyde.typepatcher
               :refer [patch-known-vector-types
                       patch-known-sequential-types 
                       patch-known-mappish-types]]
            [mrhyde.funpatcher
               :refer [patch-js-with-key-lookup
                       patch-args-seq-to-array
                       patch-args-clj-to-js]]
            [clojure.string :refer [join]]
            [cljs.reader :refer [read-string]]))

(def L (this-as ct (aget ct "L")))

(defn bootstrap [& opts]
  ; patch all seqs to also be read-only arrays for javascript interop
  (patch-known-vector-types)
  ; (patch-known-sequential-types)
  ; patch maps to include key based accessors on js object
  (patch-known-mappish-types)
  
  ; experimental - all objects can lookup by key (allows obj destructuring, etc)
  ; (patch-js-with-key-lookup)

  ; (if L (do
    ; filter L.latLng inputs: need to force single arg seqs to array
    ; (called indirectly many places, including map.setView)
    ; (patch-args-seq-to-array L "latLng" 0)
    ; (patch-args-clj-to-js (-> L .-control) "layers" 0 1)  
    ; (patch-args-clj-to-js L "setOptions" 1)  
  ; ))
)
