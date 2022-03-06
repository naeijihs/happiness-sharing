<template>
  <div>
    <index-view />
    <dialog-component />
  </div>
</template>

<script lang="ts">
import { defineAsyncComponent, defineComponent } from "vue";
import IndexView from "./views/IndexView.vue";
const DialogComponent = defineAsyncComponent(
  () => import("../src/components/DialogComponent.vue")
);
export default defineComponent({
  components: { IndexView, DialogComponent },
  setup() {
    return {};
  },
});
</script>

<style></style>
