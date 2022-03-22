import {createApp} from "vue";
import App from "./App.vue";

import {router} from './router/index'
import {store} from './store/index'

import ElementPlus from "element-plus";
import 'element-plus/dist/index.css';

import debounce from './directive/debounce'


// import all element css, uncommented next line
// import "element-plus/dist/index.css";
// or use cdn, recommended
// see index.html

const app = createApp(App);
app.use(ElementPlus);
app.use(router);
app.use(store);
app.use(debounce);

app.mount("#app");
