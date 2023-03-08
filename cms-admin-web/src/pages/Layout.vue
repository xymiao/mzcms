<template>
  <el-container>
    <el-aside :class="LeftMenuStore.isCollapse ? 'cms-aside-collapse' : 'cms-aside'">
      <left-navbar></left-navbar>
    </el-aside>
    <el-container :class="LeftMenuStore.isCollapse ? 'cms-main-collapse' : 'cms-main'">
      <el-header class="cms-header">
        <cms-header></cms-header>
      </el-header>
      <el-main class="cms-content">
        <router-view></router-view>
      </el-main>
      <el-footer style="text-align: center; font-size: 12px"
      >Copyright © {{ getYear() }} MzCMS {{LeftMenuStore.isCollapse}}
      </el-footer>
    </el-container>
  </el-container>
</template>

<script lang="ts"  setup>
import {ref} from 'vue';
import CmsHeader from "../components/CmsHeader.vue";
import LeftNavbar from "../components/LeftNavbar.vue";
import {LeftMenuStore} from '../store/left_menu';

const getYear = () => {
  return new Date().getFullYear();
}
</script>

<style>
* {
  padding: 0;
  margin: 0;
}

html,
body,
#app {
  height: 100%;
}

.cms-header {
  --el-header-padding: 0 !important;
}

.cms-content {
  min-height: 500px;
}

.cms-aside {
  width: 240px !important;
  position: fixed;
  height: 100%;
}

.cms-aside-collapse {
  width: 64px !important;
  position: fixed;
  height: 100%;
}

.cms-main {
  margin-left: 240px;
}

.cms-main-collapse {
  margin-left: 64px;
}
</style>
