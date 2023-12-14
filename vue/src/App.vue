<template>
  <div id="capstone-app">
    <div id="nav">
      <div id="navHome">
        <img src="/White Home Icon.png"/>
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      </div>
      <div id="navLogout">
        <img src="/White Logout Icon.png"/>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>
<style>
#nav {
  display: flex;
  padding: 0px;
  font-family: 'Libre Baskerville', serif;
  font-size: xx-large;
  width: 100vw;
  
}
#navHome {
  display:flex;
  flex-direction: column;
  flex-grow: 1;
  text-align: center;
  background-color: rgb(0, 0, 0, 0.9);
  border-style: solid;
  border-top: none;
  border-left: none;
  border-bottom: none;
  border-color: rgb(255, 255, 255);
}
#navHome:hover {
  background-color: rgb(214, 20, 6, 0.9);
}
#nav img {
  height: 100px;
  width: 100px;
  align-self: center;
  padding-top: 10px;
}
#navLogout {
  
  display:flex;
  flex-direction: column;
  flex-grow: 1;
  text-align: center;
  background-color: rgb(0, 0, 0, 0.9);
  border-style: solid;
  border-top: none;
  border-left: none;
  border-bottom: none;
  border-color: rgb(255, 255, 255);
  padding: 0px;
  margin: 0px;
}
#navLogout:hover {
  background-color: rgb(214, 20, 6, 0.9);
}
a {
  color: #FBF17A;
}
a:hover {
  color: rgb();
}
.router-link-exact-active {
  color: #F1C55F
}
</style>