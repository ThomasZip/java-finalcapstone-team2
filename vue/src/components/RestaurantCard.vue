<template>
<!-- Hold individual sections for each restaurant ex: div v-for -->
<h1>test</h1>
<div>
  <div v-for="business in businesses" v-bind:key="business.id">
    <h2>{{ business.name }}</h2>
    <p>Rating: {{ business.rating }}</p>
    <p>Price: {{ business.price }}</p>
    <p>Address: {{ business.location.address1 }}, {{ business.location.city }}, {{ business.location.state }}</p>
    <p>Phone: {{ business.display_phone }}</p>
    <img v-bind:src="business.image_url"  style="max-width: 200px; max-height: 150px;">
    <hr/>

  </div>
</div>

</template>
<script>
    import RestaurantService from '../services/RestaurantService';
    export default {
 
  data() {
    return {
      businesses: [],
      zipcodeInput: this.$store.state.zipcodeInput
    };
  },

  methods: {
    getListOfBusinesses(){
      RestaurantService.getRestaurantsByZip(this.$store.state.zipcodeInput)
      .then((response)=>{
        this.businesses = response.data;
      })
      // error handling needs done here
    }
  }
  

  
};

</script>
<style>

</style>