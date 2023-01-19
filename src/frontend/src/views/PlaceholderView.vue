<template>
  <h1>Hello</h1>
  <p>This is just a placeholder</p>
</template>

<script>
export default {
  name: "Placeholder",
  async created() {
    const jwt = localStorage.getItem("jwt");

    if (!jwt) {
      this.$router.push("/forbidden");
    }
  },
};
</script>
