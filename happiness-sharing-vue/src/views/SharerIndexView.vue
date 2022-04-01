<template>
  <div>
    <div>
      按类筛选：
      <label
        ><input
          type="radio"
          v-model="category"
          @change="match(search, category, sort)"
          value="全部"
        />全部</label
      >
      <label
        ><input
          type="radio"
          v-model="category"
          @change="match(search, category, sort)"
          value="school"
        />school</label
      >
      <label
        ><input
          type="radio"
          v-model="category"
          @change="match(search, category, sort)"
          value="home"
        />home</label
      >
      <label
        ><input
          type="radio"
          v-model="category"
          @change="match(search, category, sort)"
          value="company"
        />company</label
      >
    </div>
    <div>
      排序方式：
      <label
        ><input
          type="radio"
          v-model="sort"
          @change="match(search, category, sort)"
          value="0"
        />最近发布</label
      >
      <label
        ><input
          type="radio"
          v-model="sort"
          @change="match(search, category, sort)"
          value="1"
        />早期发布</label
      >
      <label
        ><input
          type="radio"
          v-model="sort"
          @change="match(search, category, sort)"
          value="2"
        />点赞最多</label
      >
      <label
        ><input
          type="radio"
          v-model="sort"
          @change="match(search, category, sort)"
          value="3"
        />点赞最少</label
      >
    </div>
    <div>
      <input type="text" v-model="search" />
    </div>
    <div v-for="(share, index) in publicShares" :key="index">
      {{ share.sharer.user.username }}
      <button @click="needinfo(index)" v-if="isneedinfo[index] != 1">
        info
      </button>
      <div v-else>
        {{ share.sharer.name }}
        {{ share.sharer.age }}
        {{ share.sharer.sex }}
        {{ share.sharer.contact }}
        {{ share.sharer.province }}
        <input type="text" v-model="message[index]" />
        <button @click="sendMessage(share.sharer.id, message[index], index)">
          message
        </button>
        <button @click="closeinfo(index)">closeinfo</button>
      </div>
      timu:{{ share.title }}neirong:{{ share.text }} {{ share.time }}点赞数{{
        share.agreeCount
      }}
      <button @click="agree(share.id)">agree</button>
      <button @click="collect(share.id)">收藏</button>
      <div v-if="isneedreport[index] == 1">
        <input type="text" v-model="reportContent[index]" />
        <button @click="report(share.id, reportContent[index], index)">
          report
        </button>
        <button @click="closereport(index)">closereport</button>
      </div>
      <button @click="needreport(index)" v-else>openreport</button>
      <div>
        评论：<input type="text" v-model="comment[index]" /><button
          @click="addComment(share.id, comment[index], index)"
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
import { computed, defineComponent, ref, watch } from "vue";
import { useStore } from "vuex";
export default defineComponent({
  setup() {
    const search = ref("");
    const category = ref("全部");
    const sort = ref(0);
    const store = useStore();
    const comment = ref([]) as any;
    const reportContent = ref([]) as any;
    const message = ref([]) as any;
    const isneedreport = ref([]) as any;
    const isneedinfo = ref([]) as any;
    const publicShares = computed(() => store.state.publicShares);
    const needreport = (index: any) => {
      isneedreport.value[index] = 1;
    };
    const closereport = (index: any) => {
      isneedreport.value[index] = null;
      reportContent.value[index] = "";
    };
    const needinfo = (index: any) => {
      isneedinfo.value[index] = 1;
    };
    const closeinfo = (index: any) => {
      isneedinfo.value[index] = null;
      message.value[index] = "";
    };
    const getAllShares = () => {
      setTimeout(() => {
        store.dispatch("getAllShares");
        setTimeout(() => {
          match(search.value, category.value, sort.value);
        }, 30);
      }, 60);
    };
    const agree = (id: any) => {
      if (confirm("您确定要为这条分享点赞吗")) {
        store.dispatch("agree", id);
        getAllShares();
      }
    };
    const addComment = (id: any, content: any, index: any) => {
      if (confirm("您确定要发表评论吗")) {
        store.dispatch("addComment", {
          id,
          content,
        });
        comment.value[index] = "";
        getAllShares();
      }
    };
    const report = (id: any, content: any, index: any) => {
      if (confirm("您确定要举报吗")) {
        store.dispatch("report", {
          id,
          content,
        });
        reportContent.value[index] = "";
      }
    };
    const sendMessage = (id: any, text: any, index: any) => {
      if (confirm("您确定要留言吗")) {
        store.dispatch("sendMessage", {
          id,
          text,
        });
        message.value[index] = "";
      }
    };
    const collect = (id: any) => {
      if (confirm("您确定要收藏吗")) {
        store.dispatch("collect", {
          id,
        });
      }
    };
    const match = (search: string, category: string, sort: any) => {
      store.commit("match", { search, category, sort });
    };
    watch(search, () => match(search.value, category.value, sort.value));
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
      category,
      sort,
      match,
    };
  },
});
</script>
