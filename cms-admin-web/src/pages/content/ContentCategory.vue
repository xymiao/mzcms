<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>内容分类管理</span>
        <el-button class="button" type="default" @click="openAdd('parent')"
        >新增类别
        </el-button>
      </div>
    </template>
    <el-tree
        :data="categoryList"
        :expand-on-click-node="false"
        :props="defaultProps"

        node-key="categoryId">
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
             <el-popconfirm cancelButtonText="取消" confirmButtonText="删除" title="确认要删除该类别吗?"
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
  <category-edit :edit-form="editForm" :edit-info="editInfo" @refresh="refreshData"></category-edit>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import {delCategory, listCategory} from "../../api/api_category";
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


let id = 1000;
const addInfo = ref({title: "新增分类", addVisible: false, modal: true});
const editInfo = ref({title: "修改分类", addVisible: false, modal: true});

const defaultProps = {
  children: "cmsCategories",
  label: "name",
};


const form = {parentId: "parent", moduleId: 'backend'};
let editForm = {
  categoryId: "",
  name: "",
  moduleId: "",
  parentId: "",
  url: "",
  sort: 0,

};

let categoryList = ref([]);
const loadCategoryList = () => {
  let data = {module: 'backend'};
  listCategory(data).then((res) => {
    //console.log(res.data);
    categoryList.value = res.data;
    //console.log("category", categoryList.value);
  });
};
loadCategoryList();
const openAdd = (parent, node) => {
  addInfo.value.addVisible = true;
  form.moduleId = 'backend';
  if (node) {
    addInfo.value.title = "添加 " + node.data.name + " 子类别"
    form.parentId = node.data.categoryId;
    //console.log(parent, node.data);
  } else {
    addInfo.value.title = "添加类别"
    form.parentId = "parent";
  }
};
const openEdit = (node) => {
  console.log("openEdit: ", node.data);
  editInfo.value.addVisible = true;
  editForm.categoryId = node.data.categoryId;
  editForm.name = node.data.name;
  editForm.moduleId = node.data.moduleId;
  editForm.parentId = node.data.parentId;
  editForm.url = node.data.url;
  console.log(editForm.name)
};
const remove = (node, data) => {
  //console.log("node", node);
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