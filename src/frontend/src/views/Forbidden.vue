<template v-cloak>
  <a-result
    status="403"
    title="403"
    sub-title="Sorry, you are not authorized to access this page."
  >
    <template #extra>
      <router-link to="/login" custom v-slot="{ navigate }">
        <button class="btn-lg btn-error" @click="navigate">Log in</button>
      </router-link>
    </template>
  </a-result>
</template>
