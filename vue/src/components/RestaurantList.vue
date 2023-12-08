<template>
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
      businesses: [],
      selectedBusinesses: [],
    };
  },
  methods: {
    getListOfBusinesses(zipCode, category) {
      if (category !== undefined) {
        category = category.toLowerCase();
      }

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
      }
    },

    linkSelectedRestaurantsToStore() {
      this.$store.commit('SET_OUTING_RESTAURANTS', this.selectedBusinesses);
    },

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
