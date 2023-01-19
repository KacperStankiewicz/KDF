<template v-cloak>
  <div class="login-container">
    <div v-if="error" class="alert-container login-alert-container">
      <a-alert
        message="Wrong email or password"
        description="If you keep having the problem with logging in, please contact us."
        type="error"
        show-icon
      />
    </div>
    <h1>Login:</h1>
    <div class="form-container">
      <form @submit.prevent="signIn">
        <label>Email:</label>
        <input type="email" id="email" v-model="loginData.email" />
        <label>Password:</label>
        <input type="password" id="password" v-model="loginData.password" />
        <br />
        <div class="login-btn-container">
          <button class="btn-lg btn-success">Log in</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Login",
  data() {
    return {
      error: false,
      loginData: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    async signIn() {
      try {
        const params = new URLSearchParams();
        params.append("username", this.loginData.email);
        params.append("password", this.loginData.password);

        const response = await axios.post("/api/login", params);

        if (response.data.access_token) {
          localStorage.setItem("jwt", response.data.access_token);

          this.$router.push("/placeholder");
        } else throw new Error("Could not log in");
      } catch (err) {
        console.log(err);
        if (err.response.status !== 200) {
          this.error = true;
        }
      }
    },
  },
};
</script>
<style>
.login-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-top: 20%;
}

#app > div > h1 {
  margin: 10px 0;
}

.login-btn-container {
  display: flex;
  justify-content: center;
}
</style>
