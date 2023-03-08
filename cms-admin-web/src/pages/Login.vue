<template>
  <el-row class="row-bg" justify="center" style="padding-top: 160px;">
    <el-col :xs="20" :sm="12" :md="6" :lg="6" :xl="11">
      <el-card class="box-card">
        <div style="font-size: 24px; padding: 10px 0; text-align: center;">用户登录</div>
        <el-form>
          <el-form-item label="">
            <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-input placeholder="请输入密码" v-model="form.pwd" @keyup.enter="onLogin" show-password></el-input>
          </el-form-item>
          <el-button type="primary" @click="onLogin" style="width: 100%;">登录</el-button>
        </el-form>
      </el-card>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import {reactive} from 'vue';
import {useStore} from 'vuex';
import {ElMessage} from 'element-plus';
import {userLogin} from '../api/api_user';

const store = useStore();
const loginFlag = store.state.userSession;
if (loginFlag === '2') {
  window.location.href = "/";
}
const form = reactive({
  username: '',
  pwd: '',
})

const onLogin = () => {
  console.log("form: ", form);
  userLogin({username: form.username, password: form.pwd}).then(res => {
    if (res.rcode == 0) {
      store.commit("userLogin", res.data);
      ElMessage.success({
        message: '登录成功！',
        type: 'success',
      });
      window.location.href = "/";
      //this.$router.push('/');
    } else {
      ElMessage.error('登录失败， 帐号和密码不正确')
    }
  });
}
</script>

<style>
</style>