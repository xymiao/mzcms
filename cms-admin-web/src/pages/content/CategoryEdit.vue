<template>
  <el-dialog
    v-model="editInfo.addVisible"
    :modal="editInfo.modal"
    :title="editInfo.title"
    width="500px"
  >
    <el-form ref="editForm" :model="editForm" label-width="80px">
      <el-form-item v-if="editForm.parentId !== 'parent'" label="父类属性">
        {{ parentName }} {{editForm}}
      </el-form-item>
      <el-form-item label="分类名称">
        <el-input
          v-model="editForm.name"
          maxlength="30"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="别名">
        <el-input v-model="editForm.url" maxlength="30" show-word-limit></el-input>
      </el-form-item>
      <el-form-item label="排序">
        <el-input v-model="editForm.sort"></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editInfo.addVisible = false">取消</el-button>
        <el-button type="primary" @click="editSubmit">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { editCategory } from "../../api/api_category";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      direction: "rtl",
    };
  },
  props: ["editInfo", "editForm"],
  emits: ['refresh'],
  methods: {
    editSubmit() {
      editCategory(this.editForm).then((res) => {
        if (res.rcode == 0) {
          ElMessage.success({
            message: "修改成功！",
            type: "success",
          });
          this.$emit("refresh");
          this.editInfo.addVisible = false;
        } else {
          ElMessage.error(res.message);
        }
        console.log("categoryEdit: ", this.editForm);
      });
    },
  },
};
</script>

<style>
</style>