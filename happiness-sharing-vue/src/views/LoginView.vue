<template>
  <h1 style="width: 200px; margin: 0 auto">快乐分享网站</h1>
  <el-tabs v-model="activeName" type="card" class="demo-tabs">
    <el-tab-pane label="登录" name="first">
      <div id="login">
        <input type="text" placeholder="用户名" v-model="username" />
        <input type="password" placeholder="密码" v-model="password" />
        <div id="btn">
          <button @click="login(username, password)">登录</button>
        </div>
      </div></el-tab-pane
    >
    <el-tab-pane label="注册" name="second">
      <div id="login">
        <input type="text" placeholder="用户名" v-model="username" />
        <input type="password" placeholder="密码" v-model="password" />
        <input
          type="password"
          placeholder="请再一次输入密码"
          v-model="tpassword"
        />
        <div id="btn">
          <button @click="regist(username, password, tpassword)">注册</button>
        </div>
      </div></el-tab-pane
    >
  </el-tabs>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useStore } from "vuex";
const useLogin = (store: any) => {
  const login = (username: any, password: any) => {
    if (password.trim() && username.trim()) {
      store.dispatch("login", {
        username,
        password,
      });
    } else {
      store.commit("openDialog", "用户名或密码不可为空");
    }
  };
  const regist = (username: any, password: any, tpassword: any) => {
    if (
      password.trim() &&
      username.trim() &&
      tpassword.trim() &&
      tpassword == password
    ) {
      store.dispatch("regist", {
        username,
        password,
      });
    } else if (tpassword != password) {
      store.commit("openDialog", "两次密码输入不一致");
    } else {
      store.commit("openDialog", "用户名或密码不可为空");
    }
  };
  return {
    login,
    regist,
  };
};
export default defineComponent({
  setup() {
    let username = ref("");
    let password = ref("");
    let tpassword = ref("");
    const activeName = ref("first");
    const store = useStore();
    const { login, regist } = useLogin(store);
    return {
      username,
      password,
      tpassword,
      login,
      regist,
      activeName,
    };
  },
});
</script>

<style scoped>
#login {
  width: 400px;
  margin: 23vh auto;
  border: 1px solid gray;
  padding: 0 10px;
  border-radius: 6px;
}
input {
  width: 300px;
  height: 30px;
  font-size: 17px;
  margin-left: 46px;
  margin-top: 50px;
  border: 1px solid silver;
  border-radius: 6px;
  padding-left: 7px;
}
input:focus {
  outline: none;
  border: 1px solid black;
}
button {
  width: 70px;
  height: 35px;
  font-size: 16.5px;
  cursor: pointer;
  border: 1px solid silver;
  color: gray;
  border-radius: 6px;
  background-color: white;
  display: block;
}
button:first-child {
  margin: 0 auto;
}
button:hover {
  background-color: rgb(79, 166, 236);
  border: 1px solid rgb(79, 166, 236);
  color: white;
}
button:focus {
  outline: none;
}
#btn {
  margin-top: 32px;
  margin-bottom: 28px;
}
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>
