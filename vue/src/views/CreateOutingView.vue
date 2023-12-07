<template>
    <h1>create outing</h1>
    <div class="input-div">
        <label for="nameInput">Name Your Outing:</label>
        <input id="nameInput" name="nameInput" type="text" v-model="outing.name" v-bind:required="true" />
    </div>
    <div class="input-div">
        <label for="zipcodeInput">Enter a Zipcode (required):</label>
        <input id="zipcodeInput" name="zipcodeInput" type="text" v-model="outing.zipcode" />
    </div>
    <div class="input-div">
        <label for="deadlineInput">Date of final decision:</label>
        <input id="deadlineInput" name="deadlineInput" type="datetime-local" v-model="outing.dateDeadline"
            v-bind:required="true" />
    </div>
    <div class="input-div">
        <label for="eventInput">Date of actual event:</label>
        <input id="eventInput" name="eventInput" type="datetime-local" v-model="outing.dateEvent" v-bind:required="true" />
    </div>
    <div class="outing-restaurants" v-for="item in iteratedRestaurants" v-bind:key="item.id">
        {{ item.name }}

    </div>
    <nav>
        <router-link v-bind:to="{ name: 'restaurantsSearch' }">Search Restaurant to Add to Your Outing
        </router-link>
    </nav>
    <nav>
        <router-link v-bind:to="{ name: 'restaurantsSearch' }">Create Your Outing
        </router-link>
    </nav>
</template>
<!-- Does this push user to restaurants list? -->

<script>
export default {
    data() {
        return {
            outing: {
                name: '',
                zipcode: '',
                dateDeadline: '',
                dateEvent: '',
                creator: '',
                guests: [],
                restaurants: this.$store.state.outingRestaurants,
                id: ''
            },
            iteratedRestaurants: []
        }
    },


    methods: {
        // getNamesOfRestaurants() {
        //     let restaurantNames = this.outing.restaurants[0].map(item => item.name);
        //     return restaurantNames.join(', ');
        // }, 

        iterateThroughOutingRestaurants() {
            let uniqueRestaurantsMap = new Map();

            this.outing.restaurants.flat().forEach((restaurant)=>{
                if(!uniqueRestaurantsMap.has(restaurant.id)){
                    uniqueRestaurantsMap.set(restaurant.id, restaurant);
                }
            });

            this.iteratedRestaurants = Array.from(uniqueRestaurantsMap.values());
        },

    },

    mounted() {
        this.iterateThroughOutingRestaurants();
    }



};
</script>

<style></style>