<template>
    <RestaurantCard/>
    <h1>{{ restaurant.name }}</h1>
</template>

<script>
import RestaurantCard from "../components/RestaurantCard.vue";

export default {
    components: {
        RestaurantCard,
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