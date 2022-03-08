<template>
  <div>
    <div>
      <router-link to="/admin/report">reporthandle</router-link>
    </div>
    <div><router-view name="admin" /></div>
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
