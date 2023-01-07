<template>

    <div class="login">
        <h1>Login:</h1>
    </div>
    <form @submit.prevent="handleSumbit">


        <label>Email:</label>
        <input type="email" v-model="email">
   

        <label>Password:</label>
        <input type="password">
        <br>
        <div class="login">
            <button class="button2">Sign in</button>
        </div> 
    </form>

</template>

<script>

  export default {

    methods: {
    handleSumbit : async () => {
        const  payload = {
        token: "brsLzGForbz6bSeK8dwtZQ",
        data: {
            name: "nameFirst",
            phone: "phoneHome",
            _repeat: 300
        }
    }
}
    }
}
</script>
<style>
    form {
        max-width: 420px;
        margin: 30px auto;
        background: white;
        text-align: left;
        padding: 40px;
        border-radius: 10px;
    }
    label {
        color: #aaa;
        display: inline-block;
        margin: 25px 0 15px;
        font-size: 0.6em;
        text-transform: uppercase;
        letter-spacing: 1px;
        font-weight: bold;
        padding: 5px
    }

    .login {
        text-align: center;
  
    }
    .button2 {
        margin-top: 5px;
        border-radius: 4px;
        background-color: green;
        border: none;
        color: #FFFFFF;
        text-align: center;
        font-size: 28px;
        padding: 20px;
        width: 200px;
        transition: all 0.5s;
        cursor: pointer;
        margin: 5px;
    }

</style>
