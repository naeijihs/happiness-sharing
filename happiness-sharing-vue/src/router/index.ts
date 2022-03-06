import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "allIndex",
    redirect: "/sharer",
    children: [
      {
        path: "/xx",
        component: () => import("../views/LoginView.vue"),
      },
      {
        path: "admin",
        component: () => import("../views/AdminView.vue"),
      },
      {
        path: "sharer",
        component: () => import("../views/SharerView.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
