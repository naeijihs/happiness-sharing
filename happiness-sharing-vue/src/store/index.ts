import axios from "../axios";
import { createStore } from "vuex";
const state = {
  role: "",
  dialogMessage: "",
  personalInfo: {},
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
};
const mutations = {
  login: (state: any, data: any) => {
    state.role = data.role;
  },
  getPersonalInfo: (state: any, { sharer }: any) => {
    state.personalInfo = sharer;
  },
  modifyPersonalInfo: (state: any, { sharer }: any) => {
    state.personalInfo = sharer;
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
