<template>
    <div id="main-div">
        <nav>
<<<<<<< HEAD
            <router-link v-bind:to="{ name: 'restaurantsSearch' }">Back to Restaurant Search
            </router-link>
        </nav>
        

        <h1>Restaurants List</h1>
        <RestaurantList />
=======
            <router-link v-bind:to="{name: 'restaurantsSearch'}">Back to Restaurant Search</router-link>
        </nav>
    <h1>Restaurants List</h1>
    <RestaurantList/>
>>>>>>> 21d27b5cc72695206a2d92771eaead41cafa1103
    </div>
</template>
<script>

import RestaurantList from '../components/RestaurantList.vue';

export default {
    components: {
        RestaurantList,
        //RestaurantCard
    },
    data() {
        return {
            restaurants: {
                businesses: []
            }
        }
    },
    methods: {
        

    }
}
</script>
<style>
#main-div {
    background-color: rgb(139, 228, 139);
    margin-top: 10%;
    margin-left: 5%;
    margin-right: 5%;
    padding: 20px;
}
</style>











