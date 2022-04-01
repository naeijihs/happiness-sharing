import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    components: {
      sharer: () => import("../views/SharerIndexView.vue"),
    },
  },
  {
    path: "/sharer/personalCenter",
    components: {
      sharer: () => import("../views/PersonalCenterView.vue"),
    },
    children: [
      {
        path: "",
        components: {
          person: () => import("../views/PersonalInfoView.vue"),
        },
      },
      {
        path: "share",
        components: {
          person: () => import("../views/PersonalShareView.vue"),
        },
      },
      {
        path: "share/addShare",
        components: {
          person: () => import("../views/AddShareView.vue"),
        },
      },
      {
        path: "comment",
        components: {
          person: () => import("../views/PersonalCommentView.vue"),
        },
      },
      {
        path: "collection",
        components: {
          person: () => import("../views/PersonalCollectionView.vue"),
        },
      },
      {
        path: "report",
        components: {
          person: () => import("../views/PersonalReportView.vue"),
        },
      },
      {
        path: "message",
        components: {
          person: () => import("../views/PersonalMessageView.vue"),
        },
      },
    ],
  },
  {
    path: "/admin/report",
    components: {
      admin: () => import("../views/AdminReportView.vue"),
    },
  },
  {
    path: "/admin/slide",
    components: {
      admin: () => import("../views/AdminSlideView.vue"),
    },
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
