<template>
  <div id="homeView">
    <div class="home">
      <h1>Home</h1>

    </div>
    <section id="option-cards">

      <div class="restaurants-search-div" v-on:click="sendToSearch">
        <p>Click the Icon to Search Restaurants for Your Outing</p>
        <nav>
          <img src="public\search.png" />
        </nav>
      </div>

      <div class="my-outings-div">
        <p>See The Outings You've Created</p>
        <nav v-on:click="sendToMyOutings">
          <img src="public\list.png" />
        </nav>
      </div>



    </section>
  </div>
</template>
<script>

export default {
  methods: {
    sendToSearch() {
      this.$router.push({ name: 'restaurantsSearch' });
    },
    sendToMyOutings() {
      this.$router.push({ name: 'my-outings' })
    }
  }



};
</script>
<style>
#option-cards {
  display: flex;
  flex-direction: column;
  justify-content: space-between;

}

#option-cards .restaurants-search-div,
#option-cards .my-outings-div {
  display: flex;
  flex-direction: column;
  border: 5px;
  border-color: black;
  border-style: solid;
  padding: 1%;
  margin: 2%;
  width: 10%;
  height: 10%;

}

#option-cards p {
  margin-top: 0;
}

#option-cards .my-outings-div {
  align-self: center;
  padding: 1.5%;
  background-color: #D61406

}

#option-cards .restaurants-search-div {
  align-self: flex-start;
  background-color: #FBF17A;
  

}

nav {
  padding: 20px;
  text-align: center;


}

#homeView {
  background-image: url("C:\Users\Student\workspace\java-finalcapstone-team2\vue\public\home.jpg");
  background-size: cover;
  background-position: center;
  height: 100vh;

}
</style>
