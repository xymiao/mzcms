import { createStore } from 'vuex'

// 创建一个新的 store 实例
export const store = createStore({
    state () {
        return {
            userSession: '1',
            count: 0
        }
    },
    getters: {
        isLogin: (state) => {
            return state.userSession;
        },
    },
    mutations: {
        increment (state) {
            state.count++
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
