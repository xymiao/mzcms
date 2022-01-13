<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>内容编辑{{ $route.params.id }}</el-breadcrumb-item>
        </el-breadcrumb>
        <el-button class="button" type="text">
          <router-link to="/content_manage">返回</router-link>
        </el-button>
      </div>
    </template>

    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="基础内容" name="first">
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="标题">
            <el-input v-model="contentInfo.title"></el-input>
          </el-form-item>
          <el-form-item label="所属分类">
            <el-select v-model="form.region" placeholder="请选择分类">
              <el-option label="Zone one" value="shanghai"></el-option>
              <el-option label="Zone two" value="beijing"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="内容">
            <WangEditorEdit></WangEditorEdit>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">提交内容</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="SEO选项" name="second">
        <el-form
            :label-position="labelPosition"
            label-width="100px"
            :model="formLabelAlign"
        >
          <el-form-item label="SEO标题">
            <el-input v-model="formLabelAlign.name"></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字">
            <el-input v-model="formLabelAlign.region"></el-input>
          </el-form-item>
          <el-form-item label="SEO描述">
            <el-input v-model="formLabelAlign.type"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="其他设置" name="third">Role</el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script lang="ts">
import {listCategory} from "../api/api_category";
import {getContent, listContent} from "../api/api_content";
import WangEditorEdit from "./WangEditorEdit.vue"

export default {
  components: {WangEditorEdit},
  data() {
    return {
      contentId: '',
      contentInfo: {},
      categoryList: [],
      activeName: 'first',
      defaultProps: {
        children: 'cmsCategories',
        label: 'label',
      },
      categoryId: '',
      contentList: [],
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: '',
      },
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: '',
      },
    }
  },
  created() {
    this.contentId = this.$route.params.id; 
    this.loadContentById();
  },
  computed: {
    dataSource() {
      return JSON.parse(JSON.stringify(this.categoryList));
    },
  },
  methods: {
    goBack() {
      console.log("go back")
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    onSubmit() {
      console.log('submit!')
    },
    loadContentById() {
      let data = {contentId: this.contentId};
      getContent(data).then((res) => {
        this.contentInfo = res.data;
        console.log("contentInfo", this.contentInfo);
      });
    },
    loadContentList() {
      let data = {categoryId: this.categoryId};
      listContent(data).then((res) => {
        this.contentList = res.data;
        console.log("category", this.categoryList);
      });
    },
    loadCategoryList() {
      let data = {module: 'backend'};
      listCategory(data).then((res) => {
        console.log(res.data);
        this.categoryList = res.data;
        this.categoryId = this.categoryList[0].categoryId;
        console.log("category", this.categoryList);
        this.loadContentList();
      });
    },
    handleNodeClick(data) {
      console.log(data)
    },
  }
}
</script>

<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>