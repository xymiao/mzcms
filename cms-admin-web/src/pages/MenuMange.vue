<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>菜单管理</span>
        <el-button class="button" type="text" @click="addMenuRoot">新增菜单</el-button>
      </div>
    </template>

    <el-table
        :data="menuList.records"
        style="width: 100%;margin-bottom: 20px;"
        row-key="menuId"
        border
        :tree-props="{children: 'cmsMenus', hasChildren: 'hasChildren'}">
      <el-table-column prop="menuName" label="名称" sortable width="180">
      </el-table-column>
      <el-table-column prop="url" label="URL" sortable width="180">
      </el-table-column>
      <el-table-column prop="sort" label="排序" sortable width="180">
      </el-table-column>
      <el-table-column prop="menuInfo" label="菜单描述"></el-table-column>
      <el-table-column fixed="right" label="操作" width="240">
        <template #default="scope">
          <el-button size="small" @click="handleAddSubMenu(scope)">新增
          </el-button>
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-popconfirm title="确定要删除该菜单吗?"
                         confirm-button-text="确认"
                         cancel-button-text="取消"
                         @confirm="delMenu(scope.$index, scope.row)">
            <template #reference>
              <el-button size="small"
                         type="danger">删除
              </el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

  <el-drawer
      title="编辑菜单"
      v-model="editMenuVisible"
      :direction="direction"
      destroy-on-close
  >
    <el-form ref="form" :model="editMenu" label-width="80px">
      <el-form-item label="菜单名称">
        <el-input v-model="editMenu.menuName" maxlength="30" show-word-limit></el-input>
      </el-form-item>
      <el-form-item label="URL">
        <el-input v-model="editMenu.url" maxlength="30" show-word-limit></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-switch v-model="editMenu.delFlag" :active-value="1" :inactive-value="0"></el-switch>
      </el-form-item>
      <el-form-item label="菜单说明">
        <el-input type="textarea" v-model="editMenu.menuInfo"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="editMenuPost">更新菜单</el-button>
      </el-form-item>
    </el-form>

  </el-drawer>
  <el-drawer
      title="新增菜单"
      v-model="addMenuVisible"
      :direction="direction"
      destroy-on-close
  >
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item v-if="form.parentId != 'parent'" label="父类属性">
        {{ parentName }}
      </el-form-item>
      <el-form-item label="菜单名称">
        <el-input v-model="form.menuName" maxlength="30" show-word-limit></el-input>
      </el-form-item>
      <el-form-item label="URL">
        <el-input v-model="form.url" maxlength="30" show-word-limit></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-switch v-model="form.delFlag" :active-value="1" :inactive-value="0"></el-switch>
      </el-form-item>
      <el-form-item label="菜单说明">
        <el-input type="textarea" v-model="form.menuInfo"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addMenu">立即创建</el-button>
      </el-form-item>
    </el-form>

  </el-drawer>

</template>

<script lang="ts">

import {delMenu, editMenu, listMenu, putMenu} from '../api/api_menu'
import {ElMessage} from "element-plus";
import MenuAdd from "./MenuAdd.vue";

export default {
  components: {MenuAdd},
  data() {
    return {
      editMenuVisible: false,
      addMenuVisible: false,
      direction: 'rtl',
      menuList: {current: 1},
      form: {
        menuName: '',
        delFlag: 0,
        menuModule: 'backend',
        parentId: 'parent',
        menuInfo: '',
        menuType: 'left_nav',
        url: '',
      },
      parentName: '',
      editMenu: {}
    }
  },
  created() {
    this.loadMenuList();
  },
  methods: {
    addMenuRoot() {
      this.addMenuVisible = true;
      this.form.parentId = 'parent';
    },
    goBack() {
      console.log("go back")
    },
    loadMenuList() {
      let data = {module: 'backend', currPage: this.menuList.current};
      listMenu(data).then(res => {
        //console.log(res.data);
        this.menuList = res.data;
        //console.log("menu", this.menuList)
      });
    },
    handleEdit(index, value) {
      //console.log(index, value);
      this.editMenu = value;
      this.editMenuVisible = true;
    },
    handleAddSubMenu(scope) {
      //console.log(scope);
      this.form.parentId = scope.row.menuId;
      this.parentName = scope.row.menuName;
      this.addMenuVisible = true;
    },
    addMenu() {
      putMenu(this.form).then(res => {
        console.log(res);
        this.menuList.records.unshift(res.data);
        this.menuList.total = this.menuList.records.length;
        if (res.rcode == 0) {
          ElMessage.success({
            message: '添加成功！',
            type: 'success',
          });
          this.addMenuVisible = false;
          this.form = {
            menuName: '',
            delFlag: 0,
            menuModule: 'backend',
            parentId: 'parent',
            menuInfo: '',
            menuType: 'left_nav',
            url: '',
          };
        } else {
          ElMessage.error("添加失败！");
        }
        console.log("menu", this.menuList)
      });
    },
    editMenuPost() {
      editMenu(this.editMenu).then(res => {
        console.log(res)
        if (res.rcode == 0) {
          ElMessage.success("修改成功！");
          this.editMenuVisible = false;
          this.editMenu = {};
        } else {
          ElMessage.error("修改失败！");
        }
      });
    },
    delMenu(index, value) {
      console.log(index, value)
      delMenu({menuId: value.menuId}).then(res => {
        console.log(res)
        if (res.rcode == 0) {
          ElMessage.success("删除成功！");
          this.loadMenuList();
        } else {
          ElMessage.error("删除失败！");
        }
      });
    },
    handleNodeClick(data) {
      console.log(data)
    },
    handleSizeChange() {

    },
    handleCurrentChange() {
      this.loadMenuList();
    },

  }
}
</script>

<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 20px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}
</style>