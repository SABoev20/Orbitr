import { createRouter, createWebHistory } from 'vue-router'
import WelcomeView from '../views/WelcomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import DashboardView from '../views/DashboardView.vue'
import MarketplaceView from '../views/MarketplaceView.vue'
import InventoryView from '../views/InventoryView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: WelcomeView,
      component: WelcomeView
      
    },
    {
      path: '/login',
      name: LoginView,
      component: LoginView
      
    },
    {
      path: '/register',
      name: RegisterView,
      component: RegisterView

    },
    {
      path: '/dashboard',
      name: DashboardView,
      component: DashboardView
      
    },
    {
      path: '/marketplace',
      name: MarketplaceView,
      component: MarketplaceView
      
    },
    {
      path: '/inventory',
      name: InventoryView,
      component: InventoryView
      
    }
  ]
})

export default router
