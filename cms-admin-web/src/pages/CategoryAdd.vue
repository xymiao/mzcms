<template>
  <el-dialog
    v-model="addInfo.addVisible"
    :modal="addInfo.modal"
    :title="addInfo.title"
    width="30%"
  >
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item v-if="form.parentId != 'parent'" label="父类属性">
        {{ parentName }}
      </el-form-item>
      <el-form-item label="分类名称">
        <el-input
          v-model="form.name"
          maxlength="30"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="别名">
        <el-input v-model="form.url" maxlength="30" show-word-limit></el-input>
      </el-form-item>
      <el-form-item label="排序">
        <el-input v-model="form.sort"></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addInfo.addVisible = false">取消</el-button>
        <el-button type="primary" @click="addSubmit">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { putCategory } from "../api/api_category";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      direction: "rtl",
    };
  },
  props: ["addInfo", "form"],
  emits: ['refresh'],
  methods: {
    addSubmit() {
      putCategory(this.form).then((res) => {
        if (res.rcode == 0) {
          ElMessage.success({
            message: "添加成功！",
            type: "success",
          });
          this.$emit("refresh");
          this.addInfo.addVisible = false;
        } else {
          ElMessage.error("添加失败！");
        }
        console.log("menu", this.menuList);
      });
    },
  },
};
</script>

<style>
</style>