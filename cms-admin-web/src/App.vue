<template>
<el-container>
    <el-aside class="cms-aside">
      <left-navbar></left-navbar>
    </el-aside>
    <el-container class="cms-main">
      <el-header>
        <cms-header></cms-header>
      </el-header>
      <el-main class="cms-content">
      <div  style="text-align: center; padding-top: 100px; font-size: 30px;">静态模版搭建初始化</div>
      </el-main>
      <el-footer style="text-align: center;font-size: 14px; ">Copyright © 2021  MzCMS </el-footer>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import CmsHeader from './components/CmsHeader.vue'
import LeftNavbar from "./components/LeftNavbar.vue";
</script>

<style>
*{
  padding: 0;
  margin: 0;
}
html, body, #app{
  height: 100%;
}
.el-header{
  --el-header-padding: 0;
}
.cms-content{
  min-height: 500px;
}
.cms-aside{
  width:240px;
  position: fixed;
  height: 100%;
}
.cms-main{
  margin-left: 240px;
}
</style>
