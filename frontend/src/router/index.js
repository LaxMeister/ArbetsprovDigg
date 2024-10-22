import { createRouter, createWebHistory } from 'vue-router'
import Home from '../components/Home.vue'
import NewUser from '../components/NewUserForm.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/newuser',
      name: 'newuser',
      component: NewUser
    },
    {
      path: '/edituser',
      name: 'edituser',
      component: NewUser,
      props: route => ({ item: route.params.item })
    }
  ]
})

export default router