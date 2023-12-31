import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'
// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import RestaurantSearchView from '../views/RestaurantSearchView.vue';
import RestaurantList from '../components/RestaurantList.vue';
import RestaurantDisplayView from '../views/RestaurantDisplayView.vue';
import CreateOutingView from '../views/CreateOutingView.vue';
import MyOutingsView from '../views/MyOutingsView.vue';
import OutingDetailView from '../views/OutingDetailView.vue';
import FinalistView from '../views/FinalistView.vue';
/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/restaurantsSearch/",
    name: "restaurantsSearch",
    component: RestaurantSearchView,
    meta: {
      requiresAuth: true
    }
  }, 
  {
    path: "/restaurantsSearch/:zipCode/:category", 
    name: "restaurant-display-zip-category", 
    component: RestaurantDisplayView, 
    meta: {
      requiresAuth: true
    }
  }, 
  {
    path: "/restaurantsSearch/:zipCode", 
    name: "restaurant-display-zip", 
    component: RestaurantDisplayView, 
    meta:{
      requiresAuth: true
    }

  },
  {
    path: "/createOuting/:userId",
    name: "create-outing",
    component: CreateOutingView,
    meta:{
      requiresAuth: true
    }
  },
  {
    path: "/myOutings",
    name: "my-outings",
    component: MyOutingsView,
    meta:{
      requiresAuth: true
    }
  }, 
  {
    path: "/myOutings/:outingId",
    name: "outing-detail",
    component: OutingDetailView, 
    meta:{
      requiresAuth: false
    }
  },
  {
    path: "/myOutings/:outingId/finalists",
    name:"finalists",
    component: FinalistView,
    meta:{
      requiresAuth:true
    }
  }
];
// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});
router.beforeEach((to) => {
  // Get the Vuex store
  const store = useStore();
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});
export default router;
