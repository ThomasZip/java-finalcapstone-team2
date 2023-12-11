<template>
    <div id="main-div-outing">
        <h1>Create Outing</h1>
        <div class="input-div">
            <label for="nameInput">Name Your Outing:&nbsp;</label>
            <input id="nameInput" name="nameInput" type="text" v-model="outing.name" v-bind:required="true" />
        </div>
        <div class="input-div">
            <label for="zipcodeInput">Enter a Zipcode:&nbsp;</label>
            <input id="zipcodeInput" name="zipcodeInput" type="text" v-model="outing.zipCode" />
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
        <nav class="nav-div-search">
            <router-link v-bind:to="{ name: 'restaurantsSearch' }">Search Restaurant to Add to Your Outing
            </router-link>
        </nav>
        <div class="outing-list" v-for="item in outing.restaurants" v-bind:key="item.id">
            {{ item.name }}

        </div>
        <div id="add-guests" class="nav-div-search">
            <button id="add-guest-btn" v-on:click="toggleIsFormShown">Add Guests</button>
        </div>
        <div class="outing-list" v-for="item in guests" v-bind:key="item.id">
            {{ item.name }} ({{ item.email }})

        </div>
        <form v-show="isFormShown" @submit.prevent>
            <div class="input-div">
                <label for="guestName">Guest's Name:</label>
                <input id="guestName" type="text" v-model="guest.name"/>
            </div>
            <div class="input-div">
                <label for="guestEmail">Guest's Email:</label>
                <input id="guestEmail" type="text" v-model="guest.email"/>
            </div>
            <div id="button-div">
                <button type="submit" v-on:click="addGuest">Save</button>
                <!-- click to submit, adds guest's name -->
            </div>
        </form>

        <!-- <div id="button-div">
            <button type="submit">Create Outing</button>
        </div> -->
    </div>
    <!-- <nav>
        <router-link v-bind:to="{ name: 'restaurantsSearch' }">Search Restaurant to Add to Your Outing
        </router-link>
    </nav> -->
    <nav>
        <router-link v-bind:to="{ name: 'home' }" v-on:click="clearOutingRestaurantsFromStoreAndAddOutingToSql">Finalize Your Outing
        </router-link>
    </nav>
</template>
<!-- Does this push user to restaurants list? -->

<script>
import RestaurantService from '../services/RestaurantService';
export default {
    data() {
        return {
            outing: {
                name: '',
                zipCode: '',
                dateDeadline: '',
                dateEvent: '',
                creator: this.$store.state.user.id,
                guests: [],
                restaurants: [],
                
            },
            localStorageOfStoreRestaurants: this.$store.state.storeOfRestaurantsInOuting,

            iteratedRestaurants: [],

            isFormShown: false,

            guest: {
                name: '',
                email: ''
            }
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

        clearOutingRestaurantsFromStoreAndAddOutingToSql() {
            this.addOutingToSql(this.outing, this.currentToken);
            this.$store.commit('CLEAR_OUTING_RESTAURANTS');
            
        },

        iterateThroughOutingRestaurants() {
            let uniqueRestaurantsMap = new Map();

            this.localStorageOfStoreRestaurants.flat().forEach((restaurant) => {
                if (!uniqueRestaurantsMap.has(restaurant.id)) {
                    uniqueRestaurantsMap.set(restaurant.id, restaurant);
                }
            });

            this.outing.restaurants = Array.from(uniqueRestaurantsMap.values());
        },

        toggleIsFormShown() {
            this.isFormShown = !this.isFormShown;
        },

        resetForm() {
            this.guest = {};
            this.toggleIsFormShown();
        },

        clearOutingGuestsFromStore() {
            this.$store.commit('CLEAR_GUESTS_RESTAURANTS')
        },

        iterateThroughGuests() {
            let uniqueGuestsMap = new Map();

            this.outing.guests.flat().forEach((guest) => {
                if (!uniqueGuestsMap.has(guest.email)) {
                    uniqueGuestsMap.set(guest.email, guest);
                }
            });

            this.guests = Array.from(uniqueGuestsMap.values());
        },

        linkNewGuestToStore() {
            this.$store.commit('SET_OUTING_GUESTS', this.guests)
        },

        addGuest() {
            this.outing.guests.push(this.guest);
            this.resetForm();
            this.iterateThroughGuests();
        }, 

        addOutingToSql(outing, currentToken){
            RestaurantService.addOuting(outing, currentToken)
        }

    },

    mounted() {
        this.iterateThroughOutingRestaurants();
        this.iterateThroughGuests();
    },

    computed: {
        currentToken(){
            return this.$store.state.token;
        }
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

.nav-div-search {
    background-color: rgb(139, 228, 139);
    padding: 1%;
    margin-top: 20px;
    margin-right: 20%;
    margin-left: 20%;
    text-align: center;
}

.outing-list {
    background-color: rgb(218, 235, 122);
    margin-left: 20%;
    margin-right: 20%;
    padding: 1%;

}
</style>