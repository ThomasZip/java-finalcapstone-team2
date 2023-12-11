<template>
    <h1>{{ outing.name }}</h1>
    <h3>You can vote on this outing until {{ outing.dateDeadline }}</h3>
    <h3>This outing will take place on {{ outing.dateEvent }}</h3>
    <h3>The guests attending this event are 
        <p v-for="guest in outing.guests" v-bind:key="guest.guest_id">{{ guest.name }}</p>
    </h3>
    <h2>The restaurants up for consideration are:
        <div v-for="(restaurant) in outing.outingRestaurants" v-bind:key="restaurant.restaurantId">
            {{ restaurant.restaurantName }}
            <button type="button" v-on:click="getRestaurantDetails(restaurant.longRestaurantId)">Click here for restaurant details</button>
            <div v-if="restaurantDetails">
                <p>Price: {{ restaurantDetails.price }}</p>
                <p>Phone Number: {{ restaurantDetails.phone }}</p>
            </div>
        </div>
        
    </h2>
        
    <div>


    </div>

</template>

<script>
import RestaurantService from '../services/RestaurantService';

export default{
    
    data() {
        return {
            outing: {},
            restaurantDetails: []
        };
    },
    methods: {
        getOutingsByOutingId(outingId) {
            RestaurantService.getOutingByOutingId(outingId)
                .then((response) => {
                this.outing = response.data;
            });
        }, 
        getRestaurantDetails(longId){
            RestaurantService.getRestaurantDetails(longId)
            .then((response)=>{
                this.restaurantDetails = response.data;
            });
        }
    },
    
    mounted() {
        this.getOutingsByOutingId(this.$route.params.outingId);
    },
    
}



</script>

<style>

</style>