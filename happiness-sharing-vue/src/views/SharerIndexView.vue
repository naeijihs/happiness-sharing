<template>
  <div>
    <div
      style="
        width: 15vw;
        position: fixed;
        left: 2vw;
        top: 40vh;
        border: 0.5px solid silver;
        border-radius: 2px;
      "
    >
      <div style="color: gray">推送</div>
      <div v-for="(recommend, index) in recommends" :key="index">
        <router-link
          :to="`/sharer/recommend/one/${recommend.id}`"
          style="text-decoration: none; color: #409eff"
          >{{ recommend.title }}</router-link
        >
      </div>
    </div>
    <div style="width: 60vw; margin: 0 auto">
      <el-carousel
        :interval="3000"
        indicator-position="outside"
        height="40vh"
        ref="carousel"
      >
        <el-carousel-item v-for="(image, index) in images" :key="index">
          <img :src="image.picture" />
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="demo-input-suffix" style="width: 33vw; margin: 0 auto">
      <el-input
        v-model="search"
        class="w-50 m-2"
        :prefix-icon="Search"
        clearable
      />
    </div>
    <div>
      <el-radio
        size="large"
        v-model="category"
        @change="match(search, category, sort)"
        label="全部"
        >全部</el-radio
      >
      <el-radio
        size="large"
        v-model="category"
        @change="match(search, category, sort)"
        label="school"
        >校园</el-radio
      >
      <el-radio
        size="large"
        v-model="category"
        @change="match(search, category, sort)"
        label="home"
        >家庭</el-radio
      >
      <el-radio
        size="large"
        v-model="category"
        @change="match(search, category, sort)"
        label="society"
        >社会</el-radio
      >
    </div>
    <div>
      <el-radio v-model="sort" @change="match(search, category, sort)" label="4"
        >近期</el-radio
      >
      <el-radio v-model="sort" @change="match(search, category, sort)" label="1"
        >早期</el-radio
      >
      <el-radio v-model="sort" @change="match(search, category, sort)" label="2"
        >多赞</el-radio
      >
      <el-radio v-model="sort" @change="match(search, category, sort)" label="3"
        >低赞</el-radio
      >
    </div>
    <el-card
      class="box-card"
      v-for="(share, index) in publicShares"
      :key="index"
    >
      <template #header>
        <div class="card-header">
          <div>
            <div>
              <el-button
                class="button"
                type="text"
                @click="needinfo(index)"
                v-if="isneedinfo[index] != 1"
                >分享者：{{ share.sharer.user.username }}</el-button
              >
              <div id="message" v-else>
                <div style="padding-bottom: 5px; color: gray">
                  <div v-if="share.sharer.name != ''">
                    姓名：{{ share.sharer.name }}
                  </div>
                  <div v-if="share.sharer.age != null">
                    年龄：{{ share.sharer.age }}岁
                  </div>
                  <div v-if="share.sharer.sex != ''">
                    性别：{{ share.sharer.sex }}
                  </div>
                  <div v-if="share.sharer.province != ''">
                    省份：{{ share.sharer.province }}
                  </div>
                  <div v-if="share.sharer.contact != ''">
                    联系方式：{{ share.sharer.contact }}
                  </div>
                </div>
                <input
                  type="text"
                  v-model="message[index]"
                  placeholder="请输入留言内容"
                  style="width: 120px"
                />
                <button
                  @click="sendMessage(share.sharer.id, message[index], index)"
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
              >{{ share.time }}</el-button
            >
            <el-button
              class="button"
              type="text"
              style="cursor: default; color: gray"
              >点赞数：{{ share.agreeCount }}</el-button
            >
            <el-button class="button" type="text" @click="agree(share.id)"
              >点赞</el-button
            >

            <el-button class="button" type="text" @click="collect(share.id)"
              >收藏</el-button
            >
            <div v-if="isneedreport[index] == 1" id="report">
              <input
                type="text"
                v-model="reportContent[index]"
                placeholder="请输入举报陈述"
                style="width: 150px"
              />
              <button @click="report(share.id, reportContent[index], index)">
                举报
              </button>
              <button @click="closereport(index)">关闭</button>
            </div>
            <el-button
              class="button"
              type="text"
              @click="needreport(index)"
              style="color: red"
              v-else
              >举报</el-button
            >
          </div>
        </div>
      </template>
      <div class="item" style="border-bottom: 0.5px solid silver">
        <div style="padding-bottom: 18px" v-if="share.title != ''">
          {{ share.title }}
        </div>
        <div
          style="text-align: left; padding-bottom: 18px"
          v-if="share.text != ''"
        >
          {{ share.text }}
        </div>
        <div
          v-if="share.picture != null && share.picture != ''"
          style="padding-bottom: 18px"
        >
          <img :src="share.picture" style="width: 300px; height: 200px" />
        </div>
        <div v-if="share.videoPath != '' && share.videoPath != null">
          <video controls>
            <source :src="share.videoPath" type="video/mp4" />
          </video>
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
          <div id="comment" style="padding-bottom: 18px">
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
    <el-button
      class="button"
      type="text"
      @click="toCommunicate"
      style="position: fixed; top: 40vh; right: 8vw; font-size: 17px"
      >聊天室</el-button
    >
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, onMounted, ref, watch } from "vue";
import { useStore } from "vuex";
import { Search } from "@element-plus/icons-vue";
import router from "@/router";
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
    const isneedcomment = ref([]) as any;
    const publicShares = computed(() => store.state.publicShares);
    const images = computed(() => store.state.images);
    const carousel = ref(null) as any;
    const recommends = computed(() => store.state.recommends);
    const getImages = () => {
      store.dispatch("getImages");
    };
    onMounted(() => {
      setTimeout(() => {
        carousel.value.setActiveItem(0);
      }, 4);
    });
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
    const needcomment = (index: any) => {
      isneedcomment.value[index] = 1;
    };
    const closecomment = (index: any) => {
      isneedcomment.value[index] = null;
      comment.value[index] = "";
    };
    const getAllShares = () => {
      setTimeout(() => {
        store.dispatch("getAllShares");
        setTimeout(() => {
          match(search.value, category.value, sort.value);
        }, 100);
      }, 100);
    };
    const agree = (id: any) => {
      if (confirm("您确定要为这条分享点赞吗")) {
        store.dispatch("agree", id);
        getAllShares();
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
          getAllShares();
        } else store.commit("openDialog", "评论不可为空");
      }
    };
    const report = (id: any, content: any, index: any) => {
      if (confirm("您确定要举报吗")) {
        if (content.trim() != "") {
          store.dispatch("report", {
            id,
            content,
          });
          reportContent.value[index] = "";
        } else store.commit("openDialog", "举报内容不可为空");
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
    const getRecommends = () => {
      store.dispatch("getRecommends");
    };
    const toCommunicate = () => {
      router.push("/sharer/communicate");
    };
    getRecommends();
    watch(search, () => match(search.value, category.value, sort.value));
    getImages();
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
      images,
      carousel,
      Search,
      isneedcomment,
      needcomment,
      closecomment,
      recommends,
      toCommunicate,
    };
  },
});
</script>
<style scoped>
img {
  height: 40vh;
  width: 60vw;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.box-card {
  margin: 20px auto;
  width: 60vw;
}
#report button,
#message button,
#comment button {
  border: 0.5px solid silver;
  color: gray;
  border-radius: 3px;
  background-color: white;
  cursor: pointer;
  font-size: 13px;
}
#report button:hover,
#message button:hover,
#comment button:hover {
  background-color: rgb(79, 166, 236);
  border: 0.5px solid rgb(79, 166, 236);
  color: white;
}
#report button:focus,
#message button:focus,
#comment button:focus {
  outline: none;
}
#report input,
#message input,
#comment input {
  font-size: 12px;
  color: gray;
  padding: 1px;
}
#comment button {
  font-size: 14px;
}
#comment input {
  font-size: 13px;
}
.el-popper.is-customized {
  /* Set padding to ensure the height is 32px */
  padding: 6px 12px;
  background: linear-gradient(90deg, rgb(159, 229, 151), rgb(204, 229, 129));
}

.el-popper.is-customized .el-popper__arrow::before {
  background: linear-gradient(45deg, #b2e68d, #bce689);
  right: 0;
}
</style>
