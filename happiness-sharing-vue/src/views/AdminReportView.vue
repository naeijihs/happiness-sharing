<template>
  <div>
    <div v-for="(r, i) in unsettledReports" :key="i">
      举报的分享标题：{{ r.share.title }} 举报的分享内容：{{
        r.share.text
      }}
      举报时间{{ r.time }}举报内容{{ r.content }}举报人{{
        r.reporter.user.username
      }}
      <button @click="acceptReport(r.id)">accept</button>
      <button @click="refuseReport(r.id)">refuse</button>
    </div>
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
