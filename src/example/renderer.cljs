(ns example.renderer
  (:require ["react" :as react]
            [reagent.core :as r]
            ["react-split-pane" :as SplitPane]
            ["react-calendar" :as cal :refer [Calendar]]
            [example.formatting :as f]))

(defonce selected-date (r/atom nil))

(println @selected-date)

(defn selected-date-comp
  [d]
  [:div
   "Selected date: "
   (f/format [:yyyy "-" :mm "-" :dd] d)])

(defn main-content []
  [:div {:className "main"}
   [selected-date-comp @selected-date]])

(defn both-panes []
  [(r/adapt-react-class SplitPane)
   {:minSize 300}
   [(r/adapt-react-class Calendar)
    {:onChange #(reset! selected-date %)}]
   [main-content]])

(defn start []
  (js/console.log "renderer - start")
  (r/render (both-panes)
            (.getElementById js/document "app")))

(defn init []
  (js/console.log "renderer - init")
  (start))

(defn stop []
  (js/console.log "renderer - stop"))
