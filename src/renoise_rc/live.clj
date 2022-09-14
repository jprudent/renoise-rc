(ns renoise-rc.live
  "Namespace dedicated to live production, full of hacks and shortcuts"
  (:require [renoise-rc.core :refer :all]))

(def r (connect-renoise))

(defn play [] (send r "/renoise/transport/start"))
(defn stop [] (send r "/renoise/transport/stop"))
(defn bpm [bpm] (send r "/renoise/song/bpm" bpm))



(comment

 (play)
 (stop)

 (bpm 104)
 (bpm 128)

 (dotimes [x 100]
   (bpm (+ 80 x))
   (Thread/sleep 100)))