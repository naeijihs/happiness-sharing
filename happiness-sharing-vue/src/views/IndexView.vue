<template>
  <div>
    <login-view v-if="vrole == '' && srole == null" />
    <admin-view v-else-if="vrole == 'admin' || srole == 'admin'" />
    <sharer-view v-else-if="vrole == 'sharer' || srole == 'sharer'" />
  </div>
</template>

<script lang="ts">
import { computed, defineAsyncComponent, defineComponent } from "vue";
import { useStore } from "vuex";
const AdminView = defineAsyncComponent(() => import("../views/AdminView.vue"));
const SharerView = defineAsyncComponent(
  () => import("../views/SharerView.vue")
);
const LoginView = defineAsyncComponent(() => import("../views/LoginView.vue"));
export default defineComponent({
  components: {
    LoginView,
    AdminView,
    SharerView,
  },
  setup() {
    const store = useStore();
    const vrole = computed(() => store.state.role);
    const srole = sessionStorage.getItem("role");
    return {
      vrole,
      srole,
    };
  },
});
</script>
