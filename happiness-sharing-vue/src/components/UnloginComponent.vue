<template>
  <div id="unlogin">
    <button @click="unlogin">退出登录</button>
  </div>
</template>

<script lang="ts">
import { defineComponent, inject } from "vue";
import { useStore } from "vuex";

export default defineComponent({
  setup() {
    const store = useStore();
    const refresh: any = inject("refresh");
    const unlogin = () => {
      store.commit("unlogin");
      refresh();
    };
    return {
      unlogin,
    };
  },
});
</script>
<style scoped>
#unlogin {
  position: fixed;
  top: 10px;
  right: 10px;
  z-index: 15;
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
