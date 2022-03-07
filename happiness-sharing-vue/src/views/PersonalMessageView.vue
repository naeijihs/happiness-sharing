<template>
  <div>
    <div v-for="(s, i) in sendMessages" :key="i">
      发送的留言{{ s.text }} 时间{{ s.time }} 接受者{{
        s.receiver.user.username
      }}
      <button @click="deleteMessage(s.id)">delete</button>
    </div>
    <div v-for="(r, index) in receiveMessages" :key="index">
      收到的留言{{ r.text }} 时间{{ r.time }} 发送者{{ r.sender.user.username }}
      <button @click="deleteMessage(r.id)">delete</button>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, inject } from "vue";
import { useStore } from "vuex";
const usePersonalMessage = (store: any, refresh: any) => {
  const getMessages = () => {
    store.dispatch("getMessages");
  };
  const deleteMessage = (id: any) => {
    if (confirm("您确定要删除这个留言吗")) {
      store.dispatch("deleteMessage", id);
      refresh();
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
    const refresh = inject("refresh");
    const sendMessages = computed(() => store.state.sendMessages);
    const receiveMessages = computed(() => store.state.receiveMessages);
    const { getMessages, deleteMessage } = usePersonalMessage(store, refresh);
    getMessages();
    return {
      sendMessages,
      receiveMessages,
      deleteMessage,
    };
  },
});
</script>
