<script setup>

</script>

<template>
  <h1 style="text-align: center; line-height: 60px;background-color: #393939; color: #fff;">LOGO</h1>
  <el-menu
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#393939"
      text-color="#fff"
      active-text-color="#ffd04b">
    <el-sub-menu index="1">
      <template #title>
        <i class="el-icon-location"></i>
        <span>内容管理</span>
      </template>
      <el-menu-item-group>
        <template #title>分组1</template>
        <el-menu-item index="1-1">内容管理</el-menu-item>
        <el-menu-item index="1-2">分类管理</el-menu-item>
        <el-menu-item index="1-1">内容回收站</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="分组2">
        <el-menu-item index="1-3">评论管理</el-menu-item>
        <el-menu-item index="1-3">标签管理</el-menu-item>
      </el-menu-item-group>
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
      <el-menu-item index="1-1">站点配置</el-menu-item>
      <el-menu-item index="1-1">数据字典</el-menu-item>
      <el-menu-item index="1-1">权限管理</el-menu-item>
      <el-menu-item index="1-1">角色管理</el-menu-item>
      <el-menu-item index="1-1">日志管理</el-menu-item>
    </el-sub-menu>
  </el-menu>

</template>

<style scoped>
.el-menu-vertical-demo{
  height: calc(100% - 60px);
}

</style>



