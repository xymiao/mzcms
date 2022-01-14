<template>
  <div ref="editor"></div>
</template>

<script lang="ts">
import { onMounted, onBeforeUnmount, ref, reactive, inject, watch } from "vue";
import WangEditor from "wangeditor";
export default {
  name: "app",
  setup() {
    const editType = inject("editType");
    const editContentOld = inject("editContentOld");
    const updateEditContent = inject("updateEditContent");

    const editor = ref();
    let countNum = 0;
    let instance;
    onMounted(() => {
      instance = new WangEditor(editor.value);
      instance.config.zIndex = 100;
      instance.config.showFullScreen = true;
      instance.config.height = 500;
      Object.assign(instance.config, {
        onchange() {
          console.log("change");
          updateEditContent(instance.txt.html());
        },
      });
      instance.create();
    });

    watch(editContentOld, (newValue, oldValue) => {
        instance.txt.html(newValue);
    });

    onBeforeUnmount(() => {
      instance.destroy();
      instance = null;
    });

    return {
      editor,
    };
  },
};
</script>