<template>
    <RestaurantList/>
    <h1>{{ restaurant.name }}</h1>
</template>

<script>
import RestaurantList from "../components/RestaurantList.vue";

export default {
    components: {
        RestaurantList
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