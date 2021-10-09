<template>
  <el-tabs v-model="activeName" @tab-click="handleClick" type="border-card">
    <el-tab-pane label="网站设置" name="site_setting">
      <setting-site></setting-site>
    </el-tab-pane>
    <el-tab-pane label="附件设置" name="annex_setting"
      ><el-empty description="附件设置暂无"></el-empty
    ></el-tab-pane>
    <el-tab-pane label="其他" name="other_setting"
      ><el-empty description="其他暂无"></el-empty
    ></el-tab-pane>
  </el-tabs>
</template>

<script>
import SettingSite from "./SettingSite.vue";
export default {
  components: {
    SettingSite,
  },
  data() {
    return {
      activeName: "site_setting",
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
};
</script>

<style>
</style>