<template>
  <el-dialog v-model="dialogVisible" title="Tip" width="36%" center="true">
    <div style="text-align: center; font-size: 17px">
      <span>{{ dialogMessage }}</span>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="closeDialog">confirm</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { computed, defineComponent, ref, watch } from "vue";
import { useStore } from "vuex";
const useDialog = (store: any) => {
  const closeDialog = () => {
    store.commit("closeDialog");
  };
  return {
    closeDialog,
  };
};
export default defineComponent({
  setup() {
    const store = useStore();
    const dialogVisible = ref(false);
    const dialogMessage = computed(() => store.state.dialogMessage);
    watch(dialogMessage, (newValue) => {
      if (newValue == "") dialogVisible.value = false;
      else dialogVisible.value = true;
    });
    const { closeDialog } = useDialog(store);
    return {
      dialogVisible,
      closeDialog,
      dialogMessage,
    };
  },
});
</script>
