<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
  <nav id="restaurants-search-nav">
    <router-link v-bind:to="{ name: 'restaurantsSearch' }">Search for Restaurants</router-link>
  </nav>
  <nav>
    <router-link v-bind:to="{name: 'create-outing', params:{userId: this.$store.state.user.id}}">Create New Outing</router-link>
  </nav>
  <nav id="my-outings-div">
    <router-link v-bind:to="{ name: 'my-outings' }">See My Outings</router-link>
  </nav>
</template>
<script>
export default {
};
</script>
<style>
nav {
  padding: 20px;
  margin-left: 20%;
  margin-right: 20%;
  margin-top: 10px;
  text-align: center;
}

#restaurants-search-nav {
  background-color: rgb(139, 228, 139);
}

#create-outings-div {
  background-color: rgb(139, 192, 228);
}

#my-outings-div {
  background-color: rgb(207, 157, 207);
}
</style>
