<template>
  <div>
    <router-view />
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
