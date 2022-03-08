<template>
  <div>
    <div>
      <router-link to="/sharer/personalCenter">info</router-link>
      <router-link to="/sharer/personalCenter/share">share</router-link>
      <router-link to="/sharer/personalCenter/comment">comment</router-link>
      <router-link to="/sharer/personalCenter/collection"
        >collection</router-link
      >
      <router-link to="/sharer/personalCenter/report">report</router-link>
      <router-link to="/sharer/personalCenter/message">message</router-link>
    </div>
    <div><router-view name="person" /></div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  setup() {
    return {};
  },
});
</script>
