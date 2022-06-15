<template>
  <div>
    <el-table
      :data="messages"
      style="width: 100%; font-size: 17px"
      empty-text="暂无留言"
    >
      <el-table-column
        prop="sender.user.username"
        label="留言者"
        width="200"
        align="center"
      />
      <el-table-column
        prop="receiver.user.username"
        label="接收者"
        width="200"
        align="center"
      />
      <el-table-column
        prop="text"
        label="留言内容"
        width="600"
        align="center"
      />
      <el-table-column
        prop="time"
        label="留言时间"
        width="200"
        align="center"
      />
      <el-table-column fixed="right" label="操作" width="120" align="center">
        <template #default="scope">
          <el-button
            type="text"
            size="small"
            @click="deleteMessage(scope.row.id)"
            style="color: red"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent } from "vue";
import { useStore } from "vuex";
export default defineComponent({
  setup() {
    const store = useStore();
    const messages = computed(() => store.state.messages);
    const getMessages = () => {
      store.dispatch("getMessages");
    };
    const deleteMessage = (id: any) => {
      if (confirm("您确认要删除这个留言吗")) {
        store.dispatch("deleteMessage", id);
        setTimeout(() => {
          getMessages();
        }, 100);
      }
    };
    getMessages();
    return {
      messages,
      deleteMessage,
    };
  },
});
</script>
