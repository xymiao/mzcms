<template>
  <el-container>
    <el-aside :class="isCollapse ? 'cms-aside-collapse' : 'cms-aside'">
      <left-navbar></left-navbar>
    </el-aside>
    <el-container :class=" isCollapse ? 'cms-main-collapse': 'cms-main'">
      <el-header class="cms-header">
        <cms-header></cms-header>
      </el-header>
      <el-main class="cms-content">
        <router-view></router-view>
      </el-main>
      <el-footer style="text-align: center;font-size: 12px; ">Copyright © 2021  MzCMS </el-footer>
    </el-container>
  </el-container>
</template>

<script >
import CmsHeader from '../components/CmsHeader.vue'
import LeftNavbar from "../components/LeftNavbar.vue";
export default {
  components:{
    CmsHeader,
    LeftNavbar,
  },
  computed: {
      isCollapse(){
       return this.$store.getters.isCollapse;
      }
  }
}
</script>

<style>
*{
  padding: 0;
  margin: 0;
}
html, body, #app{
  height: 100%;
}
.cms-header{
  --el-header-padding: 0 !important;
}
.cms-content{
  min-height: 500px;
}
.cms-aside{
  width:240px !important;
  position: fixed;
  height: 100%;
}
.cms-aside-collapse{
  width: 64px !important;
  position: fixed;
  height: 100%;
}
.cms-main{
  margin-left: 240px;
}
.cms-main-collapse{
  margin-left: 64px;
}
</style>
