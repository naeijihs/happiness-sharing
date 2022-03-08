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
      const { data } = await axios.post("/sharer/share/add", share);
      commit("openDialog", data.info);
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
  addComment: async ({ commit }: any, { comment, id }: any) => {
    try {
      const { data } = await axios.post("/sharer/share/comment/add", {
        content: comment,
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
  sendMessage: async ({ commit }: any, { id, message }: any) => {
    try {
      const { data } = await axios.post("/sharer/message/send", {
        text: message,
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
  deleteComment: async ({ commit }: any, id: any) => {
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
  getMessages: async ({ commit }: any) => {
    try {
      const { data } = await axios.get("/sharer/message/getMessages");
      commit("getMessages", data);
    } catch (error) {
      if (error) throw error;
    }
  },
  deleteMessage: async ({ commit }: any, id: any) => {
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
};
const mutations = {
  login: (state: any, data: any) => {
    state.role = data.role;
  },
  unlogin: (state: any) => {
    sessionStorage.clear();
    state.role = "";
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
    state.allShares = allShares.reverse(); //reverse改变原属组
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
  getMessages: (state: any, data: any) => {
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
