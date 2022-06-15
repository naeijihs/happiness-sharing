<template>
  <div>
    <el-table
      :data="comments"
      style="width: 100%; font-size: 17px"
      empty-text="暂无评论"
    >
      <el-table-column
        prop="commenter.user.username"
        label="评论者"
        width="200"
        align="center"
      />
      <el-table-column
        prop="content"
        label="评论内容"
        width="700"
        align="center"
      />
      <el-table-column
        prop="time"
        label="评论时间"
        width="300"
        align="center"
      />
      <el-table-column fixed="right" label="操作" width="120" align="center">
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
export default defineComponent({
  setup() {
    const store = useStore();
    const comments = computed(() => store.state.comments);
    const getComments = () => {
      store.dispatch("getComments");
    };
    const deleteComment = (id: any) => {
      if (confirm("您确认要删除这个评论吗")) {
        store.dispatch("deleteComment", id);
        setTimeout(() => {
          getComments();
        }, 100);
      }
    };
    getComments();
    return {
      comments,
      deleteComment,
    };
  },
});
</script>
