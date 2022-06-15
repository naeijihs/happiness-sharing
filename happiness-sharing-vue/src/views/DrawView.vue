<template>
  <div style="margin-top: 20px" id="drawC">
    <canvas
      id="canvas"
      height="460"
      style="background-color: white; border: 1px solid silver"
    >
    </canvas>
    <div style="padding: 20px 0; display: flex; justify-content: space-around">
      <el-button
        class="button"
        type="text"
        style="font-size: 18px; color: black"
        >画笔</el-button
      >
      <el-button class="button" type="text" style="font-size: 18px; color: gray"
        >清除</el-button
      >
      <el-button class="button" type="text" style="font-size: 18px; color: "
        >完成</el-button
      >
      <el-button class="button" type="text" style="font-size: 18px; color: gray"
        >关闭</el-button
      >
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

export default defineComponent({
  setup() {
    const store = useStore();
    const router = useRouter();
    onMounted(() => {
      const canvas = document.getElementById("canvas");
      if (canvas != null) {
        var ctx = (canvas as HTMLCanvasElement).getContext("2d");
        const content = document.getElementById("drawC");
        if (content != null)
          (canvas as HTMLCanvasElement).width = content.clientWidth;
      }
      const btn = document.getElementsByTagName("button");
      btn[0].onclick = function () {
        if (canvas != null)
          canvas.onmousedown = function (e) {
            if (ctx != null) {
              ctx.beginPath();
              ctx.moveTo(e.offsetX, e.offsetY);
              canvas.onmousemove = function (e) {
                if (ctx != null) {
                  ctx.lineTo(e.offsetX, e.offsetY);
                  ctx.stroke();
                }
              };
            }
          };
      };
      if (canvas != null)
        canvas.onmouseup = function () {
          canvas.onmousemove = null;
        };
      btn[1].onclick = function () {
        if (ctx != null && canvas != null)
          ctx.clearRect(0, 0, (canvas as HTMLCanvasElement).width, 460);
      };
      btn[3].onclick = () => {
        router.push("/sharer/personalCenter/share/addShare");
      };
      btn[2].onclick = () => {
        store.commit(
          "src",
          (canvas as HTMLCanvasElement).toDataURL("image/png", 0.2)
        );
        router.push("/sharer/personalCenter/share/addShare");
      };
    });

    return {};
  },
});
</script>
