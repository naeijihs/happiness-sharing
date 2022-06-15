<template>
  <div style="padding: 20px 17vw">
    <el-input
      v-model="recommendTitle"
      autosize
      type="textarea"
      placeholder="推送标题"
      style="font-size: 17px"
    />
    <div style="margin: 20px 0" />
    <el-input
      v-model="recommendContent"
      :autosize="{ minRows: 20 }"
      type="textarea"
      placeholder="推送内容"
      style="font-size: 17px"
    />
    <div style="padding: 20px 0; display: flex; justify-content: space-around">
      <el-button
        class="button"
        type="text"
        @click="addRecommend"
        style="font-size: 20px"
        >发布</el-button
      >
      <el-button
        class="button"
        type="text"
        @click="closeAddRecommend"
        style="font-size: 20px; color: gray"
        >关闭</el-button
      >
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
export default defineComponent({
  setup() {
    const store = useStore();
    const router = useRouter();
    const recommendTitle = ref("");
    const recommendContent = ref("");
    const addRecommend = () => {
      if (recommendContent.value.trim() && recommendTitle.value.trim()) {
        store.dispatch("addRecommend", {
          title: recommendTitle.value,
          content: recommendContent.value,
        });
        recommendTitle.value = "";
        recommendContent.value = "";
      } else store.commit("openDialog", "推送不可为空");
    };
    const closeAddRecommend = () => {
      router.push("/admin/recommend");
    };
    return {
      recommendTitle,
      recommendContent,
      addRecommend,
      closeAddRecommend,
    };
  },
});
</script>
