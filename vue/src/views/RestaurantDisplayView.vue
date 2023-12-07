<template>
    <div id="main-div">
        <nav>
            <router-link v-bind:to="{name: 'restaurantsSearch'}">Back to Restaurant Search</router-link>
        </nav>
    <h1>Restaurants List</h1>
    <RestaurantList/>
    </div>
</template>
<script>

import RestaurantList from '../components/RestaurantList.vue';

export default {
    components: {
        RestaurantList
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











