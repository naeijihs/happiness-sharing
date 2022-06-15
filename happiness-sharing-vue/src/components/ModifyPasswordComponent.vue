<template>
  <div id="modify">
    <div v-if="!isclick" style="text-align: right">
      <a @click="click" style="margin-right: 5px">密码修改</a>
    </div>
    <div v-else style="width: 378px">
      <input
        type="password"
        v-model="newPassword"
        placeholder="请输入新密码"
        autofocus
      />
      <input
        type="password"
        v-model="newtPassword"
        placeholder="请再一次输入密码"
      />
      <button @click="modifyPassword">修改</button>
      <button @click="modifyPasswordClose">关闭</button>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, Ref, ref } from "vue";
import { useStore } from "vuex";
const useModifyPassword = (
  isclick: Ref,
  newPassword: Ref,
  store: any,
  newtPassword: Ref
) => {
  const click = () => {
    isclick.value = true;
  };
  const modifyPasswordClose = () => {
    isclick.value = false;
    newPassword.value = "";
    newtPassword.value = "";
  };
  const modifyPassword = () => {
    if (
      newPassword.value.trim() &&
      newtPassword.value.trim() &&
      newPassword.value == newtPassword.value
    ) {
      store.dispatch("modifyPassword", newPassword.value);
    } else if (
      newPassword.value.trim() == "" ||
      newtPassword.value.trim() == ""
    ) {
      store.commit("openDialog", "密码不能为空或空格");
    } else if (newPassword.value != newtPassword.value) {
      store.commit("openDialog", "两次密码输入不一致");
    }
    newPassword.value = "";
    newtPassword.value = "";
  };
  return {
    click,
    modifyPassword,
    modifyPasswordClose,
  };
};
export default defineComponent({
  setup() {
    const isclick = ref<boolean>(false);
    const newPassword = ref("");
    const newtPassword = ref("");
    const store = useStore();
    const { click, modifyPassword, modifyPasswordClose } = useModifyPassword(
      isclick,
      newPassword,
      store,
      newtPassword
    );
    return {
      isclick,
      click,
      newPassword,
      newtPassword,
      modifyPassword,
      modifyPasswordClose,
    };
  },
});
</script>

<style scoped>
#modify {
  height: 25px;
  position: fixed;
  right: 15px;
  top: 10px;
  z-index: 15;
  border-radius: 10px;
}
a {
  display: block;
  text-decoration: none;
  cursor: pointer;
  color: rgb(136, 136, 136);
}
a:hover {
  color: black;
}
input {
  border: 0.5px solid silver;
  color: gray;
  border-radius: 3px;
}
button {
  border: 0.5px solid silver;
  color: gray;
  border-radius: 3px;
  background-color: white;
  cursor: pointer;
}
button:hover {
  background-color: rgb(79, 166, 236);
  border: 0.5px solid rgb(79, 166, 236);
  color: white;
}
button:focus {
  outline: none;
}
</style>
