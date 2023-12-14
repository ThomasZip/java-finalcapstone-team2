<template>
    <div id="allFinal">
        <div id="pageTop">
            <h1>Your Finalists!</h1>
        </div>
      
      <router-link class="link-text" v-bind:to="{name:'my-outings'}">Go Back</router-link>
      <div v-for="(item, index) in finalistsArray" :key="item.restaurantId">
        <h2>{{ item.restaurantName }}</h2>
        <p>Thumbs Up: {{ item.thumbsUp }}</p>
        <p>Thumbs Down: {{ item.thumbsDown }}</p>
        <!-- <p>Net Thumbs: {{ netThumbs(item.thumbsUp, item.thumbsDown) }}</p> -->
        <button
          class="hoursButton"
          @click="getRestaurantDetails(index, item.longRestaurantId); item.isVisible = false"
          v-if="item.isVisible"
        >
          Get Finalist Details
        </button>
        <div v-if="restaurantDetails[index]">
          <p>Price: {{ restaurantDetails[index].price }} </p>
          <p>Phone Number: {{ restaurantDetails[index].phone }}</p>
          <p>Rating: {{ restaurantDetails[index].rating }}</p>
          <p>Location: {{ restaurantDetails[index].location.address1 }}</p>
          <p>Category: {{ restaurantDetails[index].categories[0].title }}</p>
          <img id="doneImage" v-bind:src="restaurantDetails[index].photos[1]" class="restaurant-image" />
        </div>
      </div>
    </div>
  </template>
  <script>
import RestaurantService from '../services/RestaurantService';
  export default {
    data() {
      return {
        finalistsArray: [],
        restaurantDetails: [],
      };
    },
    methods: {
      async getFinalists(outingId) {
        try {
          const response = await RestaurantService.getOutingFinalists(outingId);
          this.finalistsArray = response.data.map(item => ({ ...item, isVisible: true }));
          this.restaurantDetails = new Array(this.finalistsArray.length).fill(null);
        } catch (error) {
          console.error('Error fetching finalists:', error);
        }
      },
      netThumbs(up, down) {
        return up - down;
      },
      async getRestaurantDetails(index, longId) {
        try {
          const response = await RestaurantService.getRestaurantDetails(longId);
          this.restaurantDetails[index] = response.data;
        } catch (error) {
          console.error('Error fetching restaurant details for index', index, ':', error);
        }
      },
    },
    mounted() {
      this.getFinalists(this.$route.params.outingId);
    },
  };
  </script>
<style>
#allFinal {
    background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.2)), url('/final.jpg');
    
    color: white;
    height: 100vh;
    background-position: center;
    background-size: cover;
    overflow:scroll;
    font-family: 'Montserrat', sans-serif;
    
}
h1{
    font-family: 'Montserrat', sans-serif;
}
#pageTop{
    background-color: rgb(214,20,6, 0.7);
    border-radius: 25px;
    padding-left: 15px;
    padding-right: 15px;
    margin-left: 45px;
    margin-right: 45px;
    margin-top: 25px;
}
/* .hoursButton {
    box-shadow:inset 0px 1px 0px 0px #91B8B3;
    background:linear-gradient(to bottom, #768D87 5%, #6C7C7C 100%);
    background-color:#768d87;
    border-radius:4px;
    border:1px solid #566963;
    display:inline-block;
    cursor:pointer;
    color:#ffffff;
    
    font-size:15px;
    font-weight:bold;
    padding:32px 22px;
    padding-top: 8px;
    text-decoration:none;
    text-shadow:0px 1px 0px #2B665E;
    text-align: center;
}
.hoursButton:hover {
    background:linear-gradient(to bottom, #6C7C7C 5%, #768D87 100%);
    background-color:#6c7c7c;
}
.hoursButton:active {
    position:relative;
    top:1px;
} */

button {
    padding: 8p 14px;
    cursor: pointer;
    border: 2px solid #0f172a;
    box-shadow: 2px 2px 0 0 #ffffff, 4px 4px 0 0 #0f172a;
    transition-duration: 200ms;
    font-family: 'Montserrat', sans-serif;
    font-weight: bold;
    border-radius: 10px;
    font-size: large
}

button:hover {
    box-shadow: 3px 3px #ffffff, 6px 6px 0 0 #0f172a;
    transform: translate(-2px, -2px);
}

/* .fixed-header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    background-color: #333;
    padding: 10px;
    z-index: 1000;
} */
.restaurant-image {
  max-width: 100%;
  max-height: 500px;
  margin: 10px 0;
}
/* .background-overlay {
    position: center;
    top: 0;
    left: 0;
    width: 50%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: -1;
} */
.link-text {
    color: yellow;
}

#doneImage {
    border-style: solid;
    border-color: rgb(255,255,255);
    border-radius: 25px;
    
}
</style>

