<template v-cloak>
  <a-layout id="custom-layout">
    <a-layout-sider v-model:collapsed="collapsed" collapsible>
      <div class="logo">
        <img src="../../public/Bowling-Logo-580x387.png" />
      </div>
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <router-link to="/placeholder" custom v-slot="{ navigate }">
          <a-menu-item key="1" @click="navigate">
            <pie-chart-outlined />
            <span>Analytics</span>
          </a-menu-item>
        </router-link>
        <router-link to="/reservation" custom v-slot="{ navigate }">
          <a-menu-item key="2" @click="navigate">
            <highlight-outlined />
            <span>Reservations</span>
          </a-menu-item>
        </router-link>
        <router-link to="/placeholder" custom v-slot="{ navigate }">
          <a-sub-menu key="3" @click="navigate">
            <template #title>
              <span>
                <home-outlined />
                <span>Facilities</span>
              </span>
            </template>
            <a-menu-item key="4">U7 Gdynia</a-menu-item>
            <a-menu-item key="5">U7 Sopot</a-menu-item>
          </a-sub-menu>
        </router-link>
        <router-link to="/placeholder" custom v-slot="{ navigate }">
          <a-menu-item key="6" @click="navigate">
            <team-outlined />
            <span>Workers</span>
          </a-menu-item>
        </router-link>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-content style="margin: 0 16px">
        <a-breadcrumb style="margin: 16px 0">
          <h1>{{ pageHeader }}</h1>
        </a-breadcrumb>
        <div
          :style="{ padding: '24px', background: '#fff', minHeight: '360px' }"
        >
          <slot></slot>>
        </div>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Ant Design ©2018 Created by Ant UED
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>

<script>
import { ref } from "vue";
import { useRoute } from "vue-router";
import {
  PieChartOutlined,
  HomeOutlined,
  UserOutlined,
  TeamOutlined,
  HighlightOutlined,
} from "@ant-design/icons-vue";

export default {
  name: "CustomLayout",
  components: {
    PieChartOutlined,
    HomeOutlined,
    UserOutlined,
    TeamOutlined,
    HighlightOutlined,
  },
  data() {
    const routeObj = useRoute();
    const route = routeObj.name;
    const routeName = `${route[0].toUpperCase()}${route
      .slice(1)
      .toLowerCase()}`;

    return {
      collapsed: ref(false),
      selectedKeys: ref(["1"]),
      pageHeader: routeName,
    };
  },
  watch: {
    $route(to, from) {
      const route = to.name;
      const routeName = `${route[0].toUpperCase()}${route
        .slice(1)
        .toLowerCase()}`;

      this.pageHeader = routeName;
    },
  },
};
</script>

<style>
#custom-layout {
  height: 100%;
}

.logo {
  margin: 14px auto;
}

.logo img {
  height: 40px;
}

#components-layout-demo-side .logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout .site-layout-background {
  background: #fff;
}

[data-theme="dark"] .site-layout .site-layout-background {
  background: #141414;
}

#custom-layout > section > main > div:nth-child(2) {
  height: 100%;
}
</style>
