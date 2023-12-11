<template>
    <h1>Your Finalists!!</h1>
    <div v-for="item in finalistsArray" v-bind:key="item.restaurantId">
        <h2>
            {{ item.restaurantName }}
        </h2>
        <p>
           Thumbs Up: {{ item.thumbsUp }}
        </p>
        <p>
            Thumbs Down: {{ item.thumbsDown }}
        </p>
        <p>
            Net Thumbs: {{ netThumbs(item.thumbsUp, item.thumbsDown) }}
        </p>
        <button type="button">Get Finalist Details</button>

    </div>

</template>

<script>
import RestaurantService from '../services/RestaurantService';
export default{
    data(){
        return{
            finalistsArray:[]
        }
    },


    methods:{
        getFinalists(outingId){
            RestaurantService.getOutingFinalists(outingId)
            .then((response)=>{
                this.finalistsArray = response.data;
            })
        }, 
        netThumbs(up, down){
        return up - down;
    }
    },
    mounted(){
        this.getFinalists(this.$route.params.outingId);
    },
    


}

</script>

<style>

</style>