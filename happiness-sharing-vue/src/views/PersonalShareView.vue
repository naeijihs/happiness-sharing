<template>
  <div>
    <el-button
      class="button"
      type="text"
      @click="toAdd"
      style="margin-top: 15px; font-size: 18px"
      >分享添加</el-button
    >
    <el-card
      class="box-card"
      v-for="(share, index) in personalShares"
      :key="index"
    >
      <template #header>
        <div class="card-header">
          <el-button
            class="button"
            type="text"
            style="cursor: default; color: gray"
            >{{ share.time }}</el-button
          >
          <div>
            <el-button
              class="button"
              type="text"
              style="cursor: default; color: gray"
              >点赞数：{{ share.agreeCount }}</el-button
            >
            <el-button class="button" type="text" @click="agree(share.id)"
              >点赞</el-button
            >
            <el-button
              class="button"
              type="text"
              @click="deletePersonalShare(share.id)"
              style="color: red"
              >删除</el-button
            >
          </div>
        </div>
      </template>
      <div
        class="item"
        style="
          border-bottom: 0.5px solid silver;
          color: gray;
          font-weight: blod;
        "
      >
        <div style="padding-bottom: 18px" v-if="share.title != ''">
          {{ share.title }}
        </div>
        <div>
          {{ share.text }}
        </div>
      </div>
      <div style="padding: 18px 0 0 0">
        <el-button
          class="button"
          type="text"
          @click="needcomment(index)"
          v-if="isneedcomment[index] != 1"
          style="color: darkseagreen"
          >评论区</el-button
        >
        <div v-else>
          <div id="pcomment" style="padding-bottom: 18px">
            <input
              type="text"
              v-model="comment[index]"
              placeholder="请输入评论内容"
            /><button @click="addComment(share.id, comment[index], index)">
              评论
            </button>
            <button @click="closecomment(index)">关闭</button>
          </div>
          <el-table
            :data="share.comments"
            style="width: 100%"
            height="250"
            empty-text="暂无评论"
          >
            <el-table-column
              prop="commenter.user.username"
              label="用户"
              width="100"
              align="center"
            />
            <el-table-column
              prop="content"
              label="评论"
              width="500"
              align="center"
            />
            <el-table-column
              prop="time"
              label="时间"
              width="200"
              align="center"
            />
          </el-table>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
const usePersonalShare = (store: any) => {
  const getPersonalShares = () => {
    setTimeout(() => {
      store.dispatch("getPersonalShares");
    }, 100);
  };
  const deletePersonalShare = (id: any) => {
    if (confirm("您确定要删除这条分享吗")) {
      store.dispatch("deletePersonalShare", id);
      getPersonalShares();
    }
  };
  const agree = (id: any) => {
    if (confirm("您确定要为这条分享点赞吗")) {
      store.dispatch("agree", id);
      getPersonalShares();
    }
  };
  const addComment = (id: any, comment: any) => {
    if (confirm("您确定要发表评论吗")) {
      store.dispatch("addComment", {
        id,
        content: comment,
      });
      getPersonalShares();
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
    const comment = ref([]) as any;
    const store = useStore();
    const router = useRouter();
    const isneedcomment = ref([]) as any;
    const personalShares = computed(() => store.state.personalShares);
    const needcomment = (index: any) => {
      isneedcomment.value[index] = 1;
    };
    const closecomment = (index: any) => {
      isneedcomment.value[index] = null;
      comment.value[index] = "";
    };
    const toAdd = () => {
      router.push("/sharer/personalCenter/share/addShare");
    };
    const { getPersonalShares, deletePersonalShare, agree, addComment } =
      usePersonalShare(store);
    getPersonalShares();
    return {
      personalShares,
      deletePersonalShare,
      agree,
      addComment,
      comment,
      needcomment,
      closecomment,
      isneedcomment,
      toAdd,
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
.item {
  padding-bottom: 18px;
}
.box-card {
  margin: 20px auto;
  width: 60vw;
}
#pcomment button {
  border: 0.5px solid silver;
  color: gray;
  border-radius: 3px;
  background-color: white;
  cursor: pointer;
  font-size: 14px;
}
#pcomment button:hover {
  background-color: rgb(79, 166, 236);
  border: 0.5px solid rgb(79, 166, 236);
  color: white;
}
#pcomment button:focus {
  outline: none;
}
#pcomment input {
  font-size: 13px;
  color: gray;
  padding: 1px;
}
</style>
