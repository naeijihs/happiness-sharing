<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      style="justify-content: center"
      @select="handleSelect"
    >
      <el-menu-item index="1">
        <router-link to="/">快乐广场</router-link></el-menu-item
      >
      <el-menu-item index="2">
        <router-link to="/sharer/personalCenter"
          >个人中心</router-link
        ></el-menu-item
      >
    </el-menu>
    <router-view
      name="sharer"
      style="width: 66vw; margin: 0 auto; text-align: center"
    />
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
  components: {
    ModifyPasswordComponent,
    UnloginComponent,
  },
  setup() {
    const activeIndex = ref("1");
    const router = useRouter();
    router.push("/");
    const handleSelect = (key: string) => {
      activeIndex.value = key;
      if (key == "1") router.push("/");
      else if (key == "2") router.push("/sharer/personalCenter");
    };
    return {
      activeIndex,
      handleSelect,
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
