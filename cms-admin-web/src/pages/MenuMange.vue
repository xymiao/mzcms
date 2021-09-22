<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>菜单管理</span>
        <el-button class="button" type="text" @click="addMenuVisible = true">新增菜单</el-button>
      </div>
    </template>
    <el-table :data="menuList.records" border style="width: 100%" >
      <el-table-column prop="menuName" label="名称" width="180"></el-table-column>
      <el-table-column prop="menuModule" label="编码"></el-table-column>
      <el-table-column prop="menuName" label="父类类型"></el-table-column>
      <el-table-column prop="menuType" label="父分类"></el-table-column>
      <el-table-column prop="parentId" label="每页内容数"></el-table-column>
      <el-table-column prop="created" label="创建时间"></el-table-column>
      <el-table-column prop="sort" label="排序"></el-table-column>
      <el-table-column fixed="right" label="操作" width="160">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
          >编辑
          </el-button
          >
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
          >删除
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          v-model:currentPage="menuList.current"
          :page-size="menuList.size"
          layout="total, prev, pager, next"
          :total="menuList.total"
      >
      </el-pagination>
    </div>
  </el-card>

  <el-drawer
      title="我是标题"
      v-model="dialogVisible"
      :direction="direction"
      :before-close="handleClose"
      destroy-on-close
  >
    <span>我来啦!</span>
  </el-drawer>
  <el-drawer
      title="新增菜单"
      v-model="addMenuVisible"
      :direction="direction"
      destroy-on-close
  >

    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="菜单名称">
        <el-input v-model="form.menuName"   maxlength="30" show-word-limit></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-switch v-model="form.delFlag" :active-value="1" :inactive-value="0"></el-switch>
      </el-form-item>
      <el-form-item label="模块类型">
        <el-radio-group v-model="form.menuModule">
          <el-radio label="frontend">前端</el-radio>
          <el-radio label="backend">后端</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="菜单类别">
        <el-radio-group v-model="form.menuType">
          <el-radio label="left_nav">左侧菜单</el-radio>
          <el-radio label="top_nav">顶部菜单</el-radio>
        </el-radio-group>
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

<script>

import {listMenu, putMenu} from '../api/api_menu'
import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      currentPage1: 5,
      menuList: {current: 1},
      dialogVisible: false,
      addMenuVisible: false,
      direction: 'rtl',
      form: {
        menuName: '',
        delFlag: 0,
        menuModule: 'backend',
        parentId: 'parent',
        menuInfo: '',
        menuType: 'left_nav',
      },
    }
  },
  created() {
    this.loadMenuList();
  },
  computed:{
  },
  methods: {
    goBack() {
      console.log("go back")
    },
    loadMenuList() {
      let data = {module: 'backend', currPage: this.menuList.current};
      listMenu(data).then(res => {
        console.log(res.data);
        this.menuList = res.data;
        console.log("menu", this.menuList)
      });
    },
    handleEdit(index, value) {
      console.log(index, value);
      this.dialogVisible = true;
    },
    addMenu(){
      putMenu(this.form).then(res => {
        console.log(res);
        this.menuList.records.unshift(res.data) ;
        this.menuList.total = this.menuList.records.length;
        if(res.rcode == 0){
          ElMessage.success({
            message: '添加成功！',
            type: 'success',
          });
          this.addMenuVisible = false;
          this.form ={
            menuName: '',
            delFlag: 0,
            menuModule: 'backend',
            parentId: 'parent',
            menuInfo: '',
            menuType: 'left_nav',
          };
        }else{
          ElMessage.error("添加失败！");
        }
        console.log("menu", this.menuList)
      });
    },
    handleNodeClick(data) {
      console.log(data)
    },
    handleSizeChange(){

    },
    handleCurrentChange(){
      this.loadMenuList();
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then((_) => {
            done()
          })
          .catch((_) => {
          })
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