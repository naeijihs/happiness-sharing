<template>
  <div>
    <router-link to="/sharer/personalCenter/share/addShare">add</router-link>
    <div v-for="(share, index) in personalShares" :key="index">
      {{ share.title }}
      {{ share.time }}点赞数{{ share.agreeCount }}
      <button @click="deletePersonalShare(share.id)">delete</button>
      <button @click="agree(share.id)">agree</button>
      评论：<input type="text" v-model="comment[index]" /><button
        @click="addComment(share.id, comment[index])"
      >
        submit
      </button>
      <div v-for="(c, i) in share.comments" :key="i">
        {{ c.content }}{{ c.time }}{{ c.commenter.user.username
        }}{{ c.commenter.name }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, inject, ref } from "vue";
import { useStore } from "vuex";
const usePersonalShare = (store: any, refresh: any) => {
  const getPersonalShares = () => {
    store.dispatch("getPersonalShares");
  };
  const deletePersonalShare = (id: any) => {
    if (confirm("您确定要删除这条分享吗")) {
      store.dispatch("deletePersonalShare", id);
      refresh();
    }
  };
  const agree = (id: any) => {
    if (confirm("您确定要为这条分享点赞吗")) {
      store.dispatch("agree", id);
      refresh();
    }
  };
  const addComment = (id: any, comment: any) => {
    if (confirm("您确定要发表评论吗")) {
      store.dispatch("addComment", {
        id,
        comment,
      });
      refresh();
    }
  };
  return {
    getPersonalShares,
    deletePersonalShare,
    agree,
    addComment,
  };
};
export default defineComponent({
  setup() {
    const comment = ref([]);
    const store = useStore();
    const personalShares = computed(() => store.state.personalShares);
    const refresh = inject("refresh");
    const { getPersonalShares, deletePersonalShare, agree, addComment } =
      usePersonalShare(store, refresh);
    getPersonalShares();
    return {
      personalShares,
      deletePersonalShare,
      agree,
      addComment,
      comment,
    };
  },
});
</script>
