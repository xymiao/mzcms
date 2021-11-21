<template>
  <el-row class="row-bg" justify="center" style="padding-top: 100px;">
    <el-col :xs="10" :sm="12" :md="6" :lg="6" :xl="11">
      <el-card class="box-card">
        <div style="font-size: 24px; padding: 10px 0; text-align: center;">用户登录</div>
        <el-form-item prop="username">
        <el-input v-model="username" placeholder="请输入用户名" ></el-input>
        </el-form-item>
        <el-form-item prop="pwd">
        <el-input placeholder="请输入密码" v-model="pwd" show-password></el-input>
        </el-form-item>
        <el-button type="primary" @click="login" style="width: 100%;">登录</el-button>
      </el-card>
    </el-col>
  </el-row>
</template>

<script lang="ts">
import {ElMessage} from 'element-plus'
import {userLogin} from '../api/api_user'

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
      userLogin({username: this.username, password: this.pwd}).then(res=>{
        console.log(res.data);
        if(res.rcode == 0){
          this.$store.commit("userLogin", res.data);
          ElMessage.success({
            message: '登录成功！',
            type: 'success',
          });
          window.location.href="/";
          //this.$router.push('/');
        }else{
          ElMessage.error('登录失败， 帐号和密码不正确')
        }
      });
      /*
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
       */

    }
  },
}
</script>

<style>

</style>