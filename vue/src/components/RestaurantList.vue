<template>
  <div id="everything">
    <div id="topBar">
      <h1 id="restaurantListName">Restaurants List</h1>
      <nav>
        <router-link v-bind:to="{ name: 'create-outing', params: { userId: this.$store.state.user.id } }"
          v-on:click="linkSelectedRestaurantsToStore">Send Selected Restaurants to Your Outing
        </router-link>
      </nav>

    </div>

    <div v-for="(restaurant, index) in businesses.businesses" :key="restaurant.id" id="restaurant-list">

      <div v-if="!isBackShown[index]" id="cardFront">
        <div id="name">
          <h3>{{ restaurant.name }}</h3>
        </div>

        <div id="rating">
          <p>Rating: {{ restaurant.rating }}</p>
        </div>
        <div id="price">
          <p>Price: {{ restaurant.price }}</p>
        </div>
        <div id="category">
          <p>Category: {{ restaurant.categories[0].title }}</p>
        </div>

        <div id="add">
          <label>Add Restaurant to Outing?</label>
          <input type="checkbox" class="checkbox" v-model="selectedBusinesses" :value="restaurant" />
        </div>
        <div v-on:click="flipCard(index)" id="flipIconFront">
          <!-- <p>
          Flip For More Info
        </p> -->
          <nav>
            <img src="/rotate.png" class="flippyDippy" id="flipImageFront" />
          </nav>
          <p>Press for More Info</p>
        </div>

      </div>


      <div id="cardBack" v-if="isBackShown[index]">
        <div id="pic-div">
          <img :src="restaurant.image_url" id="pic" />
        </div>

        <div id="show-hours-btn">
          <!-- <button type="button" @click="getDetailsOfRestaurant(index)">Show Store Hours</button> -->
          <p id="openHourz" v-if="restaurantDetails[index] && restaurantDetails[index].hours && restaurantDetails[index].hours[0]">
            <span v-for="(openHour, openIndex) in restaurantDetails[index].hours[0].open" :key="openIndex">
              {{ formatOpenHours(openHour) }}<br />
            </span>
            <!-- <p>Open Status: {{ getTrueOpenStatus(restaurantDetails[index].hours[0].is_open_now) }}</p> -->
          </p>
        </div>


        <div id="phoneNumber">
          <p>Phone Number: {{ restaurant.display_phone }}</p>
        </div>
        <div id="options">
          <p>Options: {{ formatTransactionTypes(restaurant.transactions) }}</p>
        </div>
        <div id="address">
          <p>Address: {{ restaurant.location.address1 }}, {{ restaurant.location.city }},
            {{ restaurant.location.state }}</p>
        </div>
        <!-- <img id="map"
        v-bind:src="`https://api.mapbox.com/styles/v1/mapbox/streets-v12/static/${restaurant.coordinates.longitude},${restaurant.coordinates.latitude},14/200x200?access_token=pk.eyJ1IjoiYmFvYW5qbmd1eWVuIiwiYSI6ImNscHNwdHU1czA1M2Uyd3JwNWZwd2I2N2sifQ.J1sb_URnd6sCtiH7ITM2Wg`" />
      <button v-on:click="flipCard(index)">Flip card</button> -->
        <div v-on:click="flipCard(index)" id="flipIconBack" >
          <!-- <p>
          Flip For More Info
        </p> -->
          <nav>
            <img src="/rotate.png" class="flippyDippy" />
          </nav>

        </div>


      </div>
    </div>
    <nav>
      <router-link v-bind:to="{ name: 'restaurantsSearch' }">Back to Restaurant Search</router-link>
    </nav>
  </div>
</template>

<script>
import RestaurantService from '../services/RestaurantService';

export default {
  data() {
    return {
      businesses: {},
      selectedBusinesses: [],
      restaurantDetails: [],
      isBackShown: Array(20).fill(false)
    };
  },
  computed: {
    openStatus() {
      return this.businesses.businesses.map(restaurant => {
        if (restaurant.isClosed) {
          return 'Closed';
        } else if (!restaurant.isClosed) {
          return 'Open Now';
        } else {
          return 'No open status data available';
        }
      });
    },
  },
  methods: {
    getListOfBusinesses(zipCode, category) {
      if (category) {
        category = category.toLowerCase();
        RestaurantService.getRestaurantsByZipAndCategory(zipCode, category)
          .then((response) => {
            this.businesses = response.data;
          });
      } else {
        RestaurantService.getRestaurantsByZipOnly(zipCode)
          .then((response) => {
            this.businesses = response.data;
          });
      }
    },
    linkSelectedRestaurantsToStore() {
      this.$store.commit('SET_OUTING_RESTAURANTS', this.selectedBusinesses);
    },
    formatTransactionTypes(transactions) {
      if (transactions.length > 0) {
        return transactions
          .map(transaction => transaction
            .split('_')
            .map(word => word.charAt(0).toUpperCase() + word.slice(1))
            .join(' ')
          )
          .join(', ');
      } else {
        return 'Dine-In Only';
      }
    },
    setSelectedRestaurant(restaurant) {
      this.$store.commit('ADD_SELECTED_RESTAURANT', restaurant);
    },
    getDetailsOfRestaurant(index) {
      const restaurantId = this.businesses.businesses[index].id;

      RestaurantService.getRestaurantDetails(restaurantId)
        .then((response) => {
          this.restaurantDetails[index] = response.data;
        });
    },
    formatOpenHours(openHour) {
      const formatTime = (time) => {
        const formattedTime = new Date();
        formattedTime.setHours(Math.floor(time / 100));
        formattedTime.setMinutes(time % 100);
        return formattedTime.toLocaleTimeString('en-US', { hour: 'numeric', minute: '2-digit' });
      };
      const formattedStart = formatTime(openHour.start);
      const formattedEnd = formatTime(openHour.end);

      return `Opening Hours: ${this.getDayName(openHour.day)} ${formattedStart} - ${formattedEnd}`;

    },
    getTrueOpenStatus(openStatus) {
      if (openStatus == true) {
        return 'Open Now';
      } else {
        return 'Closed';
      }
    },


    getDayName(day) {
      const daysOfWeek = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
      return daysOfWeek[day];
    },
    formatTime(time) {
      const hours = Math.floor(time / 100);
      const minutes = time % 100;
      const ampm = hours >= 12 ? 'PM' : 'AM';
      const formattedHours = hours % 12 === 0 ? 12 : hours % 12;
      return `${formattedHours}:${minutes.toString().padStart(2, '0')} ${ampm}`;
    },
    flipCard(index) {
      if (!this.isBackShown[index]) {
        this.getDetailsOfRestaurant(index);
      }
      this.isBackShown[index] = !this.isBackShown[index];


    }
  },
  created() {
    console.log('Component created');
    this.getListOfBusinesses(this.$route.params.zipCode, this.$route.params.category);
  },
};
</script>

<style>
/* #pic {
  max-height: 200px;
  max-width: 200px;
} */

#everything {
  /* background-image: url('https://images.pexels.com/photos/311004/pexels-photo-311004.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'); */
  background: linear-gradient( rgba(135, 25, 25, 0.5), rgba(0, 0, 0, 0.5));
  background-size: contain;
  background-position: center;
}

.checkbox {
  height:45px;
  width:45px;
  margin-top:10px
}
.flippyDippy:hover{
  transform: scale(1.1) rotate(90deg);
  background-color: rgb(255,255,255, 0.1);
  border-radius: 50%;
  cursor:pointer;
}
#restaurant-list {
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  padding: 10px;
  margin: 2%;
  min-height: 220px;

}

#cardFront {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "name name"
    "rating price"
    "category addBtn"
    "flip flip";


}

/* #hours {
  grid-area: "hours";
  text-align: center;
} */

#name {
  grid-area: name;
  display: flex;
  justify-content: center;
}

#rating {
  grid-area: rating;
}

#price {
  grid-area: price;
}

#category {
  grid-area: category;
}

#add {
  grid-area: addBtn;
  display: flex;
  justify-content: center;
  align-self: center;
}



#flipIconFront {
  grid-area: flip;
  /* display: flex; */
  justify-content: center;
  align-items: center;


}


.rotateImage {
  max-height: 75px;
}

#cardBack {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    ". pic ."
    "address  . showHours"
    "phoneNumber flipBtn showHours "
    "options . showHours";

}

#openHourz {
  font-size: smaller;
}

#openHourz span {
  gap:10px;
}

#pic-div {
  grid-area: pic;
  display: flex;
  align-self: center;
  justify-content: center;
  height: 150px;
  width: 150px;
  margin: auto;


}

#pic{
  width: 350px;
  height: 300px;
  border-radius: 25px;
  border: 5px;
  border-style: solid;
  border-color: rgb(255,255,255);
}
#show-hours-btn {
  grid-area: showHours;
  display: flex;
  justify-content: center;
}

#phoneNumber {
  grid-area: phoneNumber;
}

#options {
  grid-area: options;
}

#address {
  grid-area: address;
}

#flipIconBack {
  grid-area: flipBtn;
  display: flex;
  align-items: center;
  justify-content: center;

}

#topBar {
  background-color: rgb(0, 0, 0, 0.75);
}

#restaurantListName {
  color: white;
}

/* #map{
  grid-area: map;
  
  display: flex;
  align-content: center;
  justify-content: center;
} */






/* #show-hours-btn {
  grid-area: "hours";
} */

/* #map {
  grid-area: "map";
  display: flex;
  justify-content: center;
  align-items: center;
} */



/* #pic-div {
  grid-area: "pic";
  display: flex;
  justify-content: center;
  align-items: center;
} */
</style>





//     formatOpenHours(openHour) {
  //   const currentDay = new Date().getDay(); // 0 for Sunday, 1 for Monday, and so on
  //   const todayOpenHour = openHour.find(day => day.day === currentDay);

  //   if (todayOpenHour) {
  //     const formatTime = (time) => {
  //       const formattedTime = new Date();
  //       formattedTime.setHours(Math.floor(time / 100));
  //       formattedTime.setMinutes(time % 100);
  //       return formattedTime.toLocaleTimeString('en-US', { hour: 'numeric', minute: '2-digit' });
  //     };

  //     const formattedStart = formatTime(todayOpenHour.start);
  //     const formattedEnd = formatTime(todayOpenHour.end);

  //     return `Opening Hours: ${formattedStart} - ${formattedEnd}`;
  //   } else {
  //     return 'Closed today';
  //   }
  // },
