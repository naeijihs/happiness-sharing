import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    component: () => import("../views/SharerIndexView.vue"),
  },
  {
    path: "/sharer/personalCenter",
    component: () => import("../views/PersonalCenterView.vue"),
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
