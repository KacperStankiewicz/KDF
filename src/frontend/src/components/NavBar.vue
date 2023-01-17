<template v-cloak>
  <nav>
    <router-link :to="{ name: 'home' }">Home</router-link> |
    <router-link :to="{ name: 'book' }">Online Reservation</router-link>
  </nav>
</template>

<script>
export default {
  name: "NavBar",
};
</script>
