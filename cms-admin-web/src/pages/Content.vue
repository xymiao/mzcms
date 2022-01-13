<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-button class="button" type="text" ><router-link to="/content_add">新增内容</router-link></el-button>
      </div>
    </template>

    <el-row>
      <el-col :span="4">
        <el-tree
            :data="dataSource"
            node-key="categoryId"
            :props="defaultProps"
            :expand-on-click-node="false"
        >
          <template #default="{ node, data }">
        <span class="custom-tree-node">
          <span>{{ node.data.name }}</span>
        </span>
          </template>
        </el-tree>
      </el-col>
      <el-col :span="20">
        <el-table :data="contentList" border style="width: 100%">
          <el-table-column prop="contentId" label="ID" width="80"></el-table-column>
          <el-table-column prop="title" label="内容" ></el-table-column>
<!--          <el-table-column prop="viewNum" label="浏览"></el-table-column>
          <el-table-column prop="commentNum" label="评论数"></el-table-column>
          <el-table-column prop="viewNum" label="点击数"></el-table-column>
          <el-table-column prop="created" label="发布日期"></el-table-column>
          <el-table-column prop="address" label="置顶"></el-table-column>-->
          <el-table-column prop="contentState" label="状态"  width="120"><el-link >访问</el-link></el-table-column>
          <el-table-column prop="address" label="操作"  width="120">
            <template #default="scope">
            <el-button class="button btn-small" size="mini">
              <router-link :to="{path:'/content_edit/' + scope.row.contentId}">编辑</router-link></el-button>
         </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              v-model:currentPage="currentPage1"
              :page-size="100"
              layout="total, prev, pager, next"
              :total="1000"
          >
          </el-pagination>
        </div>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
import {listCategory} from "../api/api_category";
import {listContent} from "../api/api_content";

export default {
  data() {
    return {
      categoryList: [],
      defaultProps: {
        children: 'cmsCategories',
        label: 'label',
      },
      categoryId: '',
      contentList: [],
    }
  },
  created() {
    this.loadCategoryList();
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
    loadContentList() {
      let data = {categoryId: this.categoryId};
      listContent(data).then((res) => {
        console.log(res.data);
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