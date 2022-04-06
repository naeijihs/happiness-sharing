<template>
  <div id="modify">
    <div v-if="!isclick" style="text-align: right">
      <a @click="click" style="margin-right: 5px">密码修改</a>
    </div>
    <div v-else style="width: 230px">
      <input
        type="password"
        v-model="newPassword"
        placeholder="请输入新密码"
        autofocus
      />
      <button @click="modifyPassword">修改</button>
      <button @click="modifyPasswordClose">关闭</button>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, Ref, ref } from "vue";
import { useStore } from "vuex";
const useModifyPassword = (isclick: Ref, newPassword: Ref, store: any) => {
  const click = () => {
    isclick.value = true;
  };
  const modifyPasswordClose = () => {
    isclick.value = false;
    newPassword.value = "";
  };
  const modifyPassword = () => {
    if (newPassword.value.trim()) {
      store.dispatch("modifyPassword", newPassword.value);
    } else {
      store.commit("openDialog", "密码不能为空或空格");
    }
    newPassword.value = "";
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
    const store = useStore();
    const { click, modifyPassword, modifyPasswordClose } = useModifyPassword(
      isclick,
      newPassword,
      store
    );
    return {
      isclick,
      click,
      newPassword,
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
