<script  >
export default {
  methods:{
    exitSystem(){
      this.$store.commit("userExit");
      this.$router.push("/login");
    }
  }
}
</script>

<template>
  <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
  >
    <el-menu-item index="1">工作台</el-menu-item>
    <el-sub-menu index="2">
      <template #title>内容管理</template>
      <el-menu-item index="2-1">选项1</el-menu-item>
      <el-menu-item index="2-2">选项2</el-menu-item>
      <el-menu-item index="2-3">选项3</el-menu-item>
      <el-sub-menu index="2-4">
        <template #title>选项4</template>
        <el-menu-item index="2-4-1">选项1</el-menu-item>
        <el-menu-item index="2-4-2">选项2</el-menu-item>
        <el-menu-item index="2-4-3">选项3</el-menu-item>
      </el-sub-menu>
    </el-sub-menu>
    <el-menu-item index="3" >系统管理</el-menu-item>
    <el-menu-item index="4" class="menu-right" @click="exitSystem">退出</el-menu-item>
  </el-menu>

</template>

<style scoped>
a {
  color: #42b983;
}
.menu-right{
  justify-content: right;
  margin-left: auto;
  width: 100px;
}
</style>
