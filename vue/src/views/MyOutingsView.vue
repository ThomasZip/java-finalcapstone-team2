<template>
    <h1>see my current outings</h1>
    <div v-for="item in outings" v-bind:key="item.outingId">
        <nav>
            <router-link v-bind:to="{name: 'outing-detail', params: {outingId: item.outingId}}">{{ item.name }}</router-link>
        </nav>
        <p>
            {{ item.zipCode }}
        </p>
        <p>
            {{ item.dateDeadline}}
        </p>
        <p>
            {{ item.dateEvent }}
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