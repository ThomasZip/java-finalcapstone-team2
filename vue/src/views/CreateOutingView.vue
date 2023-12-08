<template>
    <div id="main-div-outing">
        <h1>Create Outing</h1>
        <div class="input-div">
            <label for="nameInput">Name Your Outing:&nbsp;</label>
            <input id="nameInput" name="nameInput" type="text" v-model="outing.name" v-bind:required="true" />
        </div>
        <div class="input-div">
            <label for="zipcodeInput">Enter a Zipcode (required):&nbsp;</label>
            <input id="zipcodeInput" name="zipcodeInput" type="text" v-model="outing.zipcode" />
        </div>
        <div class="input-div">
            <label for="deadlineInput">Date of final decision:&nbsp;</label>
            <input id="deadlineInput" name="deadlineInput" type="datetime-local" v-model="outing.dateDeadline"
                v-bind:required="true" />
        </div>
        <div class="input-div">
            <label for="eventInput">Date of actual event:&nbsp;</label>
            <input id="eventInput" name="eventInput" type="datetime-local" v-model="outing.dateEvent"
                v-bind:required="true" />
        </div>
        <nav id="nav-div-search">
            <router-link v-bind:to="{ name: 'restaurantsSearch' }">Search Restaurant to Add to Your Outing
            </router-link>
        </nav>
        <div class="outing-restaurants" v-for="item in iteratedRestaurants" v-bind:key="item.id">
            {{ item.name }}

        </div>

        <!-- <div id="button-div">
            <button type="submit">Create Outing</button>
        </div> -->
    </div>
    <!-- <nav>
        <router-link v-bind:to="{ name: 'restaurantsSearch' }">Search Restaurant to Add to Your Outing
        </router-link>
    </nav> -->
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
                creator: this.$store.state.user.id ,
                guests: [],
                restaurants: this.$store.state.storeOfRestaurantsInOuting,
                id: ''
            },
            localStorageOfStoreRestaurants: this.$store.state.storeOfRestaurantsInOuting,

            iteratedRestaurants: []
        }
    },


    methods: {
        // getNamesOfRestaurants() {
        //     let restaurantNames = this.outing.restaurants[0].map(item => item.name);
        //     return restaurantNames.join(', ');
        // }, 

    
        // iterateThroughOutingRestaurants() {
        //     this.outing.outingRestaurants = this.localStorageOfStoreRestaurants.flat();
        // },

        clearOutingRestaurantsFromStore(){
            this.$store.commit('CLEAR_OUTING_RESTAURANTS')
        },

        iterateThroughOutingRestaurants() {
            let uniqueRestaurantsMap = new Map();

            this.outing.restaurants.flat().forEach((restaurant) => {
                if (!uniqueRestaurantsMap.has(restaurant.id)) {
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

<style>
#main-div-outing {
    margin-top: 10%;
    margin-left: 10%;
    margin-right: 10%;
    padding: 10px;
    background-color: rgb(139, 192, 228);
}

h1,
p {
    text-align: center;
}

#button-div {
    text-align: center;
    padding-top: 15px;
}

.input-div {
    padding: 5px;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    padding: 5px;
}

button {
    text-align: center;
    width: 150px;
    height: 50px;
}

#nav-div-search {
    background-color: rgb(139, 228, 139);
    padding: 1%;
    margin-top: 20px;
    margin-right: 20%;
    margin-left: 20%;
    text-align: center;
}

.outing-restaurants {
    background-color: rgb(218, 235, 122);
    margin-left: 20%;
    margin-right: 20%;
    padding: 1%;

}
</style>