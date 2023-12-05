<template>
<!-- Hold individual sections for each restaurant ex: div v-for -->

<div v-for="restaurant in businesses.businesses" v-bind:key="restaurant.id">
 <h3>{{ restaurant.name }}</h3>
 <p>Rating: {{ restaurant.rating }}</p>
 <p>Price: {{ restaurant.price }}</p>
 <div>
  <img v-bind:src="restaurant.image_url" id="pic"/>
 </div>

 <p>Coordinates: {{ restaurant.coordinates.longitude }}, {{ restaurant.coordinates.latitude }}</p>
 
 <img v-bind:src="`https://api.mapbox.com/styles/v1/mapbox/streets-v12/static/${restaurant.coordinates.longitude},${restaurant.coordinates.latitude},14/200x200?access_token=pk.eyJ1IjoiYmFvYW5qbmd1eWVuIiwiYSI6ImNscHNwdHU1czA1M2Uyd3JwNWZwd2I2N2sifQ.J1sb_URnd6sCtiH7ITM2Wg`" />

  
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
  max-height: 100px;
 
}

</style>