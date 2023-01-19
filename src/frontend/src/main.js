import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { VueReCaptcha } from "vue-recaptcha-v3";
import Antd from "ant-design-vue";
import DefaultLayout from "./components/DefaultLayout";
import CustomLayout from "./components/CustomLayout";

import "ant-design-vue/dist/antd.css";

createApp(App)
  .use(router)
  .use(Antd)
  .use(VueReCaptcha, {
    siteKey: "6LepxAokAAAAAMdSZuntGXU11PvGtqkBHXPzUvbc",
    loaderOptions: { autoHideBadge: true },
  })
  .component("default-layout", DefaultLayout)
  .component("custom-layout", CustomLayout)
  .mount("#app");
