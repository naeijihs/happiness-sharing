<template>
  <div>
    <el-table
      :data="reports"
      style="width: 100%"
      height="75vh"
      empty-text="您未举报"
    >
      <el-table-column
        prop="content"
        label="举报陈述"
        width="250"
        align="center"
      />
      <el-table-column
        prop="share.text"
        label="举报内容"
        width="250"
        align="center"
      />
      <el-table-column
        prop="time"
        label="举报时间"
        width="170"
        align="center"
      />
      <el-table-column
        prop="state"
        label="状态"
        width="120"
        align="center"
        fixed="right"
      />
    </el-table>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent } from "vue";
import { useStore } from "vuex";
const usePersonalReport = (store: any) => {
  const getPersonalReports = () => {
    store.dispatch("getPersonalReports");
  };
  return {
    getPersonalReports,
  };
};
export default defineComponent({
  setup() {
    const store = useStore();
    const reports = computed(() => store.state.personalReports);
    const { getPersonalReports } = usePersonalReport(store);
    getPersonalReports();
    return {
      reports,
    };
  },
});
</script>
<style scoped>
.my-label {
  background: var(--el-color-success-light-9);
}
.my-content {
  background: var(--el-color-danger-light-9);
}
</style>
