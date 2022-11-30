<template>
  <div>
    <div style="margin: 20px 0" />
    <el-input
      v-model="shareTitle"
      autosize
      type="textarea"
      placeholder="标题"
      style="font-size: 17px"
    />
    <div style="margin: 20px 0" />
    <el-input
      v-model="shareText"
      :autosize="{ minRows: 17 }"
      type="textarea"
      placeholder="内容"
      style="font-size: 17px"
    />
    <div style="position: fixed; right: 2vw; top: 42vh; width: 120px">
      <el-button
        class="button"
        type="text"
        style="font-size: 16px; color: #409eff; cursor: default"
        @click="todraw"
        >图片绘制</el-button
      >
    </div>
    <div style="position: fixed; right: 2vw; top: 48vh; width: 120px">
      <el-button
        class="button"
        type="text"
        style="font-size: 16px; color: darkseagreen; cursor: default"
        >图片选择</el-button
      >
      <input
        type="file"
        @change="upload"
        id="file"
        style="opacity: 0; position: absolute; left: 0; top: 5px"
      />
      <div v-if="picture != ''">
        <div>
          <img :src="picture" style="width: 60px; height: 60px" />
        </div>
        <div>
          <el-button
            class="button"
            type="text"
            style="font-size: 14px; color: red; cursor: pointer"
            @click="deletePicture"
            >删除</el-button
          >
        </div>
      </div>
    </div>
    <div style="position: fixed; right: 2vw; top: 66vh; width: 120px">
      <el-button
        class="button"
        type="text"
        style="font-size: 16px; color: orange; cursor: default"
        >视频选择</el-button
      >
      <input
        type="file"
        @change="selectVideo"
        id="video"
        style="opacity: 0; position: absolute; left: 0; top: 5px"
      />
      <div v-if="videoInfo">
        <div>{{ videoInfo }}</div>
        <div>
          <el-button
            class="button"
            type="text"
            style="font-size: 14px; color: red; cursor: pointer"
            @click="deleteVideo"
            >删除</el-button
          >
        </div>
      </div>
    </div>
    <div style="padding: 20px 0; display: flex; justify-content: space-around">
      <el-button
        class="button"
        type="text"
        @click="addShare"
        style="font-size: 18px"
        >发布</el-button
      >
      <el-button
        class="button"
        type="text"
        @click="closeAddShare"
        style="font-size: 18px; color: gray"
        >关闭</el-button
      >
    </div>
  </div>
</template>

<script lang="ts">
import axios from "@/axios";
import { computed, defineComponent, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
export default defineComponent({
  setup() {
    const store = useStore();
    const router = useRouter();
    const shareTitle = ref("");
    const shareText = ref("");
    let videoPath = {} as any;
    const videoInfo = computed(() => store.state.videoInfo);
    const picture = computed(() => store.state.src);
    const formData = computed(() => store.state.formData);
    //作用域问题 无法模块化  必须放在setup才能更新响应式数据
    const addShare = async () => {
      if (
        shareText.value.trim() ||
        shareTitle.value.trim() ||
        picture.value.trim() ||
        videoInfo.value
      ) {
        if (videoInfo.value)
          try {
            videoPath = await axios.post(
              "/sharer/share/addvideo",
              formData.value,
              {
                headers: { "Content-Type": "multipart/form-data" },
              }
            );
          } catch (error) {
            if (error) throw error;
          }
        store.dispatch("addShare", {
          title: shareTitle.value,
          text: shareText.value,
          picture: picture.value,
          videoPath: videoPath.data ? videoPath.data.videoPath : "",
        });
        shareTitle.value = "";
        shareText.value = "";
        deletePicture();
        deleteVideo();
      } else store.commit("openDialog", "分享不可为空");
    };
    const closeAddShare = () => {
      router.push("/sharer/personalCenter/share");
      store.commit("clearsrc");
    };
    const upload = (event: any) => {
      const file = event.target.files[0];
      const reader = new FileReader();
      reader.onload = function (e: any) {
        if (e.target.result.includes("image")) {
          const img = document.createElement("img");
          img.src = e.target.result;
          const canvas = document.createElement("canvas");
          const drawer = canvas.getContext("2d");
          canvas.width = 300;
          canvas.height = 600;
          if (drawer != null)
            drawer.drawImage(img, 0, 0, canvas.width, canvas.height);
          const quality = 0.2;
          store.commit("src", canvas.toDataURL(file.type, quality));
        } else {
          alert("请选择图片上传");
          event.target.value = "";
        }
      };
      if (file) {
        reader.readAsDataURL(file);
      }
    };
    const selectVideo = async (event: any) => {
      const file = event.target.files[0];
      store.commit("videoInfo", file.name);
      const formData = new FormData();
      formData.append("video", file);
      store.commit("formData", formData);
    };
    const deletePicture = () => {
      store.commit("clearsrc");
      const file = document.getElementById("file");
      (file as HTMLInputElement).value = "";
    };
    const deleteVideo = () => {
      store.commit("clearvideo");
      store.commit("clearvideoinfo");
      const file = document.getElementById("video");
      (file as HTMLInputElement).value = "";
    };
    const todraw = () => {
      router.push("/sharer/personalCenter/share/draw");
    };
    return {
      shareTitle,
      shareText,
      addShare,
      closeAddShare,
      picture,
      upload,
      deletePicture,
      todraw,
      selectVideo,
      formData,
      videoInfo,
      deleteVideo,
    };
  },
});
</script>
