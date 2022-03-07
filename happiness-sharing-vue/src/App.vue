<template>
  <div>
    <index-view v-if="showPage" />
    <dialog-component />
  </div>
</template>

<script lang="ts">
import {
  defineAsyncComponent,
  defineComponent,
  nextTick,
  provide,
  ref,
} from "vue";
import IndexView from "./views/IndexView.vue";
const DialogComponent = defineAsyncComponent(
  () => import("../src/components/DialogComponent.vue")
);
export default defineComponent({
  components: { IndexView, DialogComponent },
  setup() {
    let showPage = ref<boolean>(true);
    const refresh = () => {
      setTimeout(() => {
        showPage.value = false;
        nextTick(() => (showPage.value = true));
      }, 60);
    };
    provide("refresh", refresh);
    return {
      showPage,
    };
  },
});
</script>

<style></style>
