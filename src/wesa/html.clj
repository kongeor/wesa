(ns wesa.html
  (:require
    (hiccup [page :refer [html5 include-js include-css]])
    [wesa.tw :as tw]))

(defn index [req]
  (let [q (-> req :params :q)
        tweets (when q (tw/get-tweets q))]
    (html5
      [:head
       [:meta {:charset "utf-8"}]
       [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
       [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
       [:meta {:name "description" :content "wesa"}]
       [:meta {:name "author" :content "Kostas Georgiadis"}]
       [:title "wesa"]

       (include-css
         "//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
         "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
         "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css")

       "<!--[if lt IE 9]>"
       [:script {:src "https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"}]
       [:script {:src "https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"}]
       "<![endif]-->"]
      [:body

       [:div#main-area.container
        [:h1 "wesa!"]
        [:form {:method "POST" :action "query"}
         [:input {:type "hidden" :name "__anti-forgery-token" :value (:anti-forgery-token req)}]
         [:div.form-group
          [:label {:for "query-input"} "Query"]
          [:input#query-input.form-control {:type "text" :name "q"}]]
         [:button.btn.btn-primary {:type "submit"} "Submit"]]

        [:br]

        (when tweets
          [:table.table
           [:thead
            [:tr
             [:th {:scope "col"} "Text"]
             [:th {:scope "col"} "Positive"]
             [:th {:scope "col"} "Neutral"]
             [:th {:scope "col"} "Negative"]
             [:th {:scope "col"} "Compound"]]]
           [:tbody
            (for [t tweets]
              [:tr
               [:td (:full_text t)]
               [:td (-> t :polarity :positive)]
               [:td (-> t :polarity :neutral)]
               [:td (-> t :polarity :negative)]
               [:td (-> t :polarity :compound)]])]])]


       (include-js
         "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"
         "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js")])))