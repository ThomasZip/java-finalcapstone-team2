import { createStore as _createStore } from 'vuex';
import axios from 'axios';
export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      //the zipcode input from the user that should be stored and accessible globally
      zipcodeInput: '',
      //holds restaurants to be added to a users outing, links the restaurants checked to what is displayed in the outing. 
      storeOfRestaurantsInOuting: [],
      storeOfGuestsInOuting: []
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
        state.storeOfRestaurantsInOuting = [];
      },
      SET_ZIPCODE(state, zipcodeInput) {
        state.zipcodeInput = zipcodeInput;
      },
      SET_OUTING_RESTAURANTS(state, restaurants) {
        state.storeOfRestaurantsInOuting.unshift(restaurants);

      },
      CLEAR_OUTING_RESTAURANTS(state) {
        state.storeOfRestaurantsInOuting = [];
      },
      SET_GUESTS_RESTAURANTS(state, guests) {
        state.storeOfGuestsInOuting.unshift(guests);
      },
      CLEAR_GUESTS_RESTAURANTS(state) {
        state.storeOfGuestsInOuting = [];
      }

    },
  });
  return store;
}