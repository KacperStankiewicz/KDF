<template><h1>Hello</h1></template>

<script>
import axios from "axios";
export default {
  name: "Dashboard",
  async created() {
    const jwt = localStorage.getItem("jwt");

    if (!jwt) {
      this.$router.push("/forbidden");
    } else {
      try {
        const response = await axios.get("user", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        });

        console.log({ response });
      } catch (err) {
        console.log(err);
      }
    }
  },
};
</script>
