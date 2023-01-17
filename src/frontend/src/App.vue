<template v-cloak>
  <router-view />
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body {
  margin: 10px 0;
  background-color: white;
}

nav {
  padding: 15px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
  padding: 10px;
  border-radius: 4px;
}

nav a.router-link-exact-active {
  color: white;
  background: crimson;
}

.form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

form {
  display: flex;
  flex-direction: column;
  width: 50%;
  max-width: 420px;
  background: white;
  text-align: left;
  border-radius: 10px;
}

label {
  color: #aaa;
  display: inline-block;
  margin: 10px;
  font-size: 0.6em;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
}

label:after {
  content: " *";
  color: red;
}

.book-button {
  margin-top: 40px;
  border-radius: 4px;
  background-color: crimson;
  border: none;
  color: #ffffff;
  text-align: center;
  font-size: 26px;
  width: 200px;
  height: 60px;
  transition: all 0.5s;
  cursor: pointer;
}
</style>
