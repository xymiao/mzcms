<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>Card name</span>
          </div>
        </template>
        <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
      </el-card>
    </el-col>
    <el-col :span="12">内容</el-col>
    <el-col :span="6">属性</el-col>
  </el-row>
</template>

<script lang="ts" setup>

</script>

<style>
body{
  padding: 0;
  margin: 0 auto;
}
</style>