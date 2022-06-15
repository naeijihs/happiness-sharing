<template>
  <div>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span style="color: gray; font-size: 18px">聊天室</span>
          <el-button
            type="text"
            class="button"
            style="font-size: 16px; color: red"
            @click="closeCommunication"
            >关闭</el-button
          >
        </div>
      </template>
      <div style="height: 450px; overflow: auto" id="scroll">
        <div
          v-for="(communication, index) in communications"
          :key="index"
          style="
            color: gray;
            padding-left: 24px;
            text-align: left;
            margin: 0 0 10px 0;
          "
        >
          <div style="padding: 0 0 5px 0">
            {{ communication.communicator.user.username
            }}<span style="margin-left: 14px">{{ communication.time }}</span>
          </div>
          <div tyle="padding: 0 0 5px 0">{{ communication.content }}</div>
        </div>
      </div>
      <div style="padding: 14px 14px 0 14px">
        <div class="bottom" style="display: flex">
          <el-input
            v-model="content"
            maxlength="500"
            placeholder="聊天内容"
            show-word-limit
            type="text"
            autofocus
          />
          <div style="padding-left: 10px">
            <el-button
              type="text"
              class="button"
              style="font-size: 16px"
              @click="communicate"
              >发送</el-button
            >
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>
<script lang="ts">
import { computed, defineComponent, onBeforeUnmount, ref } from "vue";
import { useStore } from "vuex";
import { EventSourcePolyfill } from "event-source-polyfill";
import { useRouter } from "vue-router";
import { dateFormat } from "../util/dateUtil";

export default defineComponent({
  setup() {
    const content = ref("");
    const store = useStore();
    const router = useRouter();
    const communications = computed(() => store.state.communications);
    const communicate = () => {
      store.dispatch("communicate", {
        content: content.value,
        time: dateFormat("YYYY-mm-dd HH:MM", new Date()),
      });
      content.value = "";
    };
    const closeCommunication = () => {
      router.push("/");
    };
    onBeforeUnmount(() => {
      store.dispatch("closeCommunication");
      eventSource.close();
    });
    const eventSource = new EventSourcePolyfill(
      "http://localhost:8080/sharer/communication/create",
      {
        headers: {
          authorization: sessionStorage.getItem("auth"),
        },
      }
    );
    eventSource.onmessage = (event: any) => {
      store.commit("timeCommunications", JSON.parse(event.data));
      setTimeout(() => {
        const div = document.getElementById("scroll");
        if (div != null) {
          div.scrollTop = div.scrollHeight;
        }
      }, 60);
    };
    return {
      content,
      communications,
      communicate,
      closeCommunication,
    };
  },
});
</script>
<style scoped>
.card-header {
  padding: 0 15px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}
.box-card {
  margin: 25px 0 0 0;
}
</style>
