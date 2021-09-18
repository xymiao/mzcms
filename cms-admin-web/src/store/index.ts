import { createStore } from 'vuex'

// 创建一个新的 store 实例
export  const store = createStore({
    state () {
        return {
            userSession: '1',
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
    },
    mutations: {
        useMenuCollapse(state, value){
            state.isCollapse = value;
        },
        userLogin(state){
            state.userSession = '2';
        },
        userExit(state){
            state.userSession = "1";
        }
    }
})
export  default { store }

