<template>
  <div>
    <el-table
      :data="sharers"
      style="width: 100%; font-size: 17px"
      empty-text="暂无分享者"
    >
      <el-table-column
        prop="user.username"
        label="用户名"
        width="200"
        align="center"
      />
      <el-table-column prop="name" label="姓名" width="100" align="center" />
      <el-table-column prop="sex" label="性别" width="200" align="center" />
      <el-table-column prop="age" label="年龄" width="100" align="center" />
      <el-table-column
        prop="contact"
        label="联系方式"
        width="300"
        align="center"
      />
      <el-table-column
        prop="province"
        label="省份"
        width="300"
        align="center"
      />
      <el-table-column fixed="right" label="操作" width="120" align="center">
        <template #default="scope">
          <el-button
            type="text"
            size="small"
            @click="deleteSharer(scope.row.id)"
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
    const sharers = computed(() => store.state.sharers);
    const getSharers = () => {
      store.dispatch("getSharers");
    };
    const deleteSharer = (id: any) => {
      if (confirm("您确认要删除这位分享者吗")) {
        store.dispatch("deleteSharer", id);
        setTimeout(() => {
          getSharers();
        }, 100);
      }
    };
    getSharers();
    return {
      sharers,
      deleteSharer,
    };
  },
});
</script>
