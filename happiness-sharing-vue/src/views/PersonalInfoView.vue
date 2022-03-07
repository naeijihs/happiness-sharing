<template>
  <div>
    name:<input type="text" v-model="personalInfo.name" /> age:<input
      type="text"
      v-model="personalInfo.age"
    />
    sex:<input type="text" v-model="personalInfo.sex" /> province:<input
      type="text"
      v-model="personalInfo.province"
    />
    contact:<input type="text" v-model="personalInfo.contact" />
    <button @click="modifyPersonalInfo(personalInfo)">modify</button>
  </div>
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
