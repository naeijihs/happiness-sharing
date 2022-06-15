<template>
  <div>
    <el-table
      :data="comments"
      style="width: 100%"
      height="75vh"
      empty-text="您未评论"
    >
      <el-table-column
        prop="content"
        label="评论内容"
        width="300"
        align="center"
      />
      <el-table-column
        prop="share.text"
        label="分享内容"
        width="250"
        align="center"
      />
      <el-table-column
        prop="time"
        label="评论时间"
        width="170"
        align="center"
      />
      <el-table-column label="管理" width="120" align="center" fixed="right">
        <template #default="scope">
          <el-button
            type="text"
            size="small"
            @click="deleteComment(scope.row.id)"
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
const usePersonalComment = (store: any) => {
  const getPersonalComments = () => {
    store.dispatch("getPersonalComments");
  };
  const deleteComment = (id: any) => {
    if (confirm("您确定要删除这个评论吗")) {
      store.dispatch("deletePersonalComment", id);
      setTimeout(() => {
        getPersonalComments();
      }, 100);
    }
  };
  return {
    getPersonalComments,
    deleteComment,
  };
};
export default defineComponent({
  setup() {
    const store = useStore();
    const comments = computed(() => store.state.personalComments);
    const { getPersonalComments, deleteComment } = usePersonalComment(store);
    getPersonalComments();
    return {
      comments,
      deleteComment,
    };
  },
});
</script>
