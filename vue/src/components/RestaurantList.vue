<template>
<<<<<<< HEAD
  <div>
    <nav>
      <router-link to="{ name: 'create-outing' }" @click="linkSelectedRestaurantsToStore">
        Send Selected Restaurants to Your Outing
      </router-link>
    </nav>

    <div v-if="businesses.length > 0">
      <div v-for="restaurant in businesses" :key="restaurant.id" id="restaurant-list">
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
            <label for="checkboxRestaurant">Add Restaurant to Outing?</label>
                        <input type="checkbox" v-model="selectedBusinesses" :value="restaurant" />
          </div>
          <!-- Displaying start and end variables for each day -->
          <div id="hours">
            <p v-if="restaurant.hours && restaurant.hours.length">
              Hours:
              <span v-for="day in restaurant.hours[0].open" :key="day.day">
                {{ getDayName(day.day) }}: {{ formatTime(day.start) }} - {{ formatTime(day.end) }},
              </span>
            </p>
          </div>
        </div>
        <img
          :src="`https://api.mapbox.com/styles/v1/mapbox/streets-v12/static/${restaurant.coordinates.longitude},${restaurant.coordinates.latitude},14/200x200?access_token=pk.eyJ1IjoiYmFvYW5qbmd1eWVuIiwiYSI6ImNscHNwdHU1czA1M2Uyd3JwNWZwd2I2N2sifQ.J1sb_URnd6sCtiH7ITM2Wg`"
        />
=======
  <nav>
    <!-- adds restaurants to your outing and pushes user to create outing page -->
    <router-link v-bind:to="{ name: 'create-outing', params: { userId: this.$store.state.user.id } }"
      v-on:click="linkSelectedRestaurantsToStore">Send Selected
      Restaurants to Your Outing
    </router-link>
  </nav>

  <!-- Hold individual sections for each restaurant ex: div v-for -->
  <div v-for="(restaurant, index) in businesses.businesses" v-bind:key="restaurant.id" id="restaurant-list">
    <div id="button-div">
      <button type="button" v-on:click="getDetailsOfRestaurant(restaurant.id)">Show Store Hours</button>
      <p v-if="restaurantDetails && restaurantDetails.hours && restaurantDetails.hours[0]">
        <!-- Loop through each open object inside hours[0].open -->
        <span v-for="(openHour, openIndex) in restaurantDetails.hours[0].open" :key="openIndex">
          {{ formatOpenHours(openHour) }}<br />
        </span>
      </p>
    </div>
    <div id="pic-div">
      <img v-bind:src="restaurant.image_url" id="pic" />
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
        <p>Address: {{ restaurant.location.address1 }}, {{ restaurant.location.city }}, {{ restaurant.location.state }}
        </p>
      </div>
      <div id="isClosed">
        <p>Open Status: {{ openStatus[index] }}</p>
      </div>

      <div>
        <label>Add Restaurant to Outing?</label>
        <input type="checkbox" v-model="selectedBusinesses" v-bind:value="restaurant" />
>>>>>>> 3678e33f68cd7ea6c23e4c2277e80464639117cf
      </div>
    </div>
    <div v-else>
      Loading... <!-- or display some loading indicator -->
    </div>
  </div>
</template>

<script>
import RestaurantService from '../services/RestaurantService';

export default {
  data() {
    return {
<<<<<<< HEAD
      businesses: [],
      selectedBusinesses: [],
=======
      businesses: {},
      selectedBusinesses: [],
      restaurantDetails: {},

>>>>>>> 3678e33f68cd7ea6c23e4c2277e80464639117cf
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
          return 'No open status data available'
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

<<<<<<< HEAD
      RestaurantService.getRestaurantsByZipAndCategory(zipCode, category)
        .then((response) => {
          console.log('Response data:', response.data);

          // Assuming response.data has a structure like { businesses: [...] }
          this.businesses = response.data.businesses;
          console.log(response.data.businesses)
          this.$store.commit('SET_RESTAURANTS', this.businesses);
        })
        .catch((error) => {
          console.error('Error fetching restaurant details:', error);
        });
    },

    changeIsSelected(restaurant) {
      if (!this.selectedBusinesses.includes(restaurant)) {
        this.selectedBusinesses.push(restaurant);
      } else if (this.selectedBusinesses.includes(restaurant)) {
        this.selectedBusinesses = this.selectedBusinesses.filter((item) => item !== restaurant);
=======

          })

      } else {
        RestaurantService.getRestaurantsByZipOnly(zipCode)
          .then((response) => {
            this.businesses = response.data;
          })
>>>>>>> 3678e33f68cd7ea6c23e4c2277e80464639117cf
      }


      //TODO: exception handling
    },

    linkSelectedRestaurantsToStore() {
<<<<<<< HEAD
      this.$store.commit('SET_OUTING_RESTAURANTS', this.selectedBusinesses);
    },
=======
      this.$store.commit('SET_OUTING_RESTAURANTS', this.selectedBusinesses)
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

    getDetailsOfRestaurant(id) {
      RestaurantService.getRestaurantDetails(id)
        .then((response) => {
          this.restaurantDetails = response.data;
        })
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

      return `Opening Hours: ${formattedStart} - ${formattedEnd}`;
    },

>>>>>>> 3678e33f68cd7ea6c23e4c2277e80464639117cf

    // Function to get the day name from the day number (0-6)
    getDayName(day) {
      const daysOfWeek = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
      return daysOfWeek[day];
    },

    // Function to format time from 24-hour format to hh:mm AM/PM format
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
