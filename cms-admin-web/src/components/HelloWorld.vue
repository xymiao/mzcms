<script setup lang="ts">
import { ref } from "vue";

defineProps<{ msg: string }>();

const count = ref(0);
</script>

<template>
  <h1>{{ msg }}</h1>

  <el-button icon="el-icon-success" type="primary" @click="count++"
    >count is: {{ count }}</el-button
  >

  <p>
MZCMS后端管理初始化项目
  </p>
</template>

<style scoped>
a {
  color: #42b983;
}
</style>
