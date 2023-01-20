<template>
  <div class="charts-container">
    <DoughnutChart :chartData="doughnutChartData" style="height: 40%" />
    <BarChart :chartData="barChartData" style="height: 40%" />
  </div>
</template>

<script>
import { defineComponent } from "vue";
import { DoughnutChart, BarChart } from "vue-chart-3";
import { Chart, registerables } from "chart.js";

Chart.register(...registerables);

export default defineComponent({
  name: "Analytic",
  components: { DoughnutChart, BarChart },
  setup() {
    const labels = ["Bills", "Rent", "Equipment", "Slaries", "Legal"];
    const colors = ["#77CEFF", "#0079AF", "#123E6B", "#97B0C4", "#A5C8ED"];
    const doughnutChartData = {
      labels,
      datasets: [
        {
          data: [30, 40, 60, 70, 5],
          backgroundColor: colors,
        },
      ],
    };

    const barChartData = {
      labels,
      datasets: [
        {
          data: [30, 40, 60, 70, 5],
          backgroundColor: colors,
        },
      ],
    };

    return { doughnutChartData, barChartData };
  },
});
</script>

<style>
.charts-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  flex-flow: column;
  flex-wrap: wrap;
  gap: 50px;
}
</style>
