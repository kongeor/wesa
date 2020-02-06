(ns wesa.handler
  (:require
    [compojure.route :as route]
    [compojure.core :refer [defroutes GET POST]]
    [ring.util.response]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [wesa.html :as html]
    [ring.util.response :as response]))

(defn query-handler [req]
  #_(clojure.pprint/pprint req)
  (response/redirect (str "?q=" (-> req :params :q))))

(defroutes routes
  (GET "/" [] html/index)
  (POST "/query" [] query-handler)
  (route/not-found html/index))

(def app
  (-> routes
    (wrap-defaults site-defaults)))