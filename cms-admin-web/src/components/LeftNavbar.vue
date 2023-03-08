<template>
  <h1 class="logo">{{ LeftMenuStore.isCollapse ? "CMS" : "MzCMS" }}</h1>
  <el-menu
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      @select="handleSelect"
      :collapse="LeftMenuStore.isCollapse"
      :default-active="$route.path"
      :title="$route.path"
      :unique-opened="true"
  >
    <template v-for="menu in menuList" :key="menu.menuId">
      <el-menu-item index="/" v-if="menu.cmsMenus.length === 0">
        <el-icon>
          <Menu />
        </el-icon>
        <template #title>{{ menu.menuName }}</template>
      </el-menu-item>
      <el-sub-menu
          :index="menu.url == null ? '#' : menu.url"
          v-if="menu.cmsMenus.length > 0">
        <template #title>
          <el-icon>
            <component :is='Document'/>
          </el-icon>
          <span> {{ menu.menuName }}  {{menu.iconUrl}}</span>
        </template>
        <el-menu-item :index="subMenu.url == null ? '#' : subMenu.url"
                      v-for="subMenu in menu.cmsMenus">
          {{ subMenu.menuName }}
        </el-menu-item>
      </el-sub-menu>
    </template>
  </el-menu>
  <div style="position: fixed; bottom: 10px; left: 20px">
    <el-icon
        style="color: #333; font-size: 24px"
        @click="handleCollapse()">
      <fold v-if="!LeftMenuStore.isCollapse"/>
      <expand v-if="LeftMenuStore.isCollapse"/>
    </el-icon>
  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue';
import {useStore} from 'vuex';
import {useRouter} from 'vue-router';

import {showMenu } from "../api/api_menu";
import {LeftMenuStore} from '../store/left_menu';

import {Expand, Fold, Menu, Document, Tickets, User, Setting} from "@element-plus/icons";

const store = useStore();
const router = useRouter();
const menuList = ref([]);

const showMenuList = () => {
  let data = {module: "backend"};
  showMenu(data).then((res: any) => {
    console.log(res.data);
    menuList.value = res.data;
    if (res.data != null && res.data.length > 0) {
      store.commit("setLeftNavBarList", res.data);
    }
    console.log("menu", menuList);
  });
}

const handleSelect = (key: string, keyPath:string) => {
  router.push(key);
}
const handleCollapse = () => {
  LeftMenuStore.openHide();
}


const list = store.getters.getLeftNavBarList;
if (list != null && list.length > 0) {
  menuList.value = list;
} else {
  showMenuList();
}

</script>

<style scoped>
.logo {
  text-align: center;
  line-height: 60px;
  color: #fff;
  background-color: #545c64;
  border-right: 1px #fff solid;
}

.el-menu-vertical-demo {
  height: calc(100% - 60px);
  overflow-y: scroll;
}

.el-menu-vertical-demo::-webkit-scrollbar {
  display: none;
}
</style>



