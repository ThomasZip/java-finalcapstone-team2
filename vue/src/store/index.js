import { createStore as _createStore } from 'vuex';
import axios from 'axios';
export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      //the zipcode input from the user that should be stored and accessible globally
      zipcodeInput: '',
      //a collection to hold the details of a single restaurant that the user requests to see details of
      selectedRestaurant: {},
      //an array to hold the restaurant objects selected by the outing creator
      outingRestaurants: [],
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
      ADD_SELECTED_RESTAURANT(state, chosenRestaurant) {
        state.selectedRestaurant = chosenRestaurant;
      },
      ADD_OUTING_RESTAURANT(state, restaurantForOuting) {
        state.outingRestaurants.push(restaurantForOuting);
      }
    },
  });
  return store;
}