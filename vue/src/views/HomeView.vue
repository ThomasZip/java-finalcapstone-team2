
<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
  <nav>
    <router-link v-bind:to="{name: 'restaurantsSearch'}">Search for Restaurants</router-link>
  </nav>
  <nav>
    <router-link v-bind:to="{name: 'create-outing', params:{userId: this.$store.state.user.id}}">Create New Outing</router-link>
  </nav>
  <nav>
    <router-link v-bind:to="{name: 'my-outings'}">See My Outings</router-link>
  </nav>
</template>
<script>
export default {
};
</script>
