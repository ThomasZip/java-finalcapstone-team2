<template>
    <div id="search-form">
        <form class="zipcode-form" v-on:submit.prevent="onSubmit">
            <div class="field">
                <h1>Search for Restaurants</h1>
                <div class="input-div">
                <label for="zipcodeInput">Enter a Zipcode (required):<br>&nbsp;</label>
                <input id="zipcodeInput" name="zipcodeInput" type="text" v-model="zipcodeInput"  @keydown.space.prevent/>
                </div>
                <div class="input-div">
                <label for="userCategory">Enter a Cuisine (optional):<br> i.e. Greek, tacos, noodles, etc&nbsp;&nbsp;</label>
                <input id="userCategoryId" name="userCategory" type="text" v-model="userCategory" v-bind:required="false" @keydown.space.prevent/>
                </div>
            </div>
            <div id="button-div">
                <button type="submit" value="Save" class="btn save">Go</button>
            </div>
        </form>
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
        onSubmit() {
            if(this.userCategory === ''){
                this.goToRestaurantDisplayViewZip();

            }else{this.goToRestaurantDisplayViewZipAndCategory();}
            
        },
        // addZipcode() {
        //     this.$store.commit('SET_ZIPCODE', this.zipcodeInput);
        // },
        goToRestaurantDisplayViewZipAndCategory() {
            this.$router.push({ name: 'restaurant-display-zip-category', params: { zipCode: this.zipcodeInput, category: this.userCategory }});
        }, 

        goToRestaurantDisplayViewZip(){
            this.$router.push({name: 'restaurant-display-zip', params:{zipCode: this.zipcodeInput}})
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

h1, p {
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

}

</style>
