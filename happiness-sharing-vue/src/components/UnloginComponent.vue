<template>
  <div id="unlogin">
    <a @click="unlogin">退出登录</a>
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
  top: 36px;
  right: 20px;
  z-index: 15;
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
</style>
