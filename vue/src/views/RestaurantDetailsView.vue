<template>
    <h1>Restaurant Details View</h1>
    <RestaurantCard/>
</template>

<script>

import RestaurantCard from '../components/RestaurantCard.vue';
export default {
    components: {
        RestaurantCard
    }, 
    data(){
        return {
            restaurantId: this.$route.params.id,
            targetRestaurant: {}
        }
    },
    methods: {
        
    }
}

</script>

<style></style>