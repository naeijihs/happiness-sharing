<template>
  <div>
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
import { defineComponent } from "vue";

export default defineComponent({
  components: { ModifyPasswordComponent, UnloginComponent },
  setup() {
    return {};
  },
});
</script>
