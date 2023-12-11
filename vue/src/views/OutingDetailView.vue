<template>
    <h1>{{ outing.name }}</h1>
    <h2>Link to share: http://127.0.0.1:5173{{ linkToShare }}</h2>
    <h3 v-if="permissionsRemoved">Your permissions are removed as the deadline has passed.</h3>
    <h3>You can vote on this outing until {{ outing.dateDeadline }}</h3>
    <h3>This outing will take place on {{ outing.dateEvent }}</h3>
    <h3>The guests attending this event are
        <p v-for="guest in outing.guests" v-bind:key="guest.guest_id">{{ guest.name }}</p>
    </h3>
    <h2>The restaurants up for consideration are:
        <div v-for="(restaurant) in outing.outingRestaurants" v-bind:key="restaurant.restaurantId">
            {{ restaurant.restaurantName }}
            <button type="button" v-on:click="getRestaurantDetails(restaurant.longRestaurantId)">Click here for restaurant
                details</button>
            <div v-if="restaurantDetails">
                <p>Price: {{ restaurantDetails.price }}</p>
                <p>Phone Number: {{ restaurantDetails.phone }}</p>
            </div>
        </div>
    </h2>
    <div v-if="!permissionsRemoved">
        <button id="voteButton">Like  </button>
        <button id="voteButton">Dislike  </button>
    </div>
</template>
<script>
import RestaurantService from '../services/RestaurantService';
const voteButton = document.getElementById("voteButton");
export default {
    data() {
        return {
            outing: {},
            restaurantDetails: [],
            linkToShare: this.$route.fullPath
        };
    },
    computed: {
        permissionsRemoved() {
            const currentDate = new Date();
            const deadlineDate = new Date(this.outing.dateDeadline);
          //  voteButton.disabled = true;
            return currentDate > deadlineDate;
        }
    },
    methods: {
        getOutingsByOutingId(outingId) {
            RestaurantService.getOutingByOutingId(outingId)
                .then((response) => {
                    this.outing = response.data;
                });
        },
        getRestaurantDetails(longId) {
            RestaurantService.getRestaurantDetails(longId)
                .then((response) => {
                    this.restaurantDetails = response.data;
                });
        }
    },
    mounted() {
        this.getOutingsByOutingId(this.$route.params.outingId);
    },
}
</script>
<style></style>