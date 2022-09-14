(ns renoise-rc.core
  (:import (com.illposed.osc.transport OSCPortOut)
           (com.illposed.osc OSCMessage)
           (java.net InetAddress)
        ))

(defn connect-renoise
  ([] (connect-renoise 8000))
  ([port] (connect-renoise (InetAddress/getLocalHost) port))
  ([ address port]
   (OSCPortOut. ^InetAddress address (int port))))

(defn convert-arg [x]
  (cond
    (integer? x) (int x)
    :else x))

(defn send
  "Send a message to renoise.
  `url` see https://tutorials.renoise.com/wiki/Open_Sound_Control for the list
  `args` the arguments for the url"
  [renoise url & args]
  (.send renoise (OSCMessage. url (map convert-arg args))))


(comment
 (def r (connect-renoise))
 (send r "/renoise/transport/stop")
 (send r "/renoise/song/bpm" 104)
 (send r "/renoise/transport/start"))