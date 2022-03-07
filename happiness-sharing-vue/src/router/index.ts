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
      {
        path: "share/addShare",
        component: () => import("../views/AddShareView.vue"),
      },
      {
        path: "comment",
        component: () => import("../views/PersonalCommentView.vue"),
      },
      {
        path: "collection",
        component: () => import("../views/PersonalCollectionView.vue"),
      },
      {
        path: "report",
        component: () => import("../views/PersonalReportView.vue"),
      },
      {
        path: "message",
        component: () => import("../views/PersonalMessageView.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
