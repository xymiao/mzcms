<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item
            >内容编辑{{ $route.params.id }}</el-breadcrumb-item
          >
        </el-breadcrumb>
        <el-button class="button" type="text">
          <router-link to="/content_manage">返回</router-link>
        </el-button>
      </div>
    </template>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="基础内容" name="first">
        <el-form label-width="120px">
          <el-form-item label="标题">
            <el-input v-model="contentInfo.title"></el-input>
          </el-form-item>
          <el-form-item label="内容">
            <WangEditorEdit></WangEditorEdit>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">修改内容</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="SEO选项" name="second">
        <el-form label-width="100px">
          <el-form-item label="SEO标题">
            <el-input></el-input>
          </el-form-item>
          <el-form-item label="SEO关键字">
            <el-input></el-input>
          </el-form-item>
          <el-form-item label="SEO描述">
            <el-input></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="其他设置" name="third">Role</el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script lang="ts">
import { listCategory } from "../api/api_category";
import { getContent, editContentPost } from "../api/api_content";
import WangEditorEdit from "./WangEditorEdit.vue";
import { ref, onMounted, reactive, toRefs, provide, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { ElMessage } from "element-plus";
export default {
  components: { WangEditorEdit },
  setup() {
    const route = useRoute();
    const router = useRouter();

    const activeName = ref("first");
    const labelPosition = ref("right");
    const handleClick = async (data) => {
      console.log(data);
    };
    const onSubmit = async () => {
      if (editContent.value == "") {
        ElMessage("内容不能为空！");
        return false;
      }

      contentInfo.value.content = editContent.value;
      editContentPost(contentInfo.value).then((res) => {
        console.log(res);
        ElMessage(res.message);
        if (res.rcode == 0) {
         // router.push("/content_manage");
        }
      });
    };

    let contentInfo = ref({});
    const loadContentInfo = async () => {
      const contentId = route.params.id;
      let data = { contentId: contentId };
      getContent(data).then((res) => {
        contentInfo.value = res.data;
        editContentOld.value = contentInfo.value.content;
      });
    };
    loadContentInfo();

    let editContentOld = ref(""); // 原始数据
    let editContent = ref("");
    const updateEditContent = (text) => {
      editContent.value = text;
    };

    provide("editType", "edit");
    provide("editContentOld", editContentOld);
    provide("updateEditContent", updateEditContent);

    const dataSource = computed(() => {
      return JSON.parse(JSON.stringify(this.categoryList));
    });

    return {
      activeName,
      labelPosition,
      handleClick,
      onSubmit,
      dataSource,
      contentInfo,
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