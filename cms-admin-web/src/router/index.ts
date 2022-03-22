import {createRouter, createWebHashHistory} from 'vue-router'
import {store} from '../store'

const Layout = () => import('../pages/Layout.vue')
const Home = () => import('../pages/Home.vue')
const About = () => import('../pages/About.vue')
const NotFound = () => import( '../pages/NotFound.vue')
const Login = () => import('../pages/Login.vue')
const Content = () => import('../pages/Content.vue')
const ContentAdd = () => import('../pages/ContentAdd.vue')
const ContentEdit = () => import('../pages/ContentEdit.vue')
const ContentCategory = () => import('../pages/ContentCategory.vue')
const MenuMange = () => import('../pages/MenuMange.vue')
const SiteSetting = () => import('../pages/SiteSetting.vue')

const AuthIndex = () => import('../pages/auth/AuthIndex.vue')
const RoleIndex = () => import('../pages/auth/RoleIndex.vue')

const LogIndex = () => import('../pages/LogIndex.vue')

const UserList = () => import('../pages/user/UserList.vue')

const PageIndex = () => import('../pages/PageIndex.vue')

const routes = [
    {path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound},
    {
        path: '/', component: Layout, name: 'home',
        children: [
            {
                path: '',
                component: Home, name: 'home',
            },
            {
                path: 'content_manage',
                component: Content, name: 'content_manage',
            },
            {
                path: 'content_add',
                component: ContentAdd, name: 'content_add',
            },
            {
                path: 'content_edit/:id',
                component: ContentEdit, name: 'content_edit',
            },
            {
                path: 'content_category',
                component: ContentCategory, name: 'content_category',
            },
            {
                path: 'sys_menu',
                component: MenuMange, name: 'sys_menu',
            },
            {
                path: 'site_setting',
                component: SiteSetting, name: 'site_setting',
            },
            {
                path: 'auth_manage',
                component: AuthIndex, name: 'auth_index',
            },
            {
                path: 'role_manage',
                component: RoleIndex, name: 'role_index',
            },
            {
                path: 'log_manage',
                component: LogIndex, name: 'log_index',
            },
            {
                path: 'user_list',
                component: UserList, name: 'user_list',
            },
            {
                path: 'page_cust',
                component: PageIndex, name: 'page_cust',
            },
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
    let isLogin = store.getters.isLogin;
    //console.log(to, "导航守卫， 是不是类似过滤器的概念？", from, isLogin);
    //判断用户是否登录
    if (to.name !== "Login" && isLogin == "1") {
        router.push("/login");
        //console.log("没有登录，跳转登录页面");
        return false;
    } else {
        return true;
    }
    // 返回 false 以取消导航
    //return true;
})
export default {}