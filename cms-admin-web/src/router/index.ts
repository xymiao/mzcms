import {createRouter, createWebHashHistory} from 'vue-router'
import {store} from '../store'

const Home = () => import('../pages/Home.vue')
const About = () => import('../pages/About.vue')
const NotFound = () => import( '../pages/NotFound.vue')
const Login = () => import('../pages/Login.vue')

const Layout = () => import('../pages/Layout.vue')
const routes = [
    {path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound},
    {
        path: '/', component: Layout,
        children: [
            {
                path: '',
                component: Home
            },
            {
                path: 'about1',
                component: About
            }
        ]
    },
    {path: '/about', component: About},
    {path: '/login', name: 'Login', component: Login, hidden: true},
]

export const router = createRouter({
    history: createWebHashHistory(),
    routes: routes,
})

router.beforeEach((to, from) => {
    // ...
    let isLogin = store.getters.isLogin;
    console.log(to, "导航守卫， 是不是类似过滤器的概念？", from, isLogin);
    //判断用户是否登录
    if (to.name !== "Login" && isLogin == "1") {
        router.push("/login");
        console.log("跳转登录页面");
        return false;
    } else {
        return true;
    }
    // 返回 false 以取消导航
    //return true;
})

export default {}