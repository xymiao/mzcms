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

    <el-tree
        :data="dataSource"
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
              <el-icon >
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

<script lang="ts">
import {listCategory, delCategory} from "../api/api_category";
import CategoryAdd from "./CategoryAdd.vue";
import CategoryEdit from "./CategoryEdit.vue";
import {Plus, Edit, Delete, CircleClose, Open} from "@element-plus/icons";
import {ElMessage} from 'element-plus'

let id = 1000;
export default {
  data() {
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
  },
  components: {
    CategoryAdd,
    CategoryEdit,
    Plus,
    Delete,
    Edit, Open, CircleClose
  },
  computed: {
    dataSource() {
      return JSON.parse(JSON.stringify(this.categoryList));
    },
  },
  created() {
    this.loadCategoryList();
  },
  methods: {
    goBack() {
      console.log("go back");
    },
    loadCategoryList() {
      let data = {module: 'backend'};
      listCategory(data).then((res) => {
        console.log(res.data);
        this.categoryList = res.data;
        console.log("category", this.categoryList);
      });
    },
    openAdd(parent, node) {
      this.addInfo.addVisible = true;
      this.form.moduleId = 'backend';
      if (node) {
        this.addInfo.title = "添加 " + node.data.name + " 子类别"
        this.form.parentId = node.data.categoryId;
        console.log(parent, node.data);
      } else {
        this.addInfo.title = "添加类别"
        this.form.parentId = "parent";
      }
    },
    openEdit(node) {
      console.log("openEdit: ", node.data);
      this.editInfo.addVisible = true;
      this.editForm = node.data;
    },
    remove(node, data) {
      console.log("node", node);
      delCategory({categoryId: node.data.categoryId}).then((res) => {
        console.log(res)
        if (res.rcode == 0) {
          ElMessage({
            message: res.message || '删除成功',
            type: 'success',
          });
          this.loadCategoryList();
        } else {
          ElMessage.error(res.message);
        }
      });
    },
    refreshData() {
      this.loadCategoryList();
      this.form = {};
    },

  },
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