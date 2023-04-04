import Vue from 'vue'
import Router from 'vue-router'
import OrdersInfo from '@/components/OrdersInfo'
import Login from '@/components/Login'
import Register from '@/components/Register'
import SystemNotice from '@/components/SystemNotice'
import OrderDetail from '@/components/OrderDetail'
import LeaveWord  from '@/components/LeaveWord'
import MyOrder from '@/components/MyOrder'
import PersonalCenter from '@/components/PersonalCenter'
import PersonalCenterPage from '@/components/PersonalCenterPage'
import MyComment from '@/components/MyComment'
import PasswordModi from '@/components/PasswordModi'
import PasswordModiAct from '@/components/PasswordModiAct'
import OrderRelease from '@/components/OrderRelease'
import OrderAcceptedSituation from '@/components/OrderAcceptedSituation'
import PersonalInformation from '@/components/PersonalInformation'
import backend from '@/components/backend'
import UserManage from '@/components/UserManage'
import OrderManage from '@/components/OrderManage'
import CommentManage from '@/components/CommentManage'
import NoticeManage from '@/components/SystemNoticeManage'
import LeaveWordManage from '@/components/LeaveWordManage'
import CategoryManage from '@/components/CategoryManage'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'OrdersInfo',
      component: OrdersInfo
    },
    {
      path:'/backend',
      name:'backend',
      component: backend,
      children:[
        {
          path: 'user-manage',
          name: 'UserManage',
          component: UserManage
        },
        {
          path: 'order-manage',
          name: 'OrderManage',
          component: OrderManage
        },
        {
          path: 'comment-manage',
          name: 'CommentManage',
          component: CommentManage
        },
        {
          path: 'notice-manage',
          name: 'SystemNoticeManage',
          component: NoticeManage
        },
        {
          path: 'leaveword-manage',
          name: 'LeaveWordManage',
          component: LeaveWordManage
        },
        {
          path: 'category-manage',
          name: 'CategoryMange',
          component: CategoryManage
        }
      ]
    },
    {
      path: '/order-release',
      name: 'OrderRelease',
      component: OrderRelease
    },
    {
      path: '/order-accepted-situation',
      name: 'OrderAcceptedSituation',
      component: OrderAcceptedSituation
    },
    {
      path: '/personal-center',
      name: 'personalCenter',
      component: PersonalCenter,
      children:[
        {
          path: 'page',
          name: 'PersonalCenterPage',
          component: PersonalCenterPage,
        },
        {
          path: 'comment',
          name: 'MyComment',
          component: MyComment
        },
        {
          path: 'pwmodi',
          name: 'PasswordModi',
          component: PasswordModi
        },
        {
          path: 'pwmodi-act',
          name: 'PasswordModiAct',
          component: PasswordModiAct
        },
        {
          path: 'per-info',
          name: 'PersonalInformation',
          component: PersonalInformation
          
        }
      ]
    },
    {
      path: '/myOrder',
      name: 'MyOrder',
      component: MyOrder
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/system-notice',
      name: 'SystemNotice',
      component: SystemNotice
    },
    {
      path: '/order-detail',
      name: 'OrderDetail',
      component: OrderDetail
    },
    {
      path: '/leave-word',
      name: 'Leave-word',
      component: LeaveWord
    }
  ]
})
