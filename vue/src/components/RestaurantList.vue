<template>
  <nav>
    <router-link v-bind:to="{ name: 'create-outing' }" v-on:click="linkSelectedRestaurantsToStore">Send Selected
      Restaurants to Your Outing
    </router-link>
  </nav>

  <!-- Hold individual sections for each restaurant ex: div v-for -->
  <div v-for="restaurant in businesses.businesses" v-bind:key="restaurant.id" id="restaurant-list">
    <div id="pic-div">
      <img v-bind:src="restaurant.image_url" id="pic" />
    </div>
    <div id="text-div">
      <div id="name">
        <router-link v-bind:to="{ name: 'details', params: { id: restaurant.id } }" v-on:click="setSelectedRestaurant">
          <h3>{{ restaurant.name }}</h3>
        </router-link>
      </div>
      <div id="rating">
        <p>Rating: {{ restaurant.rating }}</p>
      </div>
      <div id="price">
        <p>Price: {{ restaurant.price }}</p>
      </div>
      <div>
        <label>Add Restaurant to Outing?</label>
        <input type="checkbox" v-model="selectedBusinesses" v-bind:value="restaurant" />
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
      businesses: {
        //restaurants: []
      },
      selectedBusinesses: []
    };
  },
  methods: {
    getListOfBusinesses(zipCode, category) {
      if (category !== undefined) {
        category = category.toLowerCase();
      }

      RestaurantService.getRestaurantsByZipAndCategory(zipCode, category)
        .then((response) => {
          this.businesses = response.data;
          this.$store.commit('SET_RESTAURANTS', this.businesses)

        })

      //TODO: exception handling
    },

    changeIsSelected(restaurant) {
      if (!this.selectedBusinesses.includes(restaurant)) {
        this.selectedBusinesses.push(restaurant)
      } else if (this.selectedBusinesses.includes(restaurant)) {
        this.selectedBusinesses = this.selectedBusinesses.filter((item) => item !== restaurant)
      }
    },
    linkSelectedRestaurantsToStore() {
      this.$store.commit('SET_OUTING_RESTAURANTS', this.selectedBusinesses)
    }
  },
  setSelectedRestaurant(restaurant) {
    this.$store.commit('ADD_SELECTED_RESTAURANT', restaurant);
  },
  seeRestaurantCard() {

  },
  created() {
    this.getListOfBusinesses(this.$route.params.zipCode, this.$route.params.category)
  }

}


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
  grid-template-areas:
    "pic text-div"
  ;
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
</style>
