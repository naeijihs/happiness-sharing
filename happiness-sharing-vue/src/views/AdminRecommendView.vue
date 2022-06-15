<template>
  <div>
    <div style="text-align: center">
      <el-button
        class="button"
        type="text"
        @click="toAddRecommend"
        style="margin: 20px 0 0 0; font-size: 18px"
        >推送添加</el-button
      >
    </div>

    <el-card
      class="box-card"
      style="margin: 20px auto"
      v-for="(recommend, i) in recommends"
      :key="i"
    >
      <div
        class="text item"
        style="display: flex; justify-content: space-between"
      >
        <div>
          <el-button
            class="button"
            type="text"
            style="color: gray; cursor: default; font-size: 17px"
            >{{ recommend.title }}</el-button
          >
        </div>
        <div>
          <el-button
            class="button"
            type="text"
            @click="toRecommend(recommend.id)"
            style="font-size: 17px"
            >浏览</el-button
          >
          <el-button
            class="button"
            type="text"
            @click="deleteRecommend(recommend.id)"
            style="color: red; font-size: 17px"
            >删除</el-button
          >
        </div>
      </div>
    </el-card>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

export default defineComponent({
  setup() {
    const router = useRouter();
    const store = useStore();
    const recommends = computed(() => store.state.recommends);
    const getRecommends = () => {
      setTimeout(() => {
        store.dispatch("getRecommends");
      }, 100);
    };
    const toAddRecommend = () => {
      router.push("/admin/recommend/add");
    };
    const deleteRecommend = (id: any) => {
      if (confirm("您确定要删除这个推送吗")) {
        store.dispatch("deleteRecommend", id);
        getRecommends();
      }
    };
    const toRecommend = (id: any) => {
      router.push("/admin/recommend/one/" + id);
    };
    getRecommends();
    return {
      toAddRecommend,
      recommends,
      deleteRecommend,
      toRecommend,
    };
  },
});
</script>
<style scoped>
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 60vw;
}
</style>
