<template>
  <nav>
    <router-link v-bind:to="{ name: 'create-outing', params: { userId: this.$store.state.user.id } }"
      v-on:click="linkSelectedRestaurantsToStore">Send Selected Restaurants to Your Outing
    </router-link>
  </nav>

  <div v-for="(restaurant, index) in businesses.businesses" :key="restaurant.id" id="restaurant-list">
    <div id="button-div">
      <button type="button" @click="getDetailsOfRestaurant(index)">Show Store Hours</button>
      <p v-if="restaurantDetails[index] && restaurantDetails[index].hours && restaurantDetails[index].hours[0]">






        <span v-for="(openHour, openIndex) in restaurantDetails[index].hours[0].open" :key="openIndex">
          {{ formatOpenHours(openHour) }}<br />
        </span>

        
      </p>
    </div>
    <div id="pic-div">
      <img :src="restaurant.image_url" id="pic" />
    </div>
    <div id="text-div">
      <div id="name">
        <h3>{{ restaurant.name }}</h3>
      </div>
      <div id="rating">
        <p>Rating: {{ restaurant.rating }}</p>
      </div>
      <div id="price">
        <p>Price: {{ restaurant.price }}</p>
      </div>
      <div>
        <p>Category: {{ restaurant.categories[0].title }}</p>
      </div>
      <div id="phoneNumber">
        <p>Phone Number: {{ restaurant.display_phone }}</p>
      </div>
      <div id="options">
        <p>Options: {{ formatTransactionTypes(restaurant.transactions) }}</p>
      </div>
      <div>
        <p>Address: {{ restaurant.location.address1 }}, {{ restaurant.location.city }},
          {{ restaurant.location.state }}</p>
      </div>
      <div id="isClosed">
        <p>Open Status: {{ openStatus[index] }}</p>
      </div>
      <div>
        <label>Add Restaurant to Outing?</label>
        <input type="checkbox" v-model="selectedBusinesses" :value="restaurant" />
      </div>
    </div>
    <img
      v-bind:src="`https://api.mapbox.com/styles/v1/mapbox/streets-v12/static/${restaurant.coordinates.longitude},${restaurant.coordinates.latitude},14/200x200?access_token=pk.eyJ1IjoiYmFvYW5qbmd1eWVuIiwiYSI6ImNscHNwdHU1czA1M2Uyd3JwNWZwd2I2N2sifQ.J1sb_URnd6sCtiH7ITM2Wg`" />
  </div>
</template>

<script>
import RestaurantService from '../services/RestaurantService';

export default {
  data() {
    return {
      businesses: {},
      selectedBusinesses: [],
      restaurantDetails: [],
    };
  },
  computed: {
    openStatus() {
      return this.businesses.businesses.map(restaurant => {
        if (restaurant.isClosed) {
          return 'Closed';
        } else if (!restaurant.isClosed) {
          return 'Open Now';
        } else {
          return 'No open status data available';
        }
      });
    },
  },
  methods: {
    getListOfBusinesses(zipCode, category) {
      if (category) {
        category = category.toLowerCase();
        RestaurantService.getRestaurantsByZipAndCategory(zipCode, category)
          .then((response) => {
            this.businesses = response.data;
          });
      } else {
        RestaurantService.getRestaurantsByZipOnly(zipCode)
          .then((response) => {
            this.businesses = response.data;
          });
      }
    },
    linkSelectedRestaurantsToStore() {
      this.$store.commit('SET_OUTING_RESTAURANTS', this.selectedBusinesses);
    },
    formatTransactionTypes(transactions) {
      if (transactions.length > 0) {
        return transactions
          .map(transaction => transaction
            .split('_')
            .map(word => word.charAt(0).toUpperCase() + word.slice(1))
            .join(' ')
          )
          .join(', ');
      } else {
        return 'Dine-In Only';
      }
    },
    setSelectedRestaurant(restaurant) {
      this.$store.commit('ADD_SELECTED_RESTAURANT', restaurant);
    },
    getDetailsOfRestaurant(index) {
      const restaurantId = this.businesses.businesses[index].id;

      RestaurantService.getRestaurantDetails(restaurantId)
        .then((response) => {
          this.restaurantDetails.splice(index, 1, response.data );
        });
    },
    formatOpenHours(openHour) {

    



      const formatTime = (time) => {
        const formattedTime = new Date();
        formattedTime.setHours(Math.floor(time / 100));
        formattedTime.setMinutes(time % 100);
        return formattedTime.toLocaleTimeString('en-US', { hour: 'numeric', minute: '2-digit' });
      };
      const formattedStart = formatTime(openHour.start);
      const formattedEnd = formatTime(openHour.end);

      return `Opening Hours: ${this.getDayName(openHour.day)} ${formattedStart} - ${formattedEnd}`;

    },




//     formatOpenHours(openHour) {
//   const currentDay = new Date().getDay(); // 0 for Sunday, 1 for Monday, and so on
//   const todayOpenHour = openHour.find(day => day.day === currentDay);

//   if (todayOpenHour) {
//     const formatTime = (time) => {
//       const formattedTime = new Date();
//       formattedTime.setHours(Math.floor(time / 100));
//       formattedTime.setMinutes(time % 100);
//       return formattedTime.toLocaleTimeString('en-US', { hour: 'numeric', minute: '2-digit' });
//     };

//     const formattedStart = formatTime(todayOpenHour.start);
//     const formattedEnd = formatTime(todayOpenHour.end);

//     return `Opening Hours: ${formattedStart} - ${formattedEnd}`;
//   } else {
//     return 'Closed today';
//   }
// },










    getDayName(day) {
      const daysOfWeek = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
      return daysOfWeek[day];
    },
    formatTime(time) {
      const hours = Math.floor(time / 100);
      const minutes = time % 100;
      const ampm = hours >= 12 ? 'PM' : 'AM';
      const formattedHours = hours % 12 === 0 ? 12 : hours % 12;
      return `${formattedHours}:${minutes.toString().padStart(2, '0')} ${ampm}`;
    },
  },
  created() {
    console.log('Component created');
    this.getListOfBusinesses(this.$route.params.zipCode, this.$route.params.category);
  },
};
</script>

<style>
#pic {
  max-height: 200px;
  max-width: 200px;
}

#restaurant-list {
  background-color: rgb(218, 235, 122);
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "pic text-div";
  padding: 10px;
  margin: 2%;
  min-height: 220px;
}

#text-div {
  grid-area: "text-div";
  text-align: center;
}

#pic-div {
  grid-area: "pic";
  display: flex;
  justify-content: center;
  align-items: center;
}

#hours {
  grid-area: "hours";
  text-align: center;
}
</style>
