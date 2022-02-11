<template>
<el-page-header content="新增内容" @back="goBack" title="返回" style="    padding-bottom: 10px;"/>
  <el-card class="box-card">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="基础内容" name="first">
        <el-form label-width="120px">
          <el-form-item label="标题">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="所属分类">
            <el-select v-model="form.region" placeholder="请选择分类">
              <el-option label="Zone one" value="shanghai"></el-option>
              <el-option label="Zone two" value="beijing"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="内容">
            <WangEditorEdit></WangEditorEdit>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit" v-debounce="onSubmit">提交内容</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="SEO选项" name="second">
        <el-form
          :label-position="labelPosition"
          label-width="100px"
        >
          <el-form-item label="SEO标题">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字">
            <el-input v-model="form.region"></el-input>
          </el-form-item>
          <el-form-item label="SEO描述">
            <el-input v-model="form.type"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="其他设置" name="third">Role</el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script lang="ts">
import { putContent } from "../api/api_content";
import WangEditorEdit from "./WangEditorEdit.vue";
import { ref, reactive, toRefs, provide } from "vue";
import { useRouter } from "vue-router";
import { ElMessage } from 'element-plus'


export default {
  components: { WangEditorEdit },
  setup() {
    const route = useRouter();
    const form = ref({
      title: "",
      region: "",
      date1: "",
      date2: "",
      delivery: false,
      type: "",
      resource: "",
      desc: "",
      content: ""
    });
    const activeName = ref("first");
    const labelPosition = ref("right");
    const handleClick = async (data) => {
      console.log(data);
    };
    const onSubmit = async () => {
      if(editContent.value == ""){
          ElMessage('内容不能为空！');
          return false;
      }
      form.value.content = editContent.value;
      putContent(form.value).then((res) => {
          console.log(res);
          ElMessage(res.message);
          if(res.rcode == 0){
            route.push("/content_manage");
          }
      });
    };
    const goBack = ()=>{
      route.push("/content_manage");
    }
    const editContent = ref("");
    provide("editType", "add");
    const updateEditContent = (text) => {
      editContent.value = text;
    };
    provide("updateEditContent", updateEditContent);

    return {
      form,
      activeName,
      labelPosition,
      handleClick,
      onSubmit,
      goBack,
    };
  },
};
</script>

<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>