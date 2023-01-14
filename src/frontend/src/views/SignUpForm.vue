<template>
  <component1></component1>

  <div class="reservation">
    <h1>Create reservation:</h1>
  </div>

  <form @submit.prevent="createPost">
    <label>First name:</label>
    <input type="text" id="firstname" v-model="formData.firstname" />

    <label>Last name:</label>
    <input type="text" id="lastname" v-model="formData.lastname" />

    <label>Email:</label>
    <input type="email" id="email" v-model="formData.email" />

    <label>Enter a phone number:</label>
    <input
      type="tel"
      id="phone"
      v-model="formData.phone"
      placeholder="123 456 789"
      pattern="[0-9]{3} [0-9]{3} [0-9]{3}"
    />

    <label>Reservation date:</label>
    <input type="date" />

    <label>Enter start time:</label>
    <input type="time" id="startDate" v-model="formData.startDate" />

    <label>Enter end time:</label>
    <input type="time" id="endDate" v-model="formData.endDate" />

    <label>Enter number of people:</label>
    <input
      type="number"
      id="numberOfPeople"
      v-model="formData.numberOfPeople"
    />

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
import axios from "axios";
export default {
  name: "SignUpForm",
  components: {
    component1: NavBar,
  },
  name: "CreatePost",
  data() {
    return {
      formData: {
        firstname: "",
        lastname: "",
        email: "",
        phone: "",
        startDate: "",
        endDate: "",
        numberOfPeople: "",
      },
    };
  },
  methods: {
    createPost() {
      axios
        .post("/api/reservation", this.formData)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
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
