import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
    state:{
        orderId: 0,
        orderDetailPage: 0,
        loginOrLogout: "登录",
    },
    mutations:{
        populateOrderId(state,id){
            state.orderId = id;
        },
        populateOrderDetailPage(state,orderDetailPage){
            state.orderDetailPage = orderDetailPage;
        },
        changeLoginOrLogout(state,loginOrLogout){
            state.loginOrLogout = loginOrLogout;
        }
    }
})

export default store;