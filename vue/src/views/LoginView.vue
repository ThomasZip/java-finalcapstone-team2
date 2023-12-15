<template>
  <div id="something">
    <div id="login">
      <form v-on:submit.prevent="login">
        <div id="signIn">
          <h1 id="signH1">Please Sign In</h1>
          <img src="/user-icon.png" />
        </div>
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div id="button-div">
          <button type="submit">Sign in</button>
        </div>
        <div id="signup-div">
          <p>
            <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
          </p>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import authService from "../services/AuthService";
export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style scoped>
.form-input-group {
  margin-bottom: 1rem;
  border-radius: 20px;
}

label {
  margin-right: 0.5rem;
  padding: 10px;
}

#login {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10%;
  margin-left: 35%;
  margin-right: 30%;
  background-color: rgb(204, 197, 201);
  opacity: 70%;
  width: 600px;
  height: 500px;
  border: 3px solid rgb(0, 0, 0);
  border-radius: 10px;
  padding-bottom: 20px;
}

#signIn {
  display: flex
}

h1 {
  font-weight: bold;
  margin: 10%;
}

#signH1{
  font-size: 50px;
}

#button-div {
  text-align: center;
}

.form-input-group {
  display: flex;
  justify-content: space-between;
}

img {
  height: 150px;
  width: 150px;
}

input[type="text"],
input[type="password"] {
  width: 200px;
  padding: 10px;
  border: 2px solid black;
  border-radius: 8px;
}

.form-input-group label {
  font-weight: bold;
  font-size: 20px;
}

#signIn {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#signIn h1 {
  margin-bottom: 5px;
}

#signIn img {
  margin: 0px;
  margin-top: 5px;
}

#button-div {
  text-align: center;
  margin-top: 20px;
  margin-left: 50px;
  padding-bottom: 5px;;
}

#button-div button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #8D6B2B;
  color: rgb(15, 14, 13);
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  border-style: outset;
  margin-right: 60px;
}

#button-div button:hover {
  background-color: #B32A00;
}

#signup-div p {
  font-size: 16px;
  color: #EB2222;
  margin-left: 50px;
  color: #000;
  margin-right: 40px;
}

#signup-div p a {
  color: #333;
  /* Set the text color */
  font-size: 20px;
  font-weight: bold;
}

#signup-div p a:hover {
  color: #EB2222;
  /* Change the text color on hover */
  text-decoration: underline;
  /* Underline the link on hover */
  cursor: pointer;
}
</style>