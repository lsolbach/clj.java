;;;;
;;;;   Copyright (c) Ludger Solbach. All rights reserved.
;;;;
;;;;   The use and distribution terms for this software are covered by the
;;;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;;;   which can be found in the file license.txt at the root of this distribution.
;;;;   By using this software in any fashion, you are agreeing to be bound by
;;;;   the terms of this license.
;;;;
;;;;   You must not remove this notice, or any other, from this software.
;;;;

(ns org.soulspace.clj.java.net
  "Functions for network access."
  (:require [clojure.java.io :as io])
  (:import [java.security.cert X509Certificate]
           [java.net HttpURLConnection URL]
           [javax.net.ssl SSLContext SSLSession SSLSocketFactory SSLSocket
            TrustManager X509TrustManager]))

;;;;
;;;; Functions for network access
;;;;
(defn url-content
  "Read the content from an URL ."
  ([url]
    (slurp url))
  ([url ^Integer timeout]
    (let [^HttpURLConnection conn (.openConnection (io/as-url url))]
      (.setConnectTimeout conn timeout)
      (.connect conn)
      (slurp (.getInputStream conn)))))

(defn test-url
  "Test an URL, returns true if content is available."
  ([url]
   (test-url url 500))
  ([url ^Integer timeout]
   (try
     (let [^HttpURLConnection conn (.openConnection (io/as-url url))]
       (.setConnectTimeout conn timeout)
       ;(.setReadTimeout conn timeout)
       (.connect conn)
       (< (.getResponseCode conn) 400))
     (catch Exception e 
       false))))

(defn get-certificates
  "Returns the SSL certificates for a given host and port."
  [^String host ^Integer port]
  (let [tm (proxy [X509TrustManager] []
             (getAcceptedIssuers [] nil)
             (checkClientTrusted [certs authType] nil)
             (checkServerTrusted [certs authType] nil))
        sc (SSLContext/getInstance "SSL")]
    (.init sc nil (into-array TrustManager [tm]) nil)
    (let [sf (.getSocketFactory sc)]
      (with-open [^SSLSocket socket (.createSocket sf host port)]
        (.startHandshake socket)
        (.getPeerCertificates (.getSession socket))))))

(defn print-certificates
  "Prints the SSL certificates for a given host and port."
  [^String host ^Integer port]
  (doseq [cert (get-certificates host port)]
    (println cert)))
