<template>
  <div class="row">
  <div class="text">
    <div class="card">
      <h2>What can we offer?</h2>
      <img src="https://osir.swiebodzice.pl/wp-content/uploads/2019/02/Kr%C4%99gielnia-17-1024x678.jpg" style="width:500px;height:250;"> 
      <p>We are one of the largest providers of active entertainment,</p>
      <p>while having the following facilities to offer:</p>
      <ul class="list">
      <li>Full catering facilities</li>
      <li>Facilities adapted for the disabled</li>
      <li>Lightweight balls and extendable bumpers for children</li>
      <li>Bowling shoe sizes - starting from No. 25</li>
      <li>Special offer for seniors</li>
      <li>Organization of corporate events</li>
      <li>Organization of birthdays and occasional parties</li>
      </ul>   
    </div>
  </div>
  <div class="text">
    <div class="card">
      <h2>Why us?</h2>
      <img src="https://egryfice.pl/wp-content/uploads/2017/02/bowling-237905_960_720.jpg" style="width:500px;height:250;"> 
      <p>Invite your friends and take advantage of our promotions! Birthday party for your child?
         It will be an unforgettable party! Going out bowling with your class? 
         This lesson you will not forget for a long time! Employee integration? The best is at bowling!</p>
      <p>Come and have some fun!</p>
      <div class="submit">
            <button @click="$router.push('reserve')"
        role="link" class="button1">Reserve Now!</button>
        </div> 
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
.list {
  text-align: left;
  padding: 10px;
  margin-left: 120px;
}
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
.text {   
  max-width:800px;
  max-height: 1200px;
  margin: 30px auto;
  background: white;
  text-align: left;
  padding: 40px;
  border-radius: 10px;
}
.button1 {
        border-radius: 4px;
        background-color: crimson;
        border: none;
        color: #FFFFFF;
        text-align: center;
        font-size: 28px;
        padding: 20px;
        width: 300px;
        transition: all 0.5s;
        cursor: pointer;
        margin: 5px;
    }
</style>
