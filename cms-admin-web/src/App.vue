<template>
  <router-view></router-view>
</template>

<script lang="ts" setup>
import {useStore} from 'vuex';

const store = useStore();

if (sessionStorage.getItem("store")) {
  store.replaceState(Object.assign({}, store.state, JSON.parse(sessionStorage.getItem("store") || "{}")));
}
window.addEventListener('beforeunload', () => {
  sessionStorage.setItem('store', JSON.stringify(store.state));
})

</script>
<style>
</style>