<template>
  <div>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span style="font-size: 12px">{{ recommend.time }}</span>
          <span style="font-size: 19px">{{ recommend.title }}</span>
          <el-button
            class="button"
            type="text"
            @click="closeToRecommends"
            style="font-size: 15px"
            >关闭</el-button
          >
        </div>
      </template>
      <div style="font-size: 19px">
        {{ recommend.content }}
      </div>
    </el-card>
  </div>
</template>
<script lang="ts">
import { computed, defineComponent } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";

export default defineComponent({
  setup() {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const recommend = computed(() => store.state.recommend);
    const getRecommend = () => {
      store.dispatch("getRecommend", route.params.id);
    };
    const closeToRecommends = () => {
      router.push("/admin/recommend");
    };
    getRecommend();
    return {
      recommend,
      closeToRecommends,
    };
  },
});
</script>
<style scoped>
.box-card {
  width: 60vw;
  margin: 20px auto;
  color: gray;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
