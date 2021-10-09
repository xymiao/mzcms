<template>
  <el-skeleton :loading="loading" :animated="true" :throttle="500">
    <el-form ref="form" :model="form" label-width="120px" size="small">
      <el-form-item label="网站状态">
        <el-switch v-model="form.delivery"></el-switch>
      </el-form-item>
      <el-form-item
        :key="item.siteId"
        v-for="item in siteList"
        :label="item.title"
      >
        <el-input
          v-model="item.siteValue"
          :siteValue="item.siteValue"
          @change="changeProperties(item)"
        ></el-input>
      </el-form-item>
    </el-form>
  </el-skeleton>
</template>

<script  lang="ts">
import { list, put, edit, del } from "../api/api_site";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      loading: true,
      dialogImageUrl: "",
      dialogVisible: false,
      siteList: {},
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: true,
        type: [],
        resource: "",
        desc: "",
      },
    };
  },
  computed: {},
  created() {
    this.loadList();
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    },
    loadList() {
      let data = { module: "default" };
      list(data).then((res) => {
        console.log(res.data);
        this.siteList = res.data;
        console.log("site", this.siteList);
        this.loading = false;
      });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    changeProperties(info) {
      edit(info).then((res) => {
        ElMessage({
          message: res.message,
          type: "success",
        });
      });
    },
  },
};
</script>

<style>
</style>