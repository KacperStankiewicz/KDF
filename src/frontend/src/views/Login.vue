<template>

    <div class="reservation">
        <h1>Login:</h1>
    </div>
    <form @submit.prevent="handleSumbit">


        <label>Email:</label>
        <input type="email" v-model="email">
   
        
        <label>Password:</label>
        <input type="password">

        <div class="login">
            <button class="button">Sign in</button>
        </div> 
    </form>

</template>

<script>

  export default {
    data() {
      return {
        valid: true,
        contactDetail: { email: "", message: "" },
        email: '',
        password: '',
        terms: false,
        passwordError: ''
      };
    },
    methods: {
      validateAndSubmit() {
        if (this.$refs.form.validate()) {
          this.disableSubmit = true;
          this.$recaptcha("contactus").then((token) => {
            this.parseContactInfo(this.contactDetail);
            // parse & store data. Method can be housed in Vuex store
            // show confirmation message

            router.push("/");
            // navigate to home page after processing  data
          });
        }
      },
    handleSumbit : async () => {
        const  payload = {
        token: "brsLzGForbz6bSeK8dwtZQ",
        data: {
            name: "nameFirst",
            email: this.email,
            phone: "phoneHome",
            _repeat: 300
        }
};
        const rawResponse = await fetch('https://app.fakejson.com/q', {
        method: 'POST',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
        body: JSON.stringify(payload)
    });
        const content = await rawResponse.json();

  console.log(content);

    console.log("dupa")
    }
    },
  };
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
    }
    input,select {
        display: block;
        padding: 10px 6px;
        width: 100%;
        box-sizing: border-box;
        border: none;
        border-bottom: 1px solid #ddd;
        color: #555;
    }
    input[type="checkbox"] {
        display: inline-block;
        width: 16px;
        margin: 0 10px 0 0;
        position: relative;
        top: 2px;
    }
    .pill {
        display: inline-block;
        margin: 20px 10px 0 0;
        padding: 6px 12px;
        background: #eee;
        border-radius: 20px;
        font-size: 12px;
        letter-spacing: 1px;
        font-weight: bold;
        color: #777;
        cursor: pointer;
    }
    .login {
        text-align: center;
    }
    .error {
        color: #ff0062;
        margin-top: 10px;
        font-size: 0.8em;
        font-weight: bold;
    }
    .reservation {
        padding: 20px;
    }

    .button {
        border-radius: 4px;
        background-color: greenyellow;
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

    .button span {
        cursor: pointer;
        display: inline-block;
        position: relative;
        transition: 0.5s;
    }

    .button span:after {
        content: '\00bb';
        position: absolute;
        opacity: 0;
        top: 0;
        right: -20px;
        transition: 0.5s;
    }

    .button:hover span {
        padding-right: 25px;
    }

    .button:hover span:after {
        opacity: 1;
        right: 0;
    }
</style>
