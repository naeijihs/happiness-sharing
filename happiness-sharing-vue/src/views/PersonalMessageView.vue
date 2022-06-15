<template>
  <div>
    <el-table
      :data="sendMessages"
      style="width: 100%; margin: 20px 0"
      height="37vh"
      empty-text="您未发送留言"
    >
      <el-table-column
        prop="receiver.user.username"
        label="接收者"
        width="120"
        align="center"
      />
      <el-table-column
        prop="text"
        label="留言内容"
        width="500"
        align="center"
      />
      <el-table-column
        prop="time"
        label="留言时间"
        width="170"
        align="center"
      />
      <el-table-column fixed="right" label="管理" width="120" align="center">
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
    <el-table
      :data="receiveMessages"
      style="width: 100%"
      height="37vh"
      empty-text="您未收到留言"
    >
      <el-table-column
        prop="sender.user.username"
        label="留言者"
        width="120"
        align="center"
      />
      <el-table-column
        prop="text"
        label="留言内容"
        width="500"
        align="center"
      />
      <el-table-column
        prop="time"
        label="留言时间"
        width="170"
        align="center"
      />
      <el-table-column label="管理" width="120" align="center" fixed="right">
        <template #default="scope">
          <div v-if="isneedinfo[scope.$index] != 1">
            <el-button type="text" size="small" @click="needinfo(scope.$index)"
              >回复</el-button
            >
            <el-button
              type="text"
              size="small"
              @click="deleteMessage(scope.row.id)"
              style="color: red"
              >删除</el-button
            >
          </div>
          <div id="mmessage" v-else>
            <input
              type="text"
              v-model="message[scope.$index]"
              placeholder="请输入内容"
              style="width: 80px"
            />
            <button
              @click="
                sendMessage(
                  scope.row.sender.id,
                  message[scope.$index],
                  scope.$index
                )
              "
            >
              回复
            </button>
            <button @click="closeinfo(scope.$index)">关闭</button>
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, ref } from "vue";
import { useStore } from "vuex";
const usePersonalMessage = (store: any) => {
  const getMessages = () => {
    store.dispatch("getPersonalMessages");
  };
  const deleteMessage = (id: any) => {
    if (confirm("您确定要删除这个留言吗")) {
      store.dispatch("deletePersonalMessage", id);
      setTimeout(() => {
        getMessages();
      }, 100);
    }
  };
  return {
    getMessages,
    deleteMessage,
  };
};
export default defineComponent({
  setup() {
    const store = useStore();
    const isneedinfo = ref([]) as any;
    const message = ref([]) as any;
    const needinfo = (index: any) => {
      isneedinfo.value[index] = 1;
    };
    const closeinfo = (index: any) => {
      isneedinfo.value[index] = null;
      message.value[index] = "";
    };
    const sendMessage = (id: any, text: any, index: any) => {
      if (confirm("您确定要回复吗")) {
        if (text.trim() != "") {
          store.dispatch("sendMessage", {
            id,
            text,
          });
          setTimeout(() => {
            getMessages();
          }, 100);
          message.value[index] = "";
        } else store.commit("openDialog", "回复内容不可为空");
      }
    };
    const sendMessages = computed(() => store.state.sendMessages);
    const receiveMessages = computed(() => store.state.receiveMessages);
    const { getMessages, deleteMessage } = usePersonalMessage(store);
    getMessages();
    return {
      sendMessages,
      receiveMessages,
      deleteMessage,
      needinfo,
      closeinfo,
      message,
      sendMessage,
      isneedinfo,
    };
  },
});
</script>
<style scoped>
#mmessage button {
  border: 0.5px solid silver;
  color: gray;
  border-radius: 3px;
  background-color: white;
  cursor: pointer;
  font-size: 11px;
}
#mmessage button:hover {
  background-color: rgb(79, 166, 236);
  border: 0.5px solid rgb(79, 166, 236);
  color: white;
}
#mmessage button:focus {
  outline: none;
}
#mmessage input {
  font-size: 12px;
  color: gray;
  padding: 1px;
}
</style>
