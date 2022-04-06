/* eslint-disable prettier/prettier */
import axios from "axios";
import store from "../store/index";
axios.defaults.baseURL = "http://localhost:8080";
axios.interceptors.request.use(
    (req) => {
        const auth = sessionStorage.getItem("auth");
        if (auth && req.headers) {
            req.headers.authorization = auth;
        }
        return req;
    },
    (err) => {
        return Promise.reject(err);
    }
);
axios.interceptors.response.use(
    (res) => {
        return res;
    },
    (err) => {
        const json = JSON.stringify(err);
        const data = JSON.parse(json);
        const status = data.status;
        if (status == 401) {
            store.commit("openDialog", "您输入有误/您处于未登录状态无法访问");
            store.commit("unlogin");
        }
        else if (status == 403) {
            store.commit("openDialog", "无权限");
            store.commit("unlogin");
        }
        else if (status == 500) {
            store.commit("openDialog", "服务器出现错误")
        }

        return Promise.reject(err);
    }
)
export default axios;
