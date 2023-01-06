<template>
  <div class="row">
  <div class="leftcolumn">
    <div class="card">
      <h2>Welcome on KDF webpage!</h2>
      <img src="https://osir.swiebodzice.pl/wp-content/uploads/2019/02/Kr%C4%99gielnia-17-1024x678.jpg" style="width:500px;height:250;"> 
      <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    </div>
  </div>
</div>
</template>

<script>


export default {
  name: 'HomeView',
  components: {}
}
</script>


<style>

.card {
  text-align: center;
}
.home {
  padding: 30px;
}
* {
  box-sizing: border-box;
}

body {
  font-family: Arial;
  padding: 10px;
  background: #f1f1f1;
}

.leftcolumn {   
  max-width:800px;
  max-height: 1200px;
  margin: 30px auto;
  background: white;
  text-align: left;
  padding: 40px;
  border-radius: 10px;
}
.fakeimg {
  background-color: #aaa;
  width: 100%;
  padding: 20px;
}
</style>
