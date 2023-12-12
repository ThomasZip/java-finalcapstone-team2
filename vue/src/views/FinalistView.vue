<template>
    <h1>Your Finalists!!</h1>
    <div v-for="(item, index) in finalistsArray" v-bind:key="item.restaurantId">
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
        <button type="button" v-on:click="getRestaurantDetails(index, item.longRestaurantId)">Get Finalist Details</button>
        <div v-if="restaurantDetails[index]">
            <p>Price: {{ restaurantDetails[index].price }} </p>
            <p>Phone Number: {{ restaurantDetails[index].phone }}</p>
            <p>Rating: {{ restaurantDetails[index].rating }}</p>
            <p>Location: {{ restaurantDetails[index].location.address1 }}</p>
            <p>Category: {{ restaurantDetails[index].categories[0].title }}</p>
            <img v-bind:src="restaurantDetails[index].photos[1]" />
        </div>

    </div>
</template>

<script>
import RestaurantService from '../services/RestaurantService';
export default {
    data() {
        return {
            finalistsArray: [],
            restaurantDetails: []
        }
    },


    methods: {
        getFinalists(outingId) {
            RestaurantService.getOutingFinalists(outingId)
                .then((response) => {
                    this.finalistsArray = response.data;
                })
        },
        netThumbs(up, down) {
            return up - down;
        },
        async getRestaurantDetails(index, longId) {
            try {
                const response = await RestaurantService.getRestaurantDetails(longId);
                this.rating = response.data.rating;
                this.location = response.data.location;
                this.category = response.data.category;
                console.log(this.rating);
                this.restaurantDetails.splice(index, 1, response.data);
            } catch (error) {
                console.error('Error fetching restaurant details:', error);
            }
        }
    },
    mounted() {
        this.getFinalists(this.$route.params.outingId);
    },



}

</script>

<style></style>