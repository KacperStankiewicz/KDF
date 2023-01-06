import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import { VueReCaptcha } from "vue-recaptcha-v3";
createApp(App).use(router).use(VueReCaptcha, { siteKey: "6LeEKIwjAAAAAKvm6OtnZhJYCGYfMeXqPYyN5nSn" }).mount('#app');
