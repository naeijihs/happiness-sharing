import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    component: () => import("../views/SharerIndexView.vue"),
  },
  {
    path: "/sharer/personalCenter",
    component: () => import("../views/PersonalCenterView.vue"),
    children: [
      {
        path: "",
        component: () => import("../views/PersonalInfoView.vue"),
      },
      {
        path: "share",
        component: () => import("../views/PersonalShareView.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
