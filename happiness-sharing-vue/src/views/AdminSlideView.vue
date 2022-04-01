<template>
  <div>
    <div style="width: 600px; display: flex; margin: 40px auto 20px">
      <el-input v-model="url" placeholder="请输入图片地址" clearable />
      <el-row class="mb-4">
        <el-button type="primary" @click="addImage">添加</el-button>
      </el-row>
    </div>
    <el-row>
      <el-col
        v-for="(image, index) in images"
        :key="index"
        :span="6"
        :offset="index % 3 == 0 ? 2 : 1"
        style="margin-top: 40px"
      >
        <el-card :body-style="{ padding: '0px' }">
          <img :src="image.picture" class="image" />
          <div style="padding: 14px">
            <div class="bottom" style="text-align: center">
              <el-button
                type="text"
                class="button"
                @click="deleteImage(image.id)"
                >删除</el-button
              >
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, ref } from "vue";
import { useStore } from "vuex";

export default defineComponent({
  setup() {
    const store = useStore();
    const url = ref("");
    const images = computed(() => store.state.images);
    const addImage = () => {
      if (url.value.trim()) {
        if (confirm("你确定要添加这张图片地址吗")) {
          store.dispatch("addImage", url.value);
          getImages();
          url.value = "";
        }
      } else store.commit("openDialog", "不可为空");
    };
    const deleteImage = (id: any) => {
      if (confirm("你确定要删除这张图片吗")) {
        store.dispatch("deleteImage", id);
        getImages();
      }
    };
    const getImages = () => {
      setTimeout(() => {
        store.dispatch("getImages");
      }, 30);
    };
    getImages();
    return {
      url,
      addImage,
      images,
      deleteImage,
    };
  },
});
</script>

<style scoped>
img {
  width: 325px;
  height: 300px;
}
</style>
