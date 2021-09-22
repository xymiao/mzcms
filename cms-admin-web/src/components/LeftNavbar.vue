<script>
export default {
  data() {
    return {}
  },
  computed: {
    isCollapse() {
      return this.$store.getters.isCollapse;
    }
  },
  methods: {

    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.$router.push(key);
    },
    handleCollapse(value) {
      this.$store.commit("useMenuCollapse", value);

    }
  }
}
</script>

<template>
  <h1 style="text-align: center; line-height: 60px;background-color: #393939; color: #fff;">LOGO</h1>
  <el-menu
      class="el-menu-vertical-demo"
      @select="handleSelect"
      :collapse="isCollapse"
      :default-active="$route.path"
      :unique-opened="true"
      background-color="#393939"
      text-color="#fff"
      active-text-color="#ffd04b">
    <el-menu-item index="/">
      <i class="el-icon-menu"></i>
      <template #title >工作台</template>
    </el-menu-item>
    <el-sub-menu index="/content_home">
      <template #title>
        <i class="el-icon-location"></i>
        <span>内容管理</span>
      </template>
      <el-menu-item index="/content">内容管理</el-menu-item>
      <el-menu-item index="/content_category">分类管理</el-menu-item>
      <el-menu-item index="1-1">内容回收站</el-menu-item>
      <el-menu-item index="1-3">评论管理</el-menu-item>
      <el-menu-item index="1-3">标签管理</el-menu-item>
    </el-sub-menu>
    <el-menu-item index="2">
      <i class="el-icon-menu"></i>
      <template #title>页面管理</template>
    </el-menu-item>
    <el-menu-item index="3">
      <i class="el-icon-document"></i>
      <template #title>用户管理</template>
    </el-menu-item>
    <el-menu-item index="4">
      <i class="el-icon-setting"></i>
      <template #title>用户管理</template>
    </el-menu-item>
    <el-sub-menu index="5">
      <template #title>
        <i class="el-icon-location"></i>
        <span>系统配置</span>
      </template>
      <el-menu-item index="1-1">站点配置</el-menu-item>
      <el-menu-item index="1-1">数据字典</el-menu-item>
      <el-menu-item index="1-1">权限管理</el-menu-item>
      <el-menu-item index="1-1">角色管理</el-menu-item>
      <el-menu-item index="1-1">日志管理</el-menu-item>
      <el-menu-item index="/sys_menu">菜单管理</el-menu-item>
    </el-sub-menu>
  </el-menu>
  <div style="position: fixed; bottom: 10px; left: 20px;">
    <i class="el-icon-s-fold" v-if="!isCollapse" style="color: #fff; font-size: 24px;"
       @click="handleCollapse(true)"></i>
    <i class="el-icon-s-unfold" v-if="isCollapse" style="color: #fff; font-size: 24px;"
       @click="handleCollapse(false)"></i>
  </div>
</template>

<style scoped>
.el-menu-vertical-demo {
  height: calc(100% - 60px);
}
</style>



