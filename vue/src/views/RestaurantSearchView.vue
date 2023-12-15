<template>
    <div id="backRound">
        <div id="search-form">
            <section class="zipcode-form" >
                <div class="field">
                    <h1>Search for Restaurants</h1>
                    <div class="input-div">
                        <label for="zipcodeInput">Enter a Zipcode (required):<br>&nbsp;</label>
                        <input id="zipcodeInput" name="zipcodeInput" type="text" v-model="zipcodeInput"
                            @keydown.space.prevent />
                    </div>
                    <div class="input-div">
                        <label for="userCategory">Enter a Cuisine (optional):<br> i.e. Greek, tacos, noodles,
                            etc&nbsp;&nbsp;</label>
                        <input id="userCategoryId" name="userCategory" type="text" v-model="userCategory"
                            v-bind:required="false" @keydown.space.prevent />
                    </div>
                </div>
                <nav id="button-div">
                    <img src='/search.png' v-on:click="onSubmit"/>
                </nav>
            </section>
        </div>
    </div>
</template>
<script>

export default {
    data() {
        return {
            zipcodeInput: '',
            userCategory: ''
        };
    },
    methods: {
        //if a category is used calls local api endpoint w/ {zip} & {category}, if no category used calls endpoint w/ {zip}
        onSubmit() {
            if (this.userCategory === '') {
                this.goToRestaurantDisplayViewZip();

            } else { this.goToRestaurantDisplayViewZipAndCategory(); }

        },
        goToRestaurantDisplayViewZipAndCategory() {
            this.$router.push({ name: 'restaurant-display-zip-category', params: { zipCode: this.zipcodeInput, category: this.userCategory } });
        },
        goToRestaurantDisplayViewZip() {
            this.$router.push({ name: 'restaurant-display-zip', params: { zipCode: this.zipcodeInput } })
        }
    }
};
</script>

<style>
#backRound {
    background-image: url("C:\Users\Student\workspace\java-finalcapstone-team2\vue\public\searchback.JPG");
    background-size: cover;
    background-position: center;
    height: 100vh;
    overflow: hidden;
}

#search-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 20px;
    flex-grow: 1;
    height: 100vh;
    color: rgb(241, 197, 95)



}
.zipcode-form{
    background-color: rgb(135, 25, 25, 0.9);
    border-radius: 25px;
}

h1,
p {
    text-align: center;
}

h1 {
    font-family: 'Montserrat', sans-serif;
}

#button-div {
    text-align: center;
    padding-top: 15px;
}

button {
    height: 30px;
    width: 90px;
}

.input-div {
    padding: 5px;
    display: flex;
    justify-content: space-between;

}</style>
