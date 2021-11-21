<script  lang="ts">
export default {
  methods:{
    exitSystem(){
      this.$store.commit("userExit");
      this.$router.push("/login");
    },
    handleSelect(key, keyPath){
      this.$router.push(key);
      console.log(keyPath);
    }
  }
}
</script>

<template>
  <el-menu
      :default-active="$route.path"
      :unique-opened = true
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
  >
    <el-menu-item index="/">工作台</el-menu-item>
    <el-sub-menu index="2" class="menu-right" >
      <template #title>我的</template>
      <el-menu-item index="2-1">主站预览</el-menu-item>
      <el-menu-item index="2-2">个人设置</el-menu-item>
      <el-menu-item index="2-3" @click="exitSystem">退出</el-menu-item>
    </el-sub-menu>
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
