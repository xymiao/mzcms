<template>

  <el-dialog
      v-model="showMenu"
      title="Tips"
      width="30%"
      :before-close="handleClose"
  >
    <span>This is a message</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="showMenu = false">Cancel</el-button>
        <el-button type="primary" @click="showMenu = false"
        >Confirm</el-button
        >
      </span>
    </template>
  </el-dialog>


</template>

<script>

import {putMenu} from '../api/api_menu'
import {ElMessage} from "element-plus";

export default {
  props: ['showMenu'],
  data() {
    return {
      currentPage1: 5,
      menuList: {current: 1},
      dialogVisible: false,
      direction: 'rtl',
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

  },
  computed: {},
  methods: {
    goBack() {
      console.log("go back")
    },
    handleAddSubMenu(index, value) {
      console.log(index, value);
      this.form.parentId = value.menuId;
      this.parentName = value.menuName;
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
    handleNodeClick(data) {
      console.log(data)
    },
    handleSizeChange() {

    },
    handleCurrentChange() {
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