<template>
    <h1>see my current outings</h1>
    <router-link v-bind:to="{name:'home'}" > Go Back</router-link> 
    <div v-for="item in outings" v-bind:key="item.outingId">
        <nav>
            <router-link v-bind:to="{name: 'outing-detail', params: {outingId: item.outingId}}">{{ item.name }}</router-link>
        </nav>
        <p>
            Zip Code: {{ item.zipCode }}
        </p>
        <p>
            Voting Ends: {{ dateDeadlineToDate(item.dateDeadline)  }}
        </p>
        <p>
            Event Date: {{ dateDeadlineToDate(item.dateEvent) }}
        </p>



    </div>
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

        dateDeadlineToDate(dateDeadline){
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

<style></style>