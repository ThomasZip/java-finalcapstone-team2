<template>
    <h1>Search for Restaurants</h1>
    <!--v-on:submit.prevent="addZipcode"-->
    <form class="zipcode-form" v-on:submit.prevent="onSubmit">
        <div class="field">
            <label for="zipcodeInput">Enter a Zipcode: </label>
            <input id="zipcodeInput" name="zipcodeInput" type="text" v-model="zipcodeInput" />
            <p>{{ zipcodeInput }}</p>
        </div>
        <button type="submit" value="Save" class="btn save">Go</button>
    </form>
</template>
<script>
import RestaurantCard from '../components/RestaurantCard.vue';
export default {
    data() {
        return {
            zipcodeInput: ''
        };
    },
    methods: {
        onSubmit() {
            this.addZipcode();
            this.goToRestaurantDisplayView();
        },
        addZipcode() {
            this.$store.commit('SET_ZIPCODE', this.zipcodeInput);
        },
        goToRestaurantDisplayView() {
            this.$router.push({ name: 'restaurant-display', params: { zipCode: this.zipcodeInput } });
        }
    }
};
</script>
<style></style>