import { createStore } from 'vuex'

// 创建一个新的 store 实例
export  const store = createStore({
    state () {
        return {
            userSession: '1',
            userToken: '',
            isCollapse: false,
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
        },
        userClean(state){
            state.userSession = "1";
            state.userToken = "";
        },
    }
})
export  default { store }

