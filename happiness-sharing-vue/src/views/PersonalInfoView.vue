<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span style="color: gray; font-size: 17px; font-weight: bolder"
          >个人信息</span
        >
      </div>
    </template>
    <div class="text item">
      <div class="title">姓名</div>
      <el-input v-model="personalInfo.name" placeholder="姓名" clearable />
    </div>
    <div class="text item">
      <div class="title">年龄</div>
      <el-input-number
        v-model="personalInfo.age"
        placeholder="年龄"
        clearable
      />
    </div>
    <div class="text item">
      <div class="title">性别</div>
      <el-input v-model="personalInfo.sex" placeholder="性别" clearable />
    </div>
    <div class="text item">
      <div class="title">省份</div>
      <el-input v-model="personalInfo.province" placeholder="省份" clearable />
    </div>
    <div class="text item">
      <div class="title">联系方式</div>
      <el-input
        v-model="personalInfo.contact"
        placeholder="联系方式"
        clearable
      />
    </div>
    <el-button
      class="button"
      type="text"
      @click="modifyPersonalInfo(personalInfo)"
      style="color: darkseagreen; font-size: 19px"
      >更新</el-button
    >
  </el-card>
</template>

<script lang="ts">
import { computed, defineComponent } from "vue";
import { useStore } from "vuex";
const usePersonalInfo = (store: any) => {
  const getPersonalInfo = () => {
    store.dispatch("getPersonalInfo");
  };
  const modifyPersonalInfo = (personalInfo: any) => {
    store.dispatch("modifyPersonalInfo", personalInfo);
  };
  return { getPersonalInfo, modifyPersonalInfo };
};
export default defineComponent({
  setup() {
    const store = useStore();
    const { getPersonalInfo, modifyPersonalInfo } = usePersonalInfo(store);
    const personalInfo = computed(() => store.state.personalInfo);
    getPersonalInfo();
    return {
      personalInfo,
      modifyPersonalInfo,
    };
  },
});
</script>
<style scoped>
.card-header {
  display: flex;
  justify-content: center;
  align-items: center;
}
.item {
  padding: 3px 0 13px 0;
  width: 40vw;
  margin: 0 auto;
}
.text {
  font-size: 14px;
}
.title {
  font-size: 16px;
  color: gray;
  margin-bottom: 10px;
}
.box-card {
  margin-top: 20px;
}
</style>
