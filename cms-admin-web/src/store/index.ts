import { createStore } from 'vuex'

// 创建一个新的 store 实例
export  const store = createStore({
    state () {
        return {
            userSession: '1',
            userToken: '',
            isCollapse: false,
            leftNavbarList: [],
        }
    },
    getters: {
        isLogin: (state) => {
            return state.userSession;
        },
        isCollapse: (state) =>{
            return state.isCollapse;
        },
        userToken: (state) =>{
            return state.userToken;
        },
        getLeftNavBarList: (state) =>{
            return state.leftNavbarList;
        }
    },
    mutations: {
        useMenuCollapse(state, value){
            state.isCollapse = value;
        },
        userLogin(state, token){
            state.userSession = '2';
            console.log("设置token", token);
            state.userToken = token;
        },
        userExit(state){
            state.userSession = "1";
            state.userToken = "";
            state.leftNavbarList = [];
        },
        userClean(state){
            state.userSession = "1";
            state.userToken = "";
        },
        setLeftNavBarList(state, info){
            state.leftNavbarList = info;
        }
    }
})
export  default { store }

