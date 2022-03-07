<template>
  <div>
    <div v-for="(c, i) in collections" :key="i">
      收藏的分享标题：{{ c.share.title }} 收藏分享的内容：{{
        c.share.text
      }}
      收藏时间{{ c.time }}
      <button @click="cancelCollection(c.id)">cancel</button>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, inject } from "vue";
import { useStore } from "vuex";
const usePersonalCollection = (store: any, refresh: any) => {
  const getPersonalCollections = () => {
    store.dispatch("getPersonalCollections");
  };
  const cancelCollection = (id: any) => {
    if (confirm("您确定要取消收藏该分享吗")) {
      store.dispatch("cancelCollection", id);
      refresh();
    }
  };
  return {
    getPersonalCollections,
    cancelCollection,
  };
};
export default defineComponent({
  setup() {
    const store = useStore();
    const refresh = inject("refresh");
    const collections = computed(() => store.state.personalCollections);
    const { getPersonalCollections, cancelCollection } = usePersonalCollection(
      store,
      refresh
    );
    getPersonalCollections();
    return {
      collections,
      cancelCollection,
    };
  },
});
</script>
