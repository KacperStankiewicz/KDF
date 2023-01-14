<template v-cloak>
  <navBar />

  <div class="book-title">
    <h1>Book your game:</h1>
  </div>

  <div v-if="bookingSuccess" class="alert-container">
    <a-alert
      message="We booked the game for you"
      description="See you soon!"
      type="success"
      show-icon
    />
  </div>

  <div v-if="bookingError" class="alert-container">
    <a-alert
      message="We could not make the booking..."
      description="If you keep getting the error, please contact us."
      type="error"
      show-icon
    />
  </div>

  <div class="form-container">
    <form @submit.prevent="submit">
      <label>First name:</label>
      <input type="text" id="firstname" v-model="formData.firstname" required />

      <label>Last name:</label>
      <input type="text" id="lastname" v-model="formData.lastname" required />

      <label>Email:</label>
      <input type="email" id="email" v-model="formData.email" required />

      <label>Enter a phone number:</label>
      <input
        type="tel"
        id="phone"
        v-model="formData.phone"
        placeholder="123456789"
        pattern="[0-9]{3}[0-9]{3}[0-9]{3}"
        required
      />

      <label>booking date:</label>
      <input type="date" id="date" v-model="formData.date" />

      <label>Enter start time (opening hour: 12:00):</label>
      <input
        type="time"
        id="startTime"
        min="12:00"
        max="23:00"
        v-model="formData.startTime"
      />

      <label>Enter end time (closing hour: 23:00):</label>
      <input
        type="time"
        id="endTime"
        v-model="formData.endTime"
        min="12:00"
        max="23:00"
      />

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
        <button class="book-button">Book</button>
      </div>
    </form>
  </div>
</template>

<script>
import NavBar from "../components/NavBar.vue";
import axios from "axios";

export default {
  name: "BookForm",
  components: {
    navBar: NavBar,
  },
  data() {
    return {
      formData: {
        firstname: "",
        lastname: "",
        email: "",
        phone: "",
        date: "",
        startTime: "",
        endTime: "",
        numberOfPeople: "",
        timeRangePicker: "",
      },
      bookingError: false,
      bookingSuccess: false,
      test: true,
    };
  },
  methods: {
    submit() {
      const {
        date,
        startTime,
        endTime,
        email,
        firstname,
        lastname,
        phone,
        numberOfPeople,
      } = this.formData;

      const endDate =
        date && endTime ? new Date(`${date} ${endTime}`).toISOString() : "";
      const startDate =
        date && startTime ? new Date(`${date} ${startTime}`).toISOString() : "";

      this.bookingSuccess = false;
      this.bookingError = false;

      axios
        .post("/api/reservation", {
          email,
          endDate,
          firstname,
          lastname,
          numberOfPeople,
          phone,
          startDate,
        })
        .then((response) => {
          console.log(response);
          this.bookingSuccess = true;
        })
        .catch((error) => {
          console.log(error);
          console.log("cos sie zjebalo");
          this.bookingError = true;
        });
    },
  },
};
</script>

<style>
.alert-container {
  margin: 0 auto;
  width: 80%;
  max-width: 600px;
}

.alert-container > div > span {
  display: flex;
  align-self: center;
}

.alert-container > div > div > div.ant-alert-message {
  font-size: 20px;
  font-weight: 800;
}

.alert-container > div > div > div.ant-alert-description {
  font-size: 16px;
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

.book-title {
  padding: 20px;
}

::placeholder {
  opacity: 0.8;
}
</style>
