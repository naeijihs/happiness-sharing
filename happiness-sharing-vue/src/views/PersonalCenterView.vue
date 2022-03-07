<template>
  <div>
    <div>
      <router-link to="/sharer/personalCenter">info</router-link>
      <router-link to="/sharer/personalCenter/share">share</router-link>
    </div>
    <div><router-view /></div>
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
