<template>
    <h1>Restaurants List</h1>
    <RestaurantCard />

</template>
<script>
import restaurantService from '../services/RestaurantService';
import RestaurantCard from '../components/RestaurantCard.vue';

export default {
    components: {
        RestaurantCard
    },
    data() {
        return {
            restaurants: [],
            zipcode: this.$store.state.zipcodeInput
        }
    },
    methods: {
        created() {
            restaurantService.getRestaurantsByZip(this.zipcode)
                .then((response) => {
                    this.restaurants = response.data;
                    console.log(this.restaurants)
                })
        }
    }
}


</script>
<style></style>