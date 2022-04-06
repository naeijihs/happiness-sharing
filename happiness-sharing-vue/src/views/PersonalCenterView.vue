<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      style="justify-content: center"
      @select="handleSelect"
    >
      <el-menu-item index="1" class="second">个人信息</el-menu-item>
      <el-menu-item index="2" class="second">分享管理</el-menu-item>
      <el-menu-item index="3" class="second">评论管理</el-menu-item>
      <el-menu-item index="4" class="second">收藏管理</el-menu-item>
      <el-menu-item index="5" class="second">举报管理</el-menu-item>
      <el-menu-item index="6" class="second">留言管理</el-menu-item>
    </el-menu>
    <div><router-view name="person" /></div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  setup() {
    const activeIndex = ref("1");
    const router = useRouter();
    router.push("/sharer/personalCenter");
    const handleSelect = (key: string) => {
      activeIndex.value = key;
      if (key == "1") router.push("/sharer/personalCenter");
      else if (key == "2") router.push("/sharer/personalCenter/share");
      else if (key == "3") router.push("/sharer/personalCenter/comment");
      else if (key == "4") router.push("/sharer/personalCenter/collection");
      else if (key == "5") router.push("/sharer/personalCenter/report");
      else if (key == "6") router.push("/sharer/personalCenter/message");
    };
    return {
      activeIndex,
      handleSelect,
    };
  },
});
</script>
<style scoped>
.second {
  font-size: 17px;
  color: rgb(122, 122, 122);
}
</style>
