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
        path: "share/draw",
        components: {
          person: () => import("../views/DrawView.vue"),
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
  {
    path: "/admin/recommend",
    components: {
      admin: () => import("../views/AdminRecommendView.vue"),
    },
  },
  {
    path: "/admin/recommend/add",
    components: {
      admin: () => import("../views/AddRecommendView.vue"),
    },
  },
  {
    path: "/admin/recommend/one/:id",
    props: true,
    components: {
      admin: () => import("../views/RecommendView.vue"),
    },
  },
  {
    path: "/sharer/recommend/one/:id",
    props: true,
    components: {
      sharer: () => import("../views/SharerRecommendView.vue"),
    },
  },
  {
    path: "/sharer/communicate",
    components: {
      sharer: () => import("../views/SharerCommunicateView.vue"),
    },
  },
  {
    path: "/admin/sharer",
    components: {
      admin: () => import("../views/AdminSharerView.vue"),
    },
  },
  {
    path: "/admin/share",
    components: {
      admin: () => import("../views/AdminShareView.vue"),
    },
  },
  {
    path: "/admin/comment",
    components: {
      admin: () => import("../views/AdminCommentView.vue"),
    },
  },
  {
    path: "/admin/message",
    components: {
      admin: () => import("../views/AdminMessageView.vue"),
    },
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
