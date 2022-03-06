<template>
  <div>
    <router-link to="/">广场</router-link>
    <router-link to="/sharer/personalCenter">个人中心</router-link>
    <router-view></router-view>
    <modify-password-component />
  </div>
</template>

<script lang="ts">
import ModifyPasswordComponent from "@/components/ModifyPasswordComponent.vue";
import { defineComponent } from "vue";

export default defineComponent({
  components: { ModifyPasswordComponent },
  setup() {
    return {};
  },
});
</script>
