import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { VueReCaptcha } from "vue-recaptcha-v3";
import Antd from "ant-design-vue";

import "ant-design-vue/dist/antd.css";

createApp(App)
  .use(router)
  .use(Antd)
  .use(VueReCaptcha, {
    siteKey: "6LeEKIwjAAAAAKvm6OtnZhJYCGYfMeXqPYyN5nSn",
    loaderOptions: { autoHideBadge: true },
  })
  .mount("#app");
