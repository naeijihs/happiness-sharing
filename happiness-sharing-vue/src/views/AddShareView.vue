<template>
  <div>
    <input type="text" placeholder="title" v-model="shareTitle" />
    <textarea
      cols="30"
      rows="10"
      placeholder="text"
      v-model="shareText"
    ></textarea>
    <button @click="addShare">add</button>
    <button @click="closeAddShare">close</button>
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
      if (shareTitle.value.trim()) {
        store.dispatch("addShare", {
          title: shareTitle.value,
          text: shareText.value,
        });
        shareTitle.value = "";
        shareText.value = "";
      } else store.commit("openDialog", "标题不可为空");
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
