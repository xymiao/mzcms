<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-button>
          <router-link to="/content_add">新增内容</router-link>
        </el-button>
      </div>
    </template>

    <el-row>
      <el-col :span="4">
        <el-tree
            :data="categoryList"
            node-key="categoryId"
            :props="defaultProps"
            @node-click="openCategoryData"
            :expand-on-click-node="false">
          <template #default="{ node, data }">
              <span class="custom-tree-node">
                <span>{{ node.data.name }}</span>
              </span>
          </template>
        </el-tree>
      </el-col>
      <el-col :span="20">
        <el-table :data="contentList" border style="width: 100%">
          <!--     <el-table-column prop="contentId" label="ID" width="80"></el-table-column>-->
          <el-table-column prop="title" label="内容"></el-table-column>
          <el-table-column prop="contentState" label="状态" width="120">
            <template #default="scope">
              <el-link :href="'http://127.0.0.1:8090/archives/'+ scope.row.contentId" target="_blank">访问</el-link>
            </template>
          </el-table-column>
          <el-table-column prop="address" label="操作" width="120">
            <template #default="scope">
              <el-button class="button btn-small" size="small">
                <router-link :to="{path:'/content_edit/' + scope.row.contentId}">编辑</router-link>
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              v-model:currentPage="currentPage1"
              :page-size="10"
              layout="total, prev, pager, next"
              :total="contentList.length"
          >
          </el-pagination>
        </div>
      </el-col>
    </el-row>
  </el-card>
  <el-backtop/>
</template>

<script lang="ts" setup>
import {ref} from 'vue';
import {listCategory} from "../../api/api_category";
import {listContent} from "../../api/api_content";

const dataSource = ref([]);
const categoryList = ref([]);
const contentList = ref([]);
const currentPage1 = ref(1);
const defaultProps = {
  children: 'cmsCategories',
  label: 'label',
};

const loadContentList = (cgId: string) => {
  let data = {categoryId: cgId};
  listContent(data).then((res) => {
    contentList.value = res.data;
  });
};

const loadCategoryList = () => {
  let data = {module: 'backend'};
  listCategory(data).then((res) => {
    categoryList.value = res.data;
    dataSource.value = JSON.parse(JSON.stringify(categoryList));
    loadContentList(res.data[0].categoryId);
  });
};

loadCategoryList();

const handleNodeClick = () => {

}
const handleSizeChange = () => {

}

const handleCurrentChange = () => {

}

interface Tree {
  label: string
  children?: Tree[]
}

const openCategoryData = (data: Tree) => {
  loadContentList(data.categoryId);
}

</script>

<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>