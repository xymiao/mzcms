<script>
import {showMenu} from "../api/api_menu";

export default {
  data() {
    return {
      menuList: [],
    }
  },
  computed: {
    isCollapse() {
      return this.$store.getters.isCollapse;
    }
  },
  created() {
    this.showMenuList();
  },
  methods: {
    showMenuList() {
      let data = {module: 'backend'};
      showMenu(data).then(res => {
        console.log(res.data);
        this.menuList = res.data;
        console.log("menu", this.menuList)
      });
    },
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
    <template v-for="menu in menuList" :key="menu.menuId">
      <el-menu-item index="/" v-if="menu.cmsMenus.length === 0">
        <i class="el-icon-menu"></i>
        <template #title>{{ menu.menuName }}</template>
      </el-menu-item>
      <el-sub-menu :index="menu.url == null ? '#' : menu.url" v-if="menu.cmsMenus.length > 0">
        <template #title>
          <i class="el-icon-location"></i>
          <span>{{ menu.menuName }}</span>
        </template>
        <el-menu-item :index="subMenu.url == null ? '#' : subMenu.url" v-for="subMenu in menu.cmsMenus">{{ subMenu.menuName }}</el-menu-item>
      </el-sub-menu>
    </template>
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



