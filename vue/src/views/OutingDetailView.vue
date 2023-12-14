<template>
    
        <div id="allOutingDetail">

            <div id="header">
                <h1>{{ outing.name }}</h1>
                <p>Vote for your favorite restaurants</p>
            </div>
            <img src="/logo.png" id="logoOuting" />
            <div id="info">
                <div id="firstPanel">
                    <h2 id="shareHeading">Share this link with your friends:</h2>
                    <h5> http://127.0.0.1:5173{{ linkToShare }}</h5>
                    <h3 v-if="permissionsRemoved">Your permissions are removed as the deadline has passed.</h3>
                    <h3>You can vote on this outing until: </h3>
                    <h5>{{ dateDeadlineToDate(outing.dateDeadline) }}</h5>
                    <h3>This outing will take place on: </h3>
                    <h5>{{ dateDeadlineToDate(outing.dateEvent) }}</h5>
                    <h3>The guests attending this event are
                        <p v-for="guest in outing.guests" v-bind:key="guest.guest_id" id="guestName">{{ guest.name }}</p>
                    </h3>

                </div>
                <div id="secondPanel">
                    <h2>Number of Votes Remaining: {{ currentNumberOfVotes }} </h2>

                    <h3 id="forConsideration">The restaurants up for consideration are:</h3>

                    <div v-for="(restaurant, index) in outing.outingRestaurants" v-bind:key="restaurant.restaurantId"
                        id="votingFlex">
                        <div id="restNameAndBtn">
                            {{ restaurant.restaurantName }}
                            <button type="button" class="btn"
                                v-on:click="getRestaurantDetails(index, restaurant.longRestaurantId)" id="detailsBtn">Click
                                For
                                Details</button>
                        </div>

                        <div v-if="restaurantDetails[index]" id="detailsDetails">
                            <h4>Price: {{ restaurantDetails[index].price }} </h4>
                            <h4>Phone Number: {{ restaurantDetails[index].phone }}</h4>
                            <h4>Rating: {{ restaurantDetails[index].rating }}</h4>
                            <h4>Location: {{ restaurantDetails[index].location.address1 }}</h4>
                            <h4>Category: {{ restaurantDetails[index].categories[0].title }}</h4>
                            <img v-bind:src="restaurantDetails[index].photos[1]" />
                        </div>
                        <p>Thumbs Up: {{ restaurant.thumbsUp }}</p>
                        <p>Thumbs Down: {{ restaurant.thumbsDown }}</p>
                        <div>
                            <button id="voteButton" class="btn" v-on:click="clickThumbsUp(restaurant)">Like </button>
                            <button id="voteButton" class="btn" v-on:click="clickThumbsDown(restaurant)">Dislike </button>
                            <button id="save" class="btn" v-on:click="sendUpdate(restaurant.restaurantId, restaurant)">Save
                                Vote</button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    
</template>
<script>
import RestaurantService from '../services/RestaurantService';
const voteButton = document.getElementById("voteButton");
export default {
    data() {
        return {
            outing: {},
            restaurantDetails: [],
            linkToShare: this.$route.fullPath,
            currentNumberOfVotes: 0,
            permissionsRemoved: false

        };
    },
    computed: {

        numberOfVotes() {
            let numberOfVotes;
            if (this.outing.outingRestaurants) {
                numberOfVotes = this.outing.outingRestaurants.length
            }
            return numberOfVotes;
        }

    },
    methods: {
        getOutingsByOutingId(outingId) {
            RestaurantService.getOutingByOutingId(outingId)
                .then((response) => {
                    this.outing = response.data;
                    this.currentNumberOfVotes = this.numberOfVotes;
                    this.checkPermissions()
                });
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
        },
        dateDeadlineToDate(dateDeadline) {
            let dueDate = new Date(dateDeadline);
            return dueDate
        },
        clickThumbsUp(restaurant) {
            if (this.currentNumberOfVotes > 0) {
                restaurant.thumbsUp++;
                this.currentNumberOfVotes--;
            }

        },
        clickThumbsDown(restaurant) {
            if (this.currentNumberOfVotes > 0) {
                restaurant.thumbsDown++;
                this.currentNumberOfVotes--;
            }

        },
        setActualNumberOfVotes() {
            this.actualNumberOfVotes++
        },
        checkPermissions() {
            const currentDate = new Date();
            const deadlineDate = new Date(this.outing.dateDeadline);
            //  voteButton.disabled = true;
            this.permissionsRemoved = currentDate > deadlineDate;
            if (this.permissionsRemoved) {
                this.$router.push({ name: 'finalists' })
            }
        },
        sendUpdate(restaurantId, restaurant) {
            RestaurantService.updateRestaurant(restaurantId, restaurant)
        }

    },
    mounted() {
        this.getOutingsByOutingId(this.$route.params.outingId);


    },

}
</script>
<style>
#allOutingDetail {
    background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('https://images.pexels.com/photos/1819669/pexels-photo-1819669.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
    background-size: cover;
    background-position: center;
    

}

#header p {
    color: #FFFFFF;
}

body {
    font-family: 'Montserrat', sans-serif;
    font-size: 50px;
    font-weight: 100;
    margin: 0px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
}

#info {
    display: flex;
    flex-direction: row;
    margin-bottom: 20px;
    gap: 10px;
    border-radius: 20px;
    margin: 20px;
    margin-right: 45px;

    padding-left: 35px;
    padding-bottom: 35px;
}

#firstPanel {
    background-color: rgb(204, 197, 201, 0.8);
    border-radius: 30px;
}

#secondPanel {
    background-color: rgb(204, 197, 201, 0.8);
    border-radius: 30px;
}

#shareHeading {
    margin-bottom: 0px;
}

h4 {
    font-size: 40px;
    margin-bottom: 0px;
    color: rgb(135, 25, 25);
}

h3 {
    margin-bottom: 0px;
}

h5 {
    margin-top: 20px;
    font-size: 40px;
    color: rgb(135, 25, 25);
}

#forPadding {
    padding-left: 55px;
    padding-right: 55px;
    padding-bottom: 55px;
}

#guestName {
    color: rgb(135, 25, 25);
}

#votingFlex {
    display: flex;
    flex-direction: column;


}

#restNameAndBtn {
    display: flex;
    flex-direction: column;
    color: rgb(135, 25, 25);
}

#detailsDetails,
#detailsDetails h4 {
    color: black;
}

#detailsBtn {
    margin-top: 25px;
    align-self: center;
}

#forConsideration {
    padding-bottom: 25px;
}

.btn {
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

.btn:hover {
    box-shadow: 3px 3px #ffffff, 6px 6px 0 0 #0f172a;
    transform: translate(-2px, -2px);
}

#logoOuting {

    height: 300px;
    width: 300px;

}</style>
