import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import Forbidden from "../views/Forbidden.vue";
import BookForm from "../views/BookForm.vue";
import Login from "../views/Login.vue";
import PlaceholderView from "../views/PlaceholderView.vue";
import ReservationView from "../views/ReservationView.vue";
import AnalyticView from "../views/AnalyticView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/forbidden",
    name: "Forbidden",
    component: Forbidden,
  },
  {
    path: "/book",
    name: "book",
    component: BookForm,
  },
  {
    path: "/login",
    name: "login",
    component: Login,
  },
  {
    path: "/placeholder",
    name: "placeholder",
    component: PlaceholderView,
    meta: {
      layout: "custom-layout",
    },
  },
  {
    path: "/reservation",
    name: "reservations",
    component: ReservationView,
    meta: {
      layout: "custom-layout",
    },
  },
  {
    path: "/analytic",
    name: "analytics",
    component: AnalyticView,
    meta: {
      layout: "custom-layout",
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
