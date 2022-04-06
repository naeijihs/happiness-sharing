<template>
  <div>
    <div style="margin: 20px 0" />
    <el-input
      v-model="shareTitle"
      autosize
      type="textarea"
      placeholder="标题（可为空）"
      style="font-size: 17px"
    />
    <div style="margin: 20px 0" />
    <el-input
      v-model="shareText"
      :autosize="{ minRows: 17 }"
      type="textarea"
      placeholder="内容（不可为空）"
      style="font-size: 17px"
    />
    <div style="padding: 20px 0">
      <el-button
        class="button"
        type="text"
        @click="addShare"
        style="font-size: 18px; padding: 0 10vw"
        >发布</el-button
      >
      <el-button
        class="button"
        type="text"
        @click="closeAddShare"
        style="font-size: 18px; padding: 0 10vw; color: gray"
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
    const shareTitle = ref("");
    const shareText = ref("");
    //作用域问题 无法模块化  必须放在setup才能更新响应式数据
    const addShare = () => {
      if (shareText.value.trim()) {
        store.dispatch("addShare", {
          title: shareTitle.value,
          text: shareText.value,
        });
        shareTitle.value = "";
        shareText.value = "";
      } else store.commit("openDialog", "分享内容不可为空");
    };
    const closeAddShare = () => {
      router.push("/sharer/personalCenter/share");
    };
    return {
      shareTitle,
      shareText,
      addShare,
      closeAddShare,
    };
  },
});
</script>
