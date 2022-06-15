<template>
  <div>
    <el-table
      :data="unsettledReports"
      style="width: 100%; font-size: 17px"
      empty-text="暂无举报"
    >
      <el-table-column
        fixed
        prop="share.title"
        label="分享标题"
        width="150"
        align="center"
      />
      <el-table-column
        prop="share.text"
        label="分享内容"
        width="400"
        align="center"
      />
      <el-table-column
        prop="reporter.user.username"
        label="举报者"
        width="120"
        align="center"
      />
      <el-table-column
        prop="content"
        label="举报陈述"
        width="400"
        align="center"
      />
      <el-table-column
        prop="time"
        label="举报时间"
        width="200"
        align="center"
      />
      <el-table-column fixed="right" label="处理" width="120" align="center">
        <template #default="scope">
          <el-button
            type="text"
            size="small"
            @click="acceptReport(scope.row.id)"
            >受理</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="refuseReport(scope.row.id)"
            >拒绝</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, inject } from "vue";
import { useStore } from "vuex";
const useAdminReport = (store: any, refresh: any) => {
  const getUnsettledReports = () => {
    store.dispatch("getUnsettledReports");
  };
  const acceptReport = (id: any) => {
    if (confirm("您确定受理这份举报吗")) {
      store.dispatch("acceptReport", id);
      refresh();
    }
  };
  const refuseReport = (id: any) => {
    if (confirm("您确定不受理这份举报吗")) {
      store.dispatch("refuseReport", id);
      refresh();
    }
  };
  return {
    getUnsettledReports,
    refuseReport,
    acceptReport,
  };
};
export default defineComponent({
  setup() {
    const store = useStore();
    const refresh = inject("refresh");
    const unsettledReports = computed(() => store.state.unsettledReports);
    const { getUnsettledReports, acceptReport, refuseReport } = useAdminReport(
      store,
      refresh
    );
    getUnsettledReports();
    return {
      acceptReport,
      refuseReport,
      unsettledReports,
    };
  },
});
</script>
