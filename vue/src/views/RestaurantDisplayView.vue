<template>
    <div id="main-div">
        <nav>
<<<<<<< HEAD
            <router-link v-bind:to="{ name: 'restaurantsSearch' }">Back to Restaurant Search</router-link>
        </nav>
=======
            <router-link v-bind:to="{ name: 'restaurantsSearch' }">Back to Restaurant Search
            </router-link>
        </nav>
        

>>>>>>> afb2cbeeaceff4060bebb6a51b7d6f331a5bec09
        <h1>Restaurants List</h1>
        <RestaurantList />
    </div>
</template>
<script>
import RestaurantList from '../components/RestaurantList.vue';
export default {
    components: {
        RestaurantList
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











