<template>

    <div class="reservation">
        <h1>Create reservation:</h1>
    </div>
    <!--@submit.prevent="handleSubmit"-->
    <form>

        <label for="fname">First name:</label>
        <input type="text">

        <label>Last name:</label>
        <input type="text">

        <label>Email:</label>
        <input type="email" required v-model="email">
        
        <!--
        <label>Password:</label>
        <input type="password" required v-model="password">
        <div v-if="passwordError" class="error">{{ passwordError }}</div>

        <label>Role:</label>
        <select v-model="role">
            <option value="test1">Test 1</option>
            <option value="test2">Test 2</option>
        </select> 
        -->

        <label>Enter a phone number:</label>
        <input type="tel" placeholder="123-456-789" pattern="[0-9]{3}-[0-9]{3}-[0-9]{3}" required>

        <label>Reservation date:</label>
        <input type="date" required>

        <label>Select start time:</label>
        <input type="time">

        <label>Select end time:</label>
        <input type="time">

        <label>Select number of people:</label>
        <input type="number" min="1" max="10">
        
        <label>Tell us what you need:</label>
        <textarea rows="4" cols="50">
        </textarea>

        <div class="terms">
            <input type="checkbox" v-model="terms" required>
            <label>Accept temrs and conditions</label>
        </div>
        
        <div class="submit">
            <button class="button">Reserve</button>
        </div> 
    </form>
    
    
    <p>Email: {{ email }}</p>
    <p>Terms accepted: {{ terms }}</p>

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
      handleSubmit() {
            this.passwordError = this.password.length > 5 ? 
                '' : 'Password too short!'

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
    .submit {
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
        background-color: crimson;
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
