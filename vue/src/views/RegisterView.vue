<template>
  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <div id="button-div">
        <button type="submit">Create Account</button>
      </div>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>
<script>
import authService from '../services/AuthService';
export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      const password = this.user.password;
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else if (/[a-z]/.test(password) == false) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password be 8 characters long and include at least one uppercase letter, lowercase letter, and number.';
      }
      else if (/[A-Z]/.test(password) == false) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password be 8 characters long and include at least one uppercase letter, lowercase letter, and number.';
      } else if (password.length < 8) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password be 8 characters long and include at least one uppercase letter, lowercase letter, and number.';
      } else if (/\d/.test(password) == false) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password be 8 characters long and include at least one uppercase letter, lowercase letter, and number.';
      }
      else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>
<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
#register {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10%;
  margin-left: 30%;
  margin-right: 30%;
  background-color: rgb(184, 167, 167);
}
h1,
p {
  text-align: center;
}
#button-div {
  text-align: center;
}
.form-input-group {
  display: flex;
  justify-content: space-between;
  /* justify-content: space-between; */
}
</style>