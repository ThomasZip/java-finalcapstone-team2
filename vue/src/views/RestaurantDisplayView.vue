<template>
    <div id="main-div">
        <RestaurantList />
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

    overflow: hidden;
    /* a very long image is needed here */
}

</style>











