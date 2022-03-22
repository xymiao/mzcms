<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>内容分类管理</span>
        <el-button class="button" type="text" @click="openAdd('parent')"
        >新增类别
        </el-button>
      </div>
    </template>
{{categoryList}}
    <el-tree :data="categoryList" :props="defaultProps" @node-click="handleNodeClick"/>

    <el-tree
        :data="categoryList"
        node-key="categoryId"
        :props="defaultProps"
        default-expand-all
        :expand-on-click-node="false">
      <template #default="{ node, data }">
        <span class="custom-tree-node">
          <span>{{ node.data.name }} - {{ node.data.url }}</span>
          <span>
            <span>{{ node.data.sort }}</span>
            &nbsp;  &nbsp;
              <el-icon>
                <template v-if="node.data.status == '1'">
                  <Open/>
                </template>
                 <template v-if="!node.data.status ||  node.data.status == '0'">
                  <CircleClose/>
                </template>
            </el-icon>
                &nbsp;&nbsp;
            <el-icon @click="openEdit(node)"><Edit/></el-icon>
                &nbsp;&nbsp;
            <el-icon @click="openAdd('parent',node)">
              <Plus/>
            </el-icon>
            &nbsp;&nbsp;
             <el-popconfirm title="确认要删除该类别吗?" confirmButtonText="删除" cancelButtonText="取消"
                            @confirm="remove(node, data)">
              <template #reference>
                <el-icon>
                        <Delete/>
                      </el-icon>
              </template>
            </el-popconfirm>
          </span>
        </span>
      </template>
    </el-tree>
  </el-card>
  <category-add :add-info="addInfo" :form="form" @refresh="refreshData"></category-add>
  <category-edit :edit-info="editInfo" :edit-form="editForm" @refresh="refreshData"></category-edit>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import {delCategory, listCategory} from "../api/api_category";
import CategoryAdd from "./CategoryAdd.vue";
import CategoryEdit from "./CategoryEdit.vue";
import {CircleClose, Delete, Edit, Open, Plus} from "@element-plus/icons";
import {ElMessage} from 'element-plus'

interface Tree {
  label: string
  children?: Tree[]
}

const handleNodeClick = (data: Tree) => {
  console.log(data)
}

const data: Tree[] = [
  {
    label: 'Level one 1',
    children: [
      {
        label: 'Level two 1-1',
        children: [
          {
            label: 'Level three 1-1-1',
          },
        ],
      },
    ],
  },
  {
    label: 'Level one 2',
    children: [
      {
        label: 'Level two 2-1',
        children: [
          {
            label: 'Level three 2-1-1',
          },
        ],
      },
      {
        label: 'Level two 2-2',
        children: [
          {
            label: 'Level three 2-2-1',
          },
        ],
      },
    ],
  },
  {
    label: 'Level one 3',
    children: [
      {
        label: 'Level two 3-1',
        children: [
          {
            label: 'Level three 3-1-1',
          },
        ],
      },
      {
        label: 'Level two 3-2',
        children: [
          {
            label: 'Level three 3-2-1',
          },
        ],
      },
    ],
  },
]


let id = 1000;
//export default {
/*
data()
{
  return {
    addInfo: {title: "新增分类", addVisible: false, modal: true},
    editInfo: {title: "修改分类", addVisible: false, modal: true},
    categoryList: [],
    defaultProps: {
      children: "cmsCategories",
      label: "name",
    },
    form: {parentId: "parent", moduleId: 'backend'},
    editForm: {},
  };
}
*/
const addInfo = {title: "新增分类", addVisible: false, modal: true};
const editInfo = {title: "修改分类", addVisible: false, modal: true};
/*const defaultProps = {
  children: 'children',
  label: 'label',
}
*/
const defaultProps = {
  children: "cmsCategories",
  label: "name",
};


const form = {parentId: "parent", moduleId: 'backend'};
const editForm = {};
//methods: {
let categoryList = ref([]);
const loadCategoryList = () => {
  let data = {module: 'backend'};
  listCategory(data).then((res) => {
    console.log(res.data);
    categoryList = res.data;
    console.log("category", categoryList);
  });
};
loadCategoryList();
const openAdd = (parent, node) => {
  addInfo.addVisible = true;
  form.moduleId = 'backend';
  if (node) {
    addInfo.title = "添加 " + node.data.name + " 子类别"
    form.parentId = node.data.categoryId;
    console.log(parent, node.data);
  } else {
    addInfo.title = "添加类别"
    form.parentId = "parent";
  }
};
const openEdit = (node) => {
  console.log("openEdit: ", node.data);
  editInfo.addVisible = true;
  editForm = node.data;
};
const remove = (node, data) => {
  console.log("node", node);
  delCategory({categoryId: node.data.categoryId}).then((res) => {
    console.log(res)
    if (res.rcode == 0) {
      ElMessage({
        message: res.message || '删除成功',
        type: 'success',
      });
      loadCategoryList();
    } else {
      ElMessage.error(res.message);
    }
  });
};
const refreshData = () => {
  loadCategoryList();
};
//},
//};
</script>
<style>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}

.el-tree-node__content {
  height: 40px;
  line-height: 40px;
  border-bottom: 1px #eee solid;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 20px;
}
</style>