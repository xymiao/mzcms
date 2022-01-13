<template>
  <div ref="editor"></div>
  <div @click="syncHTML">同步内容</div>
</template>

<script lang="ts">
import {
  onMounted,
  onBeforeUnmount,
  ref,
  reactive,
  provide,
  inject,
} from "vue";
import WangEditor from "wangeditor";
export default {
  name: "app",
  setup() {
    const updateEditContent = inject('updateEditContent')
   
    const editor = ref();
    const content = reactive({
      html: "",
      text: "",
    });
    let instance;
    onMounted(() => {
      instance = new WangEditor(editor.value);
      Object.assign(instance.config, {
        onchange() {
          console.log("change");
          content.html = instance.txt.html();
          updateEditContent(content.html);
        },
      });
      instance.create();
    });
    onBeforeUnmount(() => {
      instance.destroy();
      instance = null;
    });
    const syncHTML = () => {
      content.html = instance.txt.html();
    };
  
    return {
      syncHTML,
      editor,
      content
    };
  },
};
</script>