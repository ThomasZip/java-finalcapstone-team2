<template>
    <div id="backRound">
        <div id="search-form">
            <form class="zipcode-form" v-on:submit.prevent="onSubmit">
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
                <div id="button-div">
                    <button type="submit" value="Save" class="btn save">Go</button>
                </div>
            </form>
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
}

#search-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 20px;
    flex-grow: 1;
    height: 100vh;


}
.zipcode-form{
    background-color: #fbf17a;
}

h1,
p {
    text-align: center;
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
