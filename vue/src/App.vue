<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
#nav {
  background-color: pink;
  /* height: 25px; */
  padding: 5px;
  font-weight: bold;
}
/* 
#capstone-app {
  background-color: #871919;
  height: 100vh;
  padding: 0px;
  margin: 0px;
} */

body {
  margin: 0px;
}

</style>
