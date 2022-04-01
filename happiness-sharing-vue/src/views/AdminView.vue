<template>
  <div>
    <div>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
      >
        <el-menu-item index="1">
          <router-link to="/admin/report">举报管理</router-link></el-menu-item
        >
        <el-menu-item index="2">
          <router-link to="/admin/slide">轮播图管理</router-link></el-menu-item
        >
      </el-menu>
    </div>
    <div><router-view name="admin" /></div>
    <modify-password-component />
    <unlogin-component />
  </div>
</template>

<script lang="ts">
import ModifyPasswordComponent from "@/components/ModifyPasswordComponent.vue";
import UnloginComponent from "@/components/UnloginComponent.vue";
import { defineComponent, ref } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  components: { ModifyPasswordComponent, UnloginComponent },
  setup() {
    const activeIndex = ref("1");
    const router = useRouter();
    router.push("/admin/report");
    return {
      activeIndex,
    };
  },
});
</script>
<style scoped>
a {
  text-decoration: none;
  font-size: 20px;
}
</style>
