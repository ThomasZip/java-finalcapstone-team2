<template>
    <div id="search-form">
        <form class="zipcode-form" v-on:submit.prevent="onSubmit">
            <div class="field">
                <h1>Search for Restaurants</h1>
                <label for="zipcodeInput">Enter a Zipcode: </label>
                <input id="zipcodeInput" name="zipcodeInput" type="text" v-model="zipcodeInput" />
                <p>{{ zipcodeInput }}</p>
            </div>
            <div id="button-div">
                <button type="submit" value="Save" class="btn save">Go</button>
            </div>
        </form>
    </div>
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

<style>
#search-form {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 10%;
    margin-left: 10%;
    margin-right: 10%;
    padding: 20px;
    background-color: rgb(139, 228, 139);
}

h1,
p {
    text-align: center;
}

#button-div {
    text-align: center;
}

.form-input-group {
    display: flex;
    justify-content: space-between;
}</style>