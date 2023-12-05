<template>
<!-- Hold individual sections for each restaurant ex: div v-for -->
<div>
  <div v-for="object in businesses" v-bind:key="object.id">
    <h2>{{ object.name }}</h2>
    <p>Rating: {{ object.rating }}</p>
    <p>Price: {{ object.price }}</p>
    <p>Address: {{ object.location.address1 }}, {{ business.location.city }}, {{ object.location.state }}</p>
    <p>Phone: {{ object.display_phone }}</p>
    <img v-bind:src="object.image_url"  style="max-width: 200px; max-height: 150px;">
    <hr/>

  </div>
</div>

</template>
<script>
    import RestaurantService from '../services/RestaurantService';
    export default {
 
  data() {
    return {
      businesses: {
        
      }
    };
  },

  methods: {
    getListOfBusinesses(id){
      RestaurantService.getRestaurantsByZip(id)
      .then((response)=>{
        this.businesses = response.data;
      })
      // error handling needs done here
    }, 
    created(){
      this.getListOfBusinesses(this.$route.params.zipCode)
    }
  }
  

  
};

</script>
<style>

</style>