<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
  <nav>
    <router-link v-bind:to="{name: 'restaurantsSearch'}">Search for Restaurants</router-link>
  </nav>
</template>

<script>
export default {
};
</script>
