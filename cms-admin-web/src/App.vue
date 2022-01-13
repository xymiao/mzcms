<template>
  <router-view></router-view>
</template>

<script lang="ts">
export default {
  created() {
      if(sessionStorage.getItem("store")){
        this.$store.replaceState(Object.assign({}, this.$store.state, JSON.parse(sessionStorage.getItem("store"))));
      }
      window.addEventListener('beforeunload', ()=>{
        sessionStorage.setItem('store', JSON.stringify(this.$store.state));
      })
  },
}
</script>

<style>
</style>
