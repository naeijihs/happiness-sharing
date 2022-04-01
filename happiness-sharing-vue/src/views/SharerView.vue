<template>
  <div>
    <div style="width: 80vw; margin: 0 auto">
      <el-carousel :interval="4000" type="card" height="33vh" ref="carousel">
        <el-carousel-item v-for="(image, index) in images" :key="index">
          <img :src="image.picture" />
        </el-carousel-item>
      </el-carousel>
    </div>

    <router-link to="/">广场</router-link>
    <router-link to="/sharer/personalCenter">个人中心</router-link>
    <router-view name="sharer" />
    <modify-password-component />
    <unlogin-component />
  </div>
</template>

<script lang="ts">
import ModifyPasswordComponent from "@/components/ModifyPasswordComponent.vue";
import UnloginComponent from "@/components/UnloginComponent.vue";
import { computed, defineComponent, onMounted, ref } from "vue";
import { useStore } from "vuex";

export default defineComponent({
  components: {
    ModifyPasswordComponent,
    UnloginComponent,
  },
  setup() {
    const store = useStore();
    const images = computed(() => store.state.images);
    const carousel = ref(null) as any;
    const getImages = () => {
      store.dispatch("getImages");
    };
    onMounted(() => {
      setTimeout(() => {
        carousel.value.setActiveItem(0);
      }, 4);
    });
    getImages();
    return {
      images,
      carousel,
    };
  },
});
</script>

<style scoped>
img {
  height: 33vh;
  width: 40vw;
}
</style>
