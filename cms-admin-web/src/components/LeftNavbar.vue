<template>
  <h1 class="logo">{{ isCollapse ? "CMS" : "MzCMS" }}</h1>
  <el-menu
    class="el-menu-vertical-demo"
    @select="handleSelect"
    :collapse="isCollapse"
    :default-active="$route.path"
    :title="$route.path"
    :unique-opened="true"
  >
    <template v-for="menu in menuList" :key="menu.menuId">
      <el-menu-item index="/" v-if="menu.cmsMenus.length === 0">
        <el-icon>
          <menu-icon />
        </el-icon>
        <template #title>{{ menu.menuName }}</template>
      </el-menu-item>
      <el-sub-menu
        :index="menu.url == null ? '#' : menu.url"
        v-if="menu.cmsMenus.length > 0"
      >
        <template #title>
          <el-icon>
            <location />
          </el-icon>
          <span>{{ menu.menuName }}</span>
        </template>
        <el-menu-item
          :index="subMenu.url == null ? '#' : subMenu.url"
          v-for="subMenu in menu.cmsMenus"
        >
          {{ subMenu.menuName }}
        </el-menu-item>
      </el-sub-menu>
    </template>
  </el-menu>
  <div style="position: fixed; bottom: 10px; left: 20px">
    <el-icon
      style="color: #333; font-size: 24px"
      v-if="!isCollapse"
      @click="handleCollapse(true)"
    >
      <fold />
    </el-icon>
    <el-icon
      style="color: #333; font-size: 24px"
      v-if="isCollapse"
      @click="handleCollapse(false)"
    >
      <expand />
    </el-icon>
  </div>
</template>

<script lang="ts">
import { showMenu } from "../api/api_menu";
import { Menu, Edit, Location, Fold, Expand } from "@element-plus/icons";

export default {
  components: {
    Edit,
    MenuIcon: Menu,
    Location,
    Fold,
    Expand,
  },
  data() {
    return {
      menuList: [],
      isCollapse: this.$store.getters.isCollapse,
    };
  },
  computed: {},
  created() {
    const list = this.$store.getters.getLeftNavBarList;
    if( list != null && list.length > 0){
      this.menuList = list;
    }else{
      this.showMenuList();
    }
  },
  methods: {
    showMenuList() {
      let data = { module: "backend" };
      showMenu(data).then((res) => {
        console.log(res.data);
        this.menuList = res.data;
        if(res.data != null && res.data.length > 0){
           this.$store.commit("setLeftNavBarList", res.data);
        }
        console.log("menu", this.menuList);
      });
    },
    handleSelect(key, keyPath) {
      //console.log(key, keyPath);
      this.$router.push(key);
    },
    handleCollapse(value) {
      //console.log(value);
      this.isCollapse = value;
      this.$store.commit("useMenuCollapse", value);
    },
  },
};
</script>

<style scoped>
.logo {
  text-align: center;
  line-height: 60px;
  color: #000;
}

.el-menu-vertical-demo {
  height: calc(100% - 60px);
  overflow-y: scroll;
}

.el-menu-vertical-demo::-webkit-scrollbar {
  display: none;
}
</style>



