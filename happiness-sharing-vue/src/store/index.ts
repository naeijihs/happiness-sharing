import axios from "../axios";
import { createStore } from "vuex";
import { Sunday } from "@/util/stringMatchUtil";
import { jsQuickSort } from "@/util/quickSortUtil";
const state = {
  role: "",
  dialogMessage: "",
  personalInfo: {},
  personalShares: [],
  allShares: [],
  publicShares: [],
  personalComments: [],
  personalCollections: [],
  personalReports: [],
  sendMessages: [],
  receiveMessages: [],
  unsettledReports: [],
  images: [],
  recommends: [],
  recommend: {},
  sharers: [],
  comments: [],
  messages: [],
  communications: [],
  src: "",
};
const actions = {
  login: async ({ commit }: any, { username, password }: any) => {
    try {
      const res = await axios.post("/login", {
        username,
        password,
      });
      sessionStorage.setItem("role", res.data.role);
      sessionStorage.setItem("auth", res.headers.authorization);
      commit("login", res.data);
    } catch (error) {
      if (error) throw error;
    }
  },
  regist: async ({ commit }: any, { username, password }: any) => {
    try {
      const { data } = await axios.post("/regist", {
        username,
        password,
      });
      commit("openDialog", data.info);
    } catch (error) {
      if (error) throw error;
    }
  },
  modifyPassword: async ({ commit }: any, password: string) => {
    try {
      const { data } = await axios.post("/modifyPassword", {
        password,
      });
      commit("openDialog", data.info);
    } catch (error) {
      if (error) throw error;
    }
  },
  getPersonalInfo: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/sharer/info/getOwnInfo");
      commit("getPersonalInfo", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  modifyPersonalInfo: async ({ commit }: any, personInfo: any) => {
    try {
      const { data } = await axios.post("/sharer/info/modifyInfo", personInfo);
      commit("modifyPersonalInfo", data);
      commit("openDialog", data.info);
    } catch (error) {
      if (error) throw error;
    }
  },
  addShare: async ({ commit }: any, share: any) => {
    try {
      const { data } = await axios.post(
        "http://127.0.0.1:5000/add_share",
        share
      );
      commit("openDialog", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  getPersonalShares: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/sharer/share/getOwnShares");
      commit("getPersonalShares", data.ownShares);
    } catch (error) {
      if (error) throw error;
    }
  },
  deletePersonalShare: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/sharer/share/delete/" + id);
      commit("openDialog", "删除成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  getAllShares: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/sharer/share/getAllShares");
      commit("getAllShares", data.allShares);
    } catch (error) {
      if (error) throw error;
    }
  },
  agree: async ({ commit }: any, id: any) => {
    try {
      const { data } = await axios.get("/sharer/share/agree/" + id);
      commit("openDialog", data.info);
    } catch (error) {
      if (error) throw error;
    }
  },
  addComment: async ({ commit }: any, { content, id }: any) => {
    try {
      const { data } = await axios.post("/sharer/share/comment/add", {
        content,
        share: {
          id,
        },
      });
      commit("openDialog", data.info);
    } catch (error) {
      if (error) throw error;
    }
  },
  report: async ({ commit }: any, { id, content }: any) => {
    try {
      const { data } = await axios.post("/sharer/share/report/send", {
        content,
        share: {
          id,
        },
      });
      commit("openDialog", data.info);
    } catch (error) {
      if (error) throw error;
    }
  },
  collect: async ({ commit }: any, { id }: any) => {
    try {
      const { data } = await axios.get(
        "/sharer/collection/addCollection/" + id
      );
      commit("openDialog", data.info);
    } catch (error) {
      if (error) throw error;
    }
  },
  sendMessage: async ({ commit }: any, { id, text }: any) => {
    try {
      const { data } = await axios.post("/sharer/message/send", {
        text,
        receiver: {
          id,
        },
      });
      commit("openDialog", data.info);
    } catch (error) {
      if (error) throw error;
    }
  },
  getPersonalComments: async ({ commit }: any) => {
    try {
      const { data } = await axios.get(
        "/sharer/share/comment/getOwnSendComments"
      );
      commit("getPersonalComments", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  deletePersonalComment: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/sharer/share/comment/deleteSendComment/" + id);
      commit("openDialog", "评论删除成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  getPersonalCollections: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/sharer/collection/getOwnCollections");
      commit("getPersonalCollections", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  cancelCollection: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/sharer/collection/cancelCollection/" + id);
      commit("openDialog", "取消收藏成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  getPersonalReports: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/sharer/report/getSendReports");
      commit("getPersonalReports", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  getPersonalMessages: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/sharer/message/getMessages");
      commit("getPersonalMessages", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  deletePersonalMessage: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/sharer/message/delete/" + id);
      commit("openDialog", "留言删除成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  getUnsettledReports: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/admin/report/getAllUnsettledReports");
      commit("getUnsettledReports", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  acceptReport: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/admin/report/accept/" + id);
      commit("openDialog", "举报已受理");
    } catch (error) {
      if (error) throw error;
    }
  },
  refuseReport: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/admin/report/refuse/" + id);
      commit("openDialog", "举报已拒绝");
    } catch (error) {
      if (error) throw error;
    }
  },
  addImage: async ({ commit }: any, url: any) => {
    try {
      await axios.post("/admin/slideshow/add", {
        picture: url,
      });
      commit("openDialog", "添加成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  deleteImage: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/admin/slideshow/delete/" + id);
      commit("openDialog", "删除成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  getImages: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/common/getAllSlideshows");
      commit("getImages", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  addRecommend: async ({ commit }: any, { title, content }: any) => {
    try {
      await axios.post("/common/recommend/add", { title, content });
      commit("openDialog", "添加成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  getRecommends: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/common/recommend/getAll");
      commit("getRecommends", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  getRecommend: async ({ commit }: any, id: any) => {
    try {
      const { data } = await axios.get("/common/recommend/getOne/" + id);
      commit("getRecommend", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  deleteRecommend: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/common/recommend/delete/" + id);
      commit("openDialog", "删除成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  getSharers: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/admin/sharer/all");
      commit("getSharers", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  deleteSharer: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/admin/sharer/delete/" + id);
      commit("openDialog", "删除成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  getShares: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/admin/share/all");
      commit("getAllShares", data.allShares);
    } catch (error) {
      if (error) throw error;
    }
  },
  deleteShare: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/admin/share/delete/" + id);
      commit("openDialog", "删除成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  getComments: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/admin/comment/all");
      commit("getComments", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  deleteComment: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/admin/comment/delete/" + id);
      commit("openDialog", "删除成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  getMessages: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/admin/message/all");
      commit("getMessages", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  deleteMessage: async ({ commit }: any, id: any) => {
    try {
      await axios.get("/admin/message/delete/" + id);
      commit("openDialog", "删除成功");
    } catch (error) {
      if (error) throw error;
    }
  },
  communicate: async ({ commit }: any, { content, time }: any) => {
    try {
      await axios.post("/sharer/communication/communicate", { content, time });
    } catch (error) {
      if (error) throw error;
    }
  },
  closeCommunication: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/sharer/communication/close");
      commit("openDialog", data.data);
    } catch (error) {
      if (error) throw error;
    }
  },
};
const mutations = {
  login: (state: any, data: any) => {
    state.role = data.role;
  },
  unlogin: (state: any) => {
    setTimeout(() => {
      sessionStorage.clear();
      state.role = "";
      location.reload();
    }, 600);
  },
  getPersonalInfo: (state: any, { sharer }: any) => {
    state.personalInfo = sharer;
  },
  modifyPersonalInfo: (state: any, { sharer }: any) => {
    state.personalInfo = sharer;
  },
  getPersonalShares: (state: any, personalShares: any) => {
    state.personalShares = personalShares.reverse();
  },
  getAllShares: (state: any, allShares: any) => {
    state.allShares = allShares.reverse(); //reverse改变原数组
    state.publicShares = allShares;
  },
  getPersonalComments: (state: any, data: any) => {
    state.personalComments = data.comments.reverse();
  },
  getPersonalCollections: (state: any, data: any) => {
    state.personalCollections = data.collections.reverse();
  },
  getPersonalReports: (state: any, data: any) => {
    state.personalReports = data.reports.reverse();
  },
  getPersonalMessages: (state: any, data: any) => {
    state.sendMessages = data.sendMessages.reverse();
    state.receiveMessages = data.receiveMessages.reverse();
  },
  getUnsettledReports: (state: any, data: any) => {
    state.unsettledReports = data.allUnsettledReports.reverse();
  },
  match: (state: any, { search, category, sort }: any) => {
    if (category == "全部" && search == "")
      //很奇怪，似乎state的值不能直接赋给state，会出bug
      state.publicShares = state.allShares.filter(() => true);
    else if (category != "全部" && search == "")
      state.publicShares = state.allShares.filter(
        (share: any) => share.category == category
      );
    else if (category == "全部" && search != "")
      state.publicShares = state.allShares.filter((share: any) =>
        Sunday((share.title + share.text).trim(), search.trim())
      );
    else
      state.publicShares = state.allShares
        .filter((share: any) =>
          Sunday((share.title + share.text).trim(), search.trim())
        )
        .filter((share: any) => share.category == category);
    if (sort == 1) state.publicShares.reverse();
    else if (sort == 2)
      state.publicShares = jsQuickSort(state.publicShares).reverse();
    else if (sort == 3) state.publicShares = jsQuickSort(state.publicShares);
  },
  getImages: (state: any, data: any) => {
    state.images = data.slideshows.reverse();
  },
  getRecommends: (state: any, data: any) => {
    state.recommends = data.recommends;
  },
  getRecommend: (state: any, data: any) => {
    state.recommend = data.recommend;
  },
  getSharers: (state: any, data: any) => {
    state.sharers = data.sharers;
  },
  getComments: (state: any, data: any) => {
    state.comments = data.comments;
  },
  getMessages: (state: any, data: any) => {
    state.messages = data.messages;
  },
  timeCommunications: (state: any, communications: any) => {
    state.communications = communications;
  },
  src: (state: any, src: any) => {
    state.src = src;
  },
  clearsrc: (state: any) => {
    state.src = "";
  },
  closeDialog: (state: any) => {
    state.dialogMessage = "";
  },
  openDialog: (state: any, info: string) => {
    state.dialogMessage = info;
  },
};
export default createStore({
  state,
  actions,
  mutations,
});
