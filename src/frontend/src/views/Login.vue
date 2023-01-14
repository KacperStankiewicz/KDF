<template>
  <div class="login">
    <h1>Login:</h1>
  </div>
  <form @submit.prevent="signIn">
    <label>Email:</label>
    <input type="email" id="email" v-model="loginData.email" />

    <label>Password:</label>
    <input type="password" id="password" v-model="loginData.password" />
    <br />
    <div class="login">
      <button class="button2">Sign in</button>
    </div>
  </form>
</template>

<script>
import axios from "axios";
export default {
  name: "Login",
  name: "SignIn",
  data() {
    return {
      loginData: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    signIn() {
      axios
        .post("https://jsonplaceholder.typicode.com/posts", this.loginData)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style>
.login {
  text-align: center;
}
.button2 {
  margin-top: 5px;
  border-radius: 4px;
  background-color: green;
  border: none;
  color: #ffffff;
  text-align: center;
  font-size: 28px;
  padding: 20px;
  width: 200px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}
</style>
