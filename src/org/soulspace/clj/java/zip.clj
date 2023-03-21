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

(ns org.soulspace.clj.java.zip
  "Functions for handling ZIP files."
  (:require [clojure.java.io :as io])
  (:import [java.util.zip ZipInputStream]
           [java.io File FileInputStream IOException])) 
            
(defn new-file
  "Safely generates a new file for the zip entry.
   Throws an exception, if the entry is outside of the destination directory to prevent a Zip Slip vulnerability."
  [destination-dir entry]
  (let [dest-file (File. destination-dir (.getName entry))
        dest-dir-path (.getCanonicalPath destination-dir)
        dest-file-path (.getCanonicalPath dest-file)]
    (if (.startsWith dest-file-path (str dest-dir-path (File/separator)))
      ; entry is safely inside the destination directory, so return the file for it
      dest-file
      ; entry is outside of the destination dirctory
      (throw (IOException. (str "Entry is outside of the destination directory : " (.getName entry)))))))

(defn unzip-file
  "Unzips the zip file into the destination path."
  [zip-file-path destination-path]
  (with-open [zip-stream (ZipInputStream. (FileInputStream. zip-file-path))]
    (loop [entry (.getNextEntry zip-stream)]
      (when entry
        (let [;output-file (io/as-file (str destination-path (File/separator) (.getName entry)))
              output-file (new-file destination-path entry)
              parent-dir (-> output-file .getParentFile)
              _ (when parent-dir (.mkdirs parent-dir))
              _ (if (.isDirectory entry)
                  (.mkdir output-file)
                  (with-open [output-stream (io/output-stream output-file)]
                    (io/copy zip-stream output-stream)
                    (.closeEntry zip-stream)))]
          (recur (.getNextEntry zip-stream)))))))
