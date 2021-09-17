<template>

  <el-row :gutter="20">
    <el-col :span="6" :offset="8">
      <div>用户登录</div>
      <el-input v-model="username" placeholder="请输入用户名"></el-input>
      <el-input placeholder="请输入密码" v-model="pwd" show-password></el-input>
      <el-button type="primary" @click="login">登录</el-button>
    </el-col>
  </el-row>
  <div style="padding: 20px; text-align: center">{{ c }}</div>
</template>

<script>
import {ElMessage} from 'element-plus'

export default {
  data() {
    return {
      username: '',
      pwd: '',
    }
  },
  computed: {
    c() {
      return this.$store.state.userSession;
    }
  },
  methods: {
    login() {
      console.log(this.username, this.pwd);
      if (this.username == "admin" && this.pwd == "admin") {
        this.$store.commit("userLogin");
        ElMessage.success({
          message: '登录成功！',
          type: 'success',

        });
        this.$router.push('/');
      } else {
        ElMessage.error('登录失败， 帐号和密码不正确')
      }
    }
  },
}
</script>

<style>

</style>