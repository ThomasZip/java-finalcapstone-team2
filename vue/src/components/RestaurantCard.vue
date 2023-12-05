<template>
  <!-- Hold individual sections for each restaurant ex: div v-for -->
  <div v-for="restaurant in businesses.businesses" v-bind:key="restaurant.id" id="restaurant-card">
    <div id="pic-div">
      <img v-bind:src="restaurant.image_url" id="pic" />
    </div>
    <div id="text-div">
      <div id="name">
        <router-link v-bind:to="{ name: 'details' }">
          <h3>{{ restaurant.name }}</h3>
        </router-link>
      </div>
      <div id="rating">
        <p>Rating: {{ restaurant.rating }}</p>
      </div>
      <div id="price">
        <p>Price: {{ restaurant.price }}</p>
      </div>
    </div>
  </div>
</template>
  <script>
      import RestaurantService from '../services/RestaurantService';
      export default {
    data() {
      return {
        businesses: {
          //restaurants: []
        }
      };
    },
    methods: {
      getListOfBusinesses(zipCode){
        RestaurantService.getRestaurantsByZip(zipCode)
          .then((response)=>{
            this.businesses = response.data;
          })
          //TODO: exception handling
      }
    },
    created() {
      this.getListOfBusinesses(this.$route.params.zipCode)
    }
  };
  </script>

  <style>
  #pic{
    max-height: 200px;
    max-width: 200px;
  }
  #restaurant-card {
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