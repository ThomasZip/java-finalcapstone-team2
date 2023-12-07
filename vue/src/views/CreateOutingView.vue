<template>
    <h1>create outing</h1>
    <div class="input-div">
        <label for="nameInput">Name Your Outing:</label>
        <input id="nameInput" name="nameInput" type="text" v-model="outing.name" v-bind:required="true" />
    </div>
    <div class="input-div">
        <label for="zipcodeInput">Enter a Zipcode (required):</label>
        <input id="zipcodeInput" name="zipcodeInput" type="text" v-model="outing.zipCode" />
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
    <div class="outing-restaurants" v-for="item in outing.outingRestaurants" v-bind:key="item.id">
        {{ item.name }}

    </div>
    <nav>
        <router-link v-bind:to="{ name: 'restaurantsSearch' }">Search Restaurant to Add to Your Outing
        </router-link>
    </nav>
    <nav>
        <router-link v-bind:to="{ name: 'home' }" v-on:click="clearOutingRestaurantsFromStore">Finalize Your Outing
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
                zipCode: '',
                dateDeadline: '',
                dateEvent: '',
                creator: '',
                guests: [],
                outingRestaurants: [],
                id: ''
            },
            localStorageOfStoreRestaurants: this.$store.state.storeOfRestaurantsInOuting
        }
    },


    methods: {
        // takes the array of arrays from the storeOfRestaurantsInOuting variable in the store and removes it's nested property and concatenates the arrays into one array for display on this view. 
        iterateThroughOutingRestaurants() {
            this.outing.outingRestaurants = this.localStorageOfStoreRestaurants.flat();
        },

        clearOutingRestaurantsFromStore(){
            this.$store.commit('CLEAR_OUTING_RESTAURANTS')
        }
        


    },

    mounted(){
        this.iterateThroughOutingRestaurants();
    }



};
</script>

<style></style>