<template>
    <body id="myOutingsBody">
        <h1>Your Outings</h1>
        <img src="src/img/Logo version 2 White.png" id="logo" />
        <main>
            <div id="wrapper">
                <div v-for="item in outings" v-bind:key="item.outingId" id="outing">
                    <nav>
                        <router-link v-bind:to="{ name: 'outing-detail', params: { outingId: item.outingId } }"
                            id="outingNav">{{ item.name
                            }}</router-link>
                    </nav>
                    <p id="zipCode">
                        Zip Code: {{ item.zipCode }}
                    </p>
                    <p id="deadline">
                        Voting Ends: {{ dateDeadlineToDate(item.dateDeadline) }}
                    </p>
                    <p id="eventDate">
                        Event Date: {{ dateDeadlineToDate(item.dateEvent) }}
                    </p>
                </div>
            </div>
        </main>
    </body>
</template>

<script>

import RestaurantService from '../services/RestaurantService';
export default {
    data() {
        return {
            outings: {}
        };
    },
    methods: {
        getOutingsByCreatorId(creatorId) {
            RestaurantService.getOutingsByUserId(creatorId)
                .then((response) => {
                    this.outings = response.data;
                });
        },

        dateDeadlineToDate(dateDeadline) {
            let dueDate = new Date(dateDeadline);
            return dueDate
        }
    },
    computed: {
        localUserId() {
            return this.$store.state.user.id;
        }
    },
    mounted() {
        this.getOutingsByCreatorId(this.localUserId);
    }

}


</script>

<style>
#myOutingsBody {
    background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('https://images.pexels.com/photos/1148565/pexels-photo-1148565.jpeg');
    background-size: cover;
    width: 100vw;
    height: auto;
    background-position: center;
    overflow: hidden;
    font-family: 'Montserrat', sans-serif;
    font-size: 50px;
    font-weight: 100;
    margin-right: 50px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 70px;

}
 

h1 {
    color: #FFFFFF;
    text-shadow: 0 0 2px #000000;
    font-weight: 100;
    font-family: 'Spicy Rice', serif;
    margin-bottom: 0px;
    margin-top: 0;
}

#logo {
    max-height: 300px;
    max-width: 300px;
    display: flex;
    justify-content: center;
    margin-right: 20px;
}

main {
    margin: 20px;
}

main p {
    color: rgb(0, 0, 0)
}

#outing {
    display: flex;
    flex-direction: column;
    margin-bottom: 20px;
    background-color: rgb(204, 197, 201, 0.6);
    border-radius: 20px;
}

#outingNav {
    margin-bottom: 0px;
    padding-bottom: 0px;
}
#zipCode {
    margin-bottom: 0px;
}
#deadline {
    margin-bottom: 0px;
} 
</style>