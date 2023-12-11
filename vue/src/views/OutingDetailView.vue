<template>
    <h1>{{ outing.name }}</h1>
    <h2>Link to share: http://127.0.0.1:5173{{ linkToShare }}</h2>
    <h3 v-if="permissionsRemoved">Your permissions are removed as the deadline has passed.</h3>
    <h3>You can vote on this outing until {{ dateDeadlineToDate(outing.dateDeadline) }}</h3>
    <h3>This outing will take place on {{ dateDeadlineToDate(outing.dateEvent) }}</h3>
    <h3>The guests attending this event are
        <p v-for="guest in outing.guests" v-bind:key="guest.guest_id">{{ guest.name }}</p>
    </h3>
    <h4>Number of Votes Remaining {{ currentNumberOfVotes }} </h4>
    <h2>The restaurants up for consideration are:
        <div v-for="(restaurant) in outing.outingRestaurants" v-bind:key="restaurant.restaurantId">
            {{ restaurant.restaurantName }}
            <button type="button" v-on:click="getRestaurantDetails(restaurant.longRestaurantId)">Click here for restaurant
                details</button>
            <div v-if="restaurantDetails">
                <p>Price: {{ restaurantDetails.price }} </p>
                <p>Phone Number: {{ restaurantDetails.phone }}</p>
                <p>Thumbs Up: {{ restaurant.thumbsUp }}</p>
                <p>Thumbs Down: {{ restaurant.thumbsDown }}</p>
                <div v-if="!permissionsRemoved">
                    <button id="voteButton" v-on:click="clickThumbsUp(restaurant)">Like </button>
                    <button id="voteButton" v-on:click="clickThumbsDown(restaurant)">Dislike </button>
                    <button id="save" v-on:click="sendUpdate(restaurant,restaurant.restaurantId)">Save Vote</button>
                </div>
            </div>
        </div>
    </h2>
</template>
<script>
import RestaurantService from '../services/RestaurantService';
const voteButton = document.getElementById("voteButton");
export default {
    data() {
        return {
            outing: {},
            restaurantDetails: [],
            linkToShare: this.$route.fullPath,
            currentNumberOfVotes: 0,
            permissionsRemoved: false




        };
    },
    computed: {

        numberOfVotes() {
            let numberOfVotes;
            if (this.outing.outingRestaurants) {
                numberOfVotes = this.outing.outingRestaurants.length
            }
            return numberOfVotes;
        }

    },
    methods: {
        getOutingsByOutingId(outingId) {
            RestaurantService.getOutingByOutingId(outingId)
                .then((response) => {
                    this.outing = response.data;
                    this.currentNumberOfVotes = this.numberOfVotes;
                    this.checkPermissions()
                });
        },
        getRestaurantDetails(longId) {
            RestaurantService.getRestaurantDetails(longId)
                .then((response) => {
                    this.restaurantDetails = response.data;
                });
        },
        dateDeadlineToDate(dateDeadline) {
            let dueDate = new Date(dateDeadline);
            return dueDate
        },
        clickThumbsUp(restaurant) {
            if (this.currentNumberOfVotes > 0) {
                restaurant.thumbsUp++;
                this.currentNumberOfVotes--;
            }

        },
        clickThumbsDown(restaurant) {
            if (this.currentNumberOfVotes > 0) {
                restaurant.thumbsDown++;
                this.currentNumberOfVotes--;
            }

        },
        setActualNumberOfVotes() {
            this.actualNumberOfVotes++
        },
        checkPermissions() {
            const currentDate = new Date();
            const deadlineDate = new Date(this.outing.dateDeadline);
            //  voteButton.disabled = true;
            this.permissionsRemoved = currentDate > deadlineDate;
        },
        sendUpdate(restaurant, restaurantId){
            RestaurantService.updateRestaurant(restaurant, restaurantId)
        }

    },
    mounted() {
        this.getOutingsByOutingId(this.$route.params.outingId);


    },

}
</script>
<style></style>