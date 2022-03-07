<template>
  <div>
    <div v-for="(r, i) in reports" :key="i">
      举报的分享标题：{{ r.share.title }} 举报分享的内容：{{
        r.share.text
      }}
      举报内容：{{ r.content }} 举报时间{{ r.time }} 举报处理状态：{{ r.state }}
    </div>
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
