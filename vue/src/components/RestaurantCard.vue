<template>
<!-- Hold individual sections for each restaurant ex: div v-for -->
<div>
  <!-- <div v-for="business in businesses" v-bind:key="business.id">
    <h2>{{ business.business.name }}</h2>
    <p>Rating: {{ business.rating }}</p>
    <p>Price: {{ business.price }}</p>
    <p>Address: {{ business.location.address1 }}, {{ business.location.city }}, {{ business.location.state }}</p>
    <p>Phone: {{ business.display_phone }}</p>
    <img v-bind:src="business.image_url"  style="max-width: 200px; max-height: 150px;">
    <hr/>

  </div> -->
  <div v-for="restaurant in businesses.businesses" v-bind:key="restaurant.id">
    <h2>{{ restaurant.name }}</h2>
    <p>{{ restaurant.location.address1 }}</p>
  
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
h1 {
  text-align: center;
  font-size: 70px;
}

div.h2 {
  text-align: left;
  font-size: 60px;
}

p {
  text-align: left;
  font-size: 15px;
}

</style>