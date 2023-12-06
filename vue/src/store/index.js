import { createStore as _createStore } from 'vuex';
import axios from 'axios';
export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      //the zipcode input from the user that should be stored and accessible globally
      zipcodeInput: '', 
      storeOfRestaurants: {}
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_ZIPCODE(state, zipcodeInput) {
        state.zipcodeInput = zipcodeInput;
      }, 
      SET_RESTAURANTS(state, businesses){
        state.storeOfRestaurants = businesses;
      }
    },
  });
  return store;
}