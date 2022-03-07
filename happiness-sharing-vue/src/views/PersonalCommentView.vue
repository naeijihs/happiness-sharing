<template>
  <div>
    <div v-for="(c, i) in comments" :key="i">
      评论的分享：{{ c.share.title }} 评论的内容：{{ c.content }} 评论的时间{{
        c.time
      }}
      <button @click="deleteComment(c.id)">delete</button>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, inject } from "vue";
import { useStore } from "vuex";
const usePersonalComment = (store: any, refresh: any) => {
  const getPersonalComments = () => {
    store.dispatch("getPersonalComments");
  };
  const deleteComment = (id: any) => {
    if (confirm("您确定要删除这个评论吗")) {
      store.dispatch("deleteComment", id);
      refresh();
    }
  };
  return {
    getPersonalComments,
    deleteComment,
  };
};
export default defineComponent({
  setup() {
    const store = useStore();
    const refresh = inject("refresh");
    const comments = computed(() => store.state.personalComments);
    const { getPersonalComments, deleteComment } = usePersonalComment(
      store,
      refresh
    );
    getPersonalComments();
    return {
      comments,
      deleteComment,
    };
  },
});
</script>
