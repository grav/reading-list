(ns reading-list.core
  (:require [clojure.walk]
            [com.github.bdesham.clj-plist :as pl]
            [clojure.string :as s]))

(defn html-link [{{title :title} :URIDictionary
                   url :URLString}]
  (str "<li><a href='" url "'>" title "</a></li>\n"))

(defn parse [in]
  (let [links (->>   in
                     pl/parse-plist
                     clojure.walk/keywordize-keys
                     :Children
                     (filter #(= (:Title %) "com.apple.ReadingList"))
                     first
                     :Children
                     (map html-link)
                     s/join)]
    (str "<!DOCTYPE html><html><head>
<meta charset='UTF-8'>
<title>Reading list</title>
<style>
li {
  font-size: 18pt;
  line-height: 150%;
}
a {
  text-decoration: none;
}
a:hover {
  text-decoration: underline;
}
</style>
</head><body><ul>"
         links
"</ul></body></html>")))

(defn -main []
  (-> System/in
      parse
      print))
