<template>
  <component1></component1>

  <div class="reservation">
    <h1>Create reservation:</h1>
  </div>

  <form @submit.prevent="handleSumbit">
    <label>First name:</label>
    <input type="text" v-model="fname" />

    <label>Last name:</label>
    <input type="text" v-model="surname" />

    <label>Email:</label>
    <input type="email" v-model="email" />

    <label>Enter a phone number:</label>
    <input
      type="tel"
      v-model="phone"
      placeholder="123 456 789"
      pattern="[0-9]{3} [0-9]{3} [0-9]{3}"
    />

    <label>Reservation date:</label>
    <input type="date" v-model="date" />

    <label>Enter start time:</label>
    <input type="time" v-model="startTime" />

    <label>Enter end time:</label>
    <input type="time" v-model="endTime" />

    <label>Enter number of people:</label>
    <input type="number" v-model="people" min="1" max="10" />

    <div class="terms">
      <input type="checkbox" v-model="terms" />
      <label>Accept temrs and conditions</label>
    </div>

    <div class="submit">
      <button class="button">Reserve</button>
    </div>
  </form>
</template>

<script>
  import NavBar from "./NavBar.vue";
  export default {
    name: "SignUpForm",
    components: {
      component1: NavBar,
    },
    data() {
      return {
        fname: "",
        surname: "",
        email: "",
        phone: "",
        date: "",
        startTime: "",
        endTime: "",
        people: "",
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
      methods: {
        async addBook() {
          const { data } = await axios.post("http://localhost:3000/books", {
            fname: this.fname,
            surname: this.surname,
            email: this.email,
            phone: this.phone,
            date: this.date,
            startTime: this.startTime,
            endtime: this.endTime,
            people: this.people,
          });
          this.fname = "";
          this.surname = "";
          this.email = "";
          this.phone = "";
          this.date = "";
          this.startTime = "";
          this.endTime = "";
          this.people = "";
        },
      },
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
  input,
  select {
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
  .submit {
    text-align: center;
  }
  .reservation {
    padding: 20px;
  }
  .button {
    border-radius: 4px;
    background-color: crimson;
    border: none;
    color: #ffffff;
    text-align: center;
    font-size: 28px;
    padding: 20px;
    width: 200px;
    transition: all 0.5s;
    cursor: pointer;
    margin: 5px;
  }
</style>
