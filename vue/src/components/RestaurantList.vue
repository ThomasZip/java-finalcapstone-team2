<template>
  <nav>
    <!-- adds restaurants to your outing and pushes user to create outing page -->
    <router-link v-bind:to="{ name: 'create-outing', params: { userId: this.$store.state.user.id } }"
      v-on:click="linkSelectedRestaurantsToStore">Send Selected
      Restaurants to Your Outing
    </router-link>
  </nav>

  <!-- Hold individual sections for each restaurant ex: div v-for -->
  <div v-for="(restaurant, index) in businesses.businesses" v-bind:key="restaurant.id" id="restaurant-list">
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

      },
      selectedBusinesses: []
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
    }
  },
  methods: {
    getListOfBusinesses(zipCode, category) {
      if (category !== undefined) {
        category = category.toLowerCase();
      }

      RestaurantService.getRestaurantsByZipAndCategory(zipCode, category)
        .then((response) => {
          this.businesses = response.data;
          

        })

      //TODO: exception handling
    },
    linkSelectedRestaurantsToStore() {
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
