<template v-cloak>
  <NavBar />

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
        pattern="[0-9]{9}"
        required
      />

      <label>booking date:</label>
      <input type="date" id="date" v-model="formData.date" />

      <div class="inline-input">
        <label>Enter start time (opening hour: 12:00):</label>
        <div class="input-time-container">
          <input
            type="number"
            id="startTime"
            min="12"
            max="23"
            v-model="formData.startTime"
          />
          <span>:00</span>
        </div>
      </div>

      <div class="inline-input">
        <label>Enter end time (closing hour: 23:00):</label>
        <div class="input-time-container">
          <input
            type="number"
            id="endTime"
            v-model="formData.endTime"
            min="13"
            max="24"
          />
          <span>:00</span>
        </div>
      </div>

      <label>Enter number of people:</label>
      <input
        type="number"
        id="numberOfPeople"
        v-model="formData.numberOfPeople"
        min="1"
        max="10"
      />

      <div class="terms">
        <input type="checkbox" v-model="terms" />
        <label>Accept terms and conditions</label>
      </div>

      <div class="submit">
        <button class="btn-lg btn-error">Book</button>
      </div>
    </form>
    <div class="recaptcha-info">
      This site is protected by reCAPTCHA and the Google
      <a href="https://policies.google.com/privacy">Privacy Policy</a> and
      <a href="https://policies.google.com/terms">Terms of Service</a> apply.
    </div>
  </div>
</template>

<script>
import NavBar from "../components/NavBar.vue";
import axios from "axios";

const d = new Date();
const today = d.toISOString().split("T")[0];

export default {
  name: "BookForm",
  components: {
    NavBar,
  },
  data() {
    return {
      formData: {
        firstname: "",
        lastname: "",
        email: "",
        phone: "",
        date: today,
        startTime: "20",
        endTime: "22",
        numberOfPeople: "2",
      },
      bookingError: false,
      bookingSuccess: false,
    };
  },
  methods: {
    async submit() {
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
        date && endTime
          ? new Date(`${date} ${endTime}:00:00`).toISOString()
          : "";
      const startDate =
        date && startTime
          ? new Date(`${date} ${startTime}:00:00`).toISOString()
          : "";

      try {
        const token = await this.$recaptcha("submit");

        this.bookingSuccess = false;
        this.bookingError = false;

        await axios.post("/api/reservation", {
          reservationDTO: {
            email,
            endDate,
            firstname,
            lastname,
            numberOfPeople,
            phone,
            startDate,
          },
          reCaptchaToken: token,
        });

        this.bookingSuccess = true;
      } catch (err) {
        this.bookingError = true;
      }
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

.inline-input {
  display: flex;
  flex-direction: column;
}

.inline-input label {
  width: 80%;
}

.inline-input .input-time-container {
  width: 20%;
  display: flex;
  align-content: center;
}

.inline-input .input-time-container span {
  padding-top: 10px;
}

.inline-input .input-time-container input {
  width: unset;
}

.recaptcha-info {
  margin-top: 10px;
}
</style>
