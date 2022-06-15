<template>
  <div>
    <el-card class="box-card" v-for="(share, index) in shares" :key="index">
      <template #header>
        <div class="card-header">
          <div>
            <el-button class="button" type="text"
              >分享者：{{ share.sharer.user.username }}</el-button
            >
          </div>

          <div>
            <el-button
              class="button"
              type="text"
              style="cursor: default; color: gray"
              >时间：{{ share.time }}</el-button
            >
            <el-button
              class="button"
              type="text"
              style="cursor: default; color: gray"
              >点赞数：{{ share.agreeCount }}</el-button
            >
            <el-button
              class="button"
              type="text"
              @click="deleteShare(share.id)"
              style="color: red"
              >删除</el-button
            >
          </div>
        </div>
      </template>
      <div class="item" style="border-bottom: 0.5px solid silver">
        <div style="padding-bottom: 18px" v-if="share.title != ''">
          {{ share.title }}
        </div>
        <div
          style="text-align: left; padding-bottom: 18px"
          v-if="share.text != ''"
        >
          {{ share.text }}
        </div>
        <div v-if="share.picture != null" style="padding-bottom: 18px">
          <img :src="share.picture" style="width: 300px; height: 200px" />
        </div>
      </div>
    </el-card>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent } from "vue";
import { useStore } from "vuex";
export default defineComponent({
  setup() {
    const store = useStore();
    const shares = computed(() => store.state.allShares);
    const getShares = () => {
      store.dispatch("getShares");
    };
    const deleteShare = (id: any) => {
      if (confirm("您确认要删除这个分享吗")) {
        store.dispatch("deleteShare", id);
        setTimeout(() => {
          getShares();
        }, 100);
      }
    };
    getShares();
    return {
      shares,
      deleteShare,
    };
  },
});
</script>
<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.box-card {
  margin: 20px auto;
  width: 60vw;
  text-align: center;
}
</style>
