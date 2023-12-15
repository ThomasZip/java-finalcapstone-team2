<template>
  <div id="homeView">
    <div id="topGrid">
      <img src="src/img/Logo Version 2 White.png" id="logoImage" />
    </div>
      
    
    
    <div class="outing-list-display-home">
      <div class="home-outings" v-on:click="sendToMyOutings">
        
        <nav id="listImage">
          <img src="/list.png" />
        </nav>
        <p class="homeText">See Your Outings</p>
      </div>
    </div>

    <div class="search">
      <div class="restaurants-search-div" v-on:click="sendToSearch">
        
        <nav>
          <img src="/search.png" />
        </nav>
        <p class="homeText">Search Restaurants</p>
      </div>

    </div>


    <div id="StepBy">
      <p>Step 1: Search restaurants and find your favorite spots</p>
      <p>Step 2: Give your outing a cool name and invite your crew</p>
      <p>Step 3: Share the link and have your friends vote</p>
      <p>Step 4: View the winner</p>
    </div>








  </div>
</template>
<script>

export default {
  data() {
    return {
      isMenuShown: false
    }
  },
  methods: {
    sendToSearch() {
      this.$router.push({ name: 'restaurantsSearch' });
    },
    sendToMyOutings() {
      this.$router.push({ name: 'my-outings' })
    },
    changeMenu() {
      this.isMenuShown = !this.isMenuShown;
    }
  }



};
</script>
<style>
#homeView {
  /* display: flex;
  flex-direction: column; */
  /* justify-content: flex-start;
  align-items: flex-start; */
  height: 100vh;
  width: 100vw;
  background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('/home.jpg');
  background-size: cover;
  background-position: center;
  overflow: hidden;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 0.5fr 1fr;
  grid-template-areas:
    "image image"
    "outings search"
    "stepBy stepBy";


}


.search {
  display: flex;
  /* flex-direction: column; */
  align-items: flex-start;
  justify-content: center;
  /* height: 100vh; */
  margin-top: 0px;
  padding: 0;
  grid-area: search;
  
}

.outing-list-display-home {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  /* height: 100vh; */
  margin-top: 0px;
  padding: 0;
  grid-area: outings;
}
#topGrid{
  grid-area: image;
  display: flex;
  align-items: center;
  justify-content: center;
  align-self: center;
  padding-right: 25px;
}

#logoImage{
  
  
  height: 300px;
  width: 300px;
  
}
#StepBy{
  grid-area: stepBy;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  align-self: center;
  background-color: rgb(204, 197, 201, 0.7);
  border-radius: 20px;
  margin-right: 300px;
  margin-left: 300px;
  font-size: x-large;
  font-family: 'Montserrat', sans-serif;
  font-weight: 400;
}



.restaurants-search-div {
  /* display: flex; */
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 200px;
  width: 200px;
  border-radius: 50px;
  background-color: rgb(214, 20, 6, 0.8);



}



.home-outings {


  background-color: rgb(251, 241, 122, 0.80);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 200px;
  width: 200px;
  border-radius: 50px;
}
#listImage{
  padding-top: 30px;
}

nav {
  /* padding: 20px; */
  text-align: center;



}

body {
  padding: 0px;
  margin: 0;
}

.homeText{
  font-family: 'Montserrat', sans-serif;
  font-weight: bolder;
  font-size: large;
}



/* .my-outings-div {
  max-width: 75px;
  max-height: 100px;
  background-color: rgb(251, 241, 122, 0.5);
  border-radius: 10px;
  padding-right: 20px;
  margin-right: 5px;
  flex-grow: 1;




} */
</style>
