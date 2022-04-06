<template>
  <div>
    <el-card class="box-card" v-for="(c, index) in collections" :key="index">
      <template #header>
        <div class="card-header">
          <div>
            <div>
              <el-button
                class="button"
                type="text"
                @click="needinfo(index)"
                v-if="isneedinfo[index] != 1"
                >分享者：{{ c.share.sharer.user.username }}</el-button
              >
              <div id="cmessage" v-else>
                <div style="padding-bottom: 5px; color: gray">
                  <div v-if="c.share.sharer.name != ''">
                    姓名：{{ c.share.sharer.name }}
                  </div>
                  <div v-if="c.share.sharer.age != null">
                    年龄：{{ c.share.sharer.age }}
                  </div>
                  <div v-if="c.share.sharer.sex != ''">
                    性别：{{ c.share.sharer.sex }}
                  </div>
                  <div v-if="c.share.sharer.province != ''">
                    省份：{{ c.share.sharer.province }}
                  </div>
                  <div v-if="c.share.sharer.contact != ''">
                    联系方式：{{ c.share.sharer.contact }}
                  </div>
                </div>
                <input
                  type="text"
                  v-model="message[index]"
                  placeholder="请输入留言内容"
                  style="width: 120px"
                />
                <button
                  @click="sendMessage(c.share.sharer.id, message[index], index)"
                >
                  留言
                </button>
                <button @click="closeinfo(index)">关闭</button>
              </div>
            </div>
          </div>

          <div>
            <el-button
              class="button"
              type="text"
              style="cursor: default; color: gray"
              >收藏时间：{{ c.time }}</el-button
            >
            <el-button
              class="button"
              type="text"
              style="cursor: default; color: gray"
              >点赞数：{{ c.share.agreeCount }}</el-button
            >
            <el-button class="button" type="text" @click="agree(c.share.id)"
              >点赞</el-button
            >

            <el-button
              class="button"
              type="text"
              @click="cancelCollection(c.id)"
              style="color: red"
              >取消收藏</el-button
            >
          </div>
        </div>
      </template>
      <div class="item" style="border-bottom: 0.5px solid silver; color: gray">
        <div style="padding-bottom: 18px" v-if="c.share.title != ''">
          {{ c.share.title }}
        </div>
        <div>
          {{ c.share.text }}
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
          <div id="ccomment" style="padding-bottom: 18px">
            <input
              type="text"
              v-model="comment[index]"
              placeholder="请输入评论内容"
            /><button @click="addComment(c.share.id, comment[index], index)">
              评论
            </button>
            <button @click="closecomment(index)">关闭</button>
          </div>
          <el-table
            :data="c.share.comments"
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
import { useStore } from "vuex";
const usePersonalCollection = (store: any) => {
  const getPersonalCollections = () => {
    setTimeout(() => {
      store.dispatch("getPersonalCollections");
    }, 100);
  };
  const cancelCollection = (id: any) => {
    if (confirm("您确定要取消收藏该分享吗")) {
      store.dispatch("cancelCollection", id);
      getPersonalCollections();
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
    const comment = ref([]) as any;
    const message = ref([]) as any;
    const isneedinfo = ref([]) as any;
    const isneedcomment = ref([]) as any;
    const collections = computed(() => store.state.personalCollections);
    const needinfo = (index: any) => {
      isneedinfo.value[index] = 1;
    };
    const closeinfo = (index: any) => {
      isneedinfo.value[index] = null;
      message.value[index] = "";
    };
    const needcomment = (index: any) => {
      isneedcomment.value[index] = 1;
    };
    const closecomment = (index: any) => {
      isneedcomment.value[index] = null;
      comment.value[index] = "";
    };
    const agree = (id: any) => {
      if (confirm("您确定要为这条分享点赞吗")) {
        store.dispatch("agree", id);
        getPersonalCollections();
      }
    };
    const addComment = (id: any, content: any, index: any) => {
      if (confirm("您确定要发表评论吗")) {
        if (content.trim() != "") {
          store.dispatch("addComment", {
            id,
            content,
          });
          comment.value[index] = "";
          getPersonalCollections();
        } else store.commit("openDialog", "评论不可为空");
      }
    };
    const sendMessage = (id: any, text: any, index: any) => {
      if (confirm("您确定要留言吗")) {
        if (text.trim() != "") {
          store.dispatch("sendMessage", {
            id,
            text,
          });
          message.value[index] = "";
        } else store.commit("openDialog", "留言内容不可为空");
      }
    };
    const { getPersonalCollections, cancelCollection } =
      usePersonalCollection(store);
    getPersonalCollections();
    return {
      collections,
      cancelCollection,
      comment,
      addComment,
      agree,
      message,
      isneedinfo,
      needinfo,
      closeinfo,
      sendMessage,
      isneedcomment,
      needcomment,
      closecomment,
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
#cmessage button,
#ccomment button {
  border: 0.5px solid silver;
  color: gray;
  border-radius: 3px;
  background-color: white;
  cursor: pointer;
  font-size: 13px;
}
#cmessage button:hover,
#ccomment button:hover {
  background-color: rgb(79, 166, 236);
  border: 0.5px solid rgb(79, 166, 236);
  color: white;
}
#cmessage button:focus,
#ccomment button:focus {
  outline: none;
}
#cmessage input,
#ccomment input {
  font-size: 12px;
  color: gray;
  padding: 1px;
}
#ccomment button {
  font-size: 14px;
}
#ccomment input {
  font-size: 13px;
}
</style>
