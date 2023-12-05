<template>
<!-- Hold individual sections for each restaurant ex: div v-for -->

<div v-for="restaurant in businesses.businesses" v-bind:key="restaurant.id">
 <h3>{{ restaurant.name }}</h3>
 <p>Rating: {{ restaurant.rating }}</p>
 <p>Price: {{ restaurant.price }}</p>
 <div>
  <img v-bind:src="restaurant.image_url" id="pic"/>
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
    getListOfBusinesses(zipCode, category){
      RestaurantService.getRestaurantsByZipAndCategory(zipCode,category)
        .then((response)=>{
          this.businesses = response.data;
        })
        //TODO: exception handling
    }
    
  },
  created() {
    this.getListOfBusinesses(this.$route.params.zipCode, this.$route.params.category)
  }
  

  
};

</script>
<style>
#pic{
  max-height: 100px;
 
}

</style>