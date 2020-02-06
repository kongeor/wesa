(ns wesa.tw
  (:require [twitter.oauth :as oauth]
            [twitter.api.restful :as api]
            [environ.core :refer [env]]))

(defn get-creds []
  (oauth/make-oauth-creds (env :twitter-app-key) (env :twitter-app-secret)))

(defn get-tweets [query]
  (let [my-creds (get-creds)
        tweets (api/search-tweets
                 :oauth-creds my-creds
                 :params {:result_type "recent"
                          :lang "en"
                          :count 20
                          :q (str query " AND -filter:retweets AND -filter:replies")
                          :tweet_mode "extended"})]
    (-> tweets :body :statuses)
    #_(mapv (fn [e] [(:id e) (:full_text e)])
      (-> tweets :body :statuses))))

(comment
  (map :full_text (get-tweets "clojure")))
