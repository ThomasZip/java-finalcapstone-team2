<template>
    <h1>Restaurants List</h1>
    <RestaurantCard/>

</template>
<script>
import RestaurantCard from '../components/RestaurantCard.vue';

export default {
    components: {
        RestaurantCard
    },
    data() {
        return {
            restaurants: {
                businesses: []
            }
            
        }
    },
    methods: {
       
    }
}


</script>
<style>
#main-div {
  background-color: rgb(139, 228, 139);
  margin-top: 10%;
  margin-left: 10%;
  margin-right: 10%;
  padding: 20px;
}
</style>