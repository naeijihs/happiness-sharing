<template>
  <div>
    <div>classfication</div>
    <div><input type="text" v-model="search" /></div>
    <div v-for="(share, index) in publicShares" :key="index">
      {{ share.sharer.user.username }}
      <button @click="needinfo(index)">info</button>
      <div v-if="isneedinfo[index] == 1">
        {{ share.sharer.name }}
        {{ share.sharer.age }}
        {{ share.sharer.sex }}
        {{ share.sharer.contact }}
        {{ share.sharer.province }}
        <input type="text" v-model="message[index]" />
        <button @click="sendMessage(share.sharer.id, message[index])">
          message
        </button>
        <button @click="closeinfo(index)">closeinfo</button>
      </div>
      {{ share.title }}
      {{ share.time }}点赞数{{ share.agreeCount }}
      <button @click="agree(share.id)">agree</button>
      <button @click="collect(share.id)">收藏</button>
      <div v-if="isneedreport[index] == 1">
        <input type="text" v-model="reportContent[index]" />
        <button @click="report(share.id, reportContent[index])">report</button>
        <button @click="closereport(index)">closereport</button>
      </div>
      <button @click="needreport(index)" v-else>openreport</button>
      <div>
        评论：<input type="text" v-model="comment[index]" /><button
          @click="addComment(share.id, comment[index])"
        >
          submit
        </button>
      </div>
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
const useShareIndex = (store: any, refresh: any) => {
  const getAllShares = () => {
    store.dispatch("getAllShares");
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
  const report = (id: any, content: any) => {
    if (confirm("您确定要举报吗")) {
      store.dispatch("report", {
        id,
        content,
      });
      refresh();
    }
  };
  const sendMessage = (id: any, message: any) => {
    if (confirm("您确定要留言吗")) {
      store.dispatch("sendMessage", {
        id,
        message,
      });
      refresh();
    }
  };
  const collect = (id: any) => {
    if (confirm("您确定要收藏吗")) {
      store.dispatch("collect", {
        id,
      });
      refresh();
    }
  };
  return {
    getAllShares,
    agree,
    addComment,
    report,
    collect,
    sendMessage,
  };
};
export default defineComponent({
  setup() {
    const search = ref("");
    const store = useStore();
    const comment = ref([]);
    const reportContent = ref([]);
    const message = ref([]);
    const isneedreport = ref([]) as any;
    const isneedinfo = ref([]) as any;
    const refresh = inject("refresh");
    const publicShares = computed(() => store.state.publicShares);
    const needreport = (index: any) => {
      isneedreport.value[index] = 1;
    };
    const closereport = (index: any) => {
      isneedreport.value[index] = null;
    };
    const needinfo = (index: any) => {
      isneedinfo.value[index] = 1;
    };
    const closeinfo = (index: any) => {
      isneedinfo.value[index] = null;
    };
    const { getAllShares, addComment, agree, report, collect, sendMessage } =
      useShareIndex(store, refresh);
    getAllShares();
    return {
      search,
      publicShares,
      comment,
      addComment,
      agree,
      needreport,
      closereport,
      report,
      reportContent,
      isneedreport,
      collect,
      message,
      isneedinfo,
      needinfo,
      closeinfo,
      sendMessage,
    };
  },
});
</script>
