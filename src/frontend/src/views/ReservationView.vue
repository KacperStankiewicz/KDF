<template>
  <div>
    <a-button type="primary" @click="visible = true">Add reservation</a-button>
    <div ref="success" v-if="success" class="alert-container">
      <a-alert message="New reservation added" type="success" show-icon />
    </div>

    <div v-if="error" class="alert-container">
      <a-alert
        message="Error occured when adding new reservation"
        description="Please try again"
        type="error"
        show-icon
      />
    </div>
    <a-modal
      v-model:visible="visible"
      title="Create new reservation"
      ok-text="Create"
      cancel-text="Cancel"
      @ok="onOk"
    >
      <a-form
        ref="formRef"
        :model="formState"
        layout="vertical"
        name="form_in_modal"
      >
        <a-form-item
          name="firstname"
          label="First Name"
          :rules="[
            {
              required: true,
              message: 'Please fill in this field!',
            },
          ]"
        >
          <a-input v-model:value="formState.firstname" />
        </a-form-item>
        <a-form-item
          name="lastname"
          label="last Name"
          :rules="[
            {
              required: true,
              message: 'Please fill in this field!',
            },
          ]"
        >
          <a-input v-model:value="formState.lastname" />
        </a-form-item>
        <a-form-item
          name="email"
          label="Email"
          :rules="[
            {
              required: true,
              type: 'email',
              message: 'Please fill in this field!',
            },
          ]"
        >
          <a-input v-model:value="formState.email" />
        </a-form-item>
        <a-form-item
          name="phone"
          label="Phone"
          :rules="[
            {
              pattern: '[0-9]{9}',
              required: true,
              message: 'The format should be 123456789',
            },
          ]"
        >
          <a-input v-model:value="formState.phone" />
        </a-form-item>
        <a-form-item name="date" label="Date" :rules="[{ required: true }]">
          <a-date-picker
            v-model:value="formState['date']"
            value-format="YYYY-MM-DD"
          />
        </a-form-item>

        <a-form-item name="time" label="Time" :rules="[{ required: true }]">
          <a-time-range-picker v-model:value="formState['time']" format="HH" />
        </a-form-item>

        <a-form-item
          name="numberOfPeople"
          label="People No"
          :rules="[
            {
              required: true,
              message: 'Please fill in this field!',
            },
          ]"
        >
          <a-input-number
            v-model:value="formState['numberOfPeople']"
            :min="1"
            :max="10"
          />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
  <a-table :dataSource="reservations" :columns="columns">
    <template #bodyCell="{ column, text, record }">
      <template v-if="column.dataIndex === 'edit'">
        <a>Edit</a>
      </template>
      <template v-else-if="column.dataIndex === 'delete'">
        <a-popconfirm
          v-if="reservations.length"
          title="Sure to delete?"
          @confirm="onDelete(record.id)"
        >
          <a>Delete</a>
        </a-popconfirm>
      </template>
    </template>
  </a-table>
</template>

<script>
import axios from "axios";
import { reactive, ref, toRaw } from "vue";

export default {
  name: "ReservationView",
  async created() {
    const jwt = localStorage.getItem("jwt");

    if (!jwt) {
      this.$router.push("/forbidden");
    } else {
      try {
        const response = await axios.get("/api/reservation", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        });

        this.reservations = response.data;
      } catch (err) {
        console.log(err);
      }
    }
  },
  data() {
    return {
      reservations: [],
    };
  },
  methods: {
    onDelete(id) {
      console.log("usuwanie rezerwacji z id: ", id);
    },
    onEdit() {},
  },
  setup() {
    const d = new Date();
    const today = d.toISOString().split("T")[0];

    const error = ref(false);
    const success = ref(false);
    const formRef = ref();
    const visible = ref(false);
    const formState = reactive({
      firstname: "",
      lastname: "",
      email: "",
      phone: "",
      date: today,
      time: [],
      numberOfPeople: "2",
    });

    const columns = [
      {
        title: "Id",
        dataIndex: "id",
        key: "id",
      },
      {
        title: "First Name",
        dataIndex: "firstname",
        key: "firstname",
      },
      {
        title: "Last Name",
        dataIndex: "lastname",
        key: "lastname",
      },
      {
        title: "Email",
        dataIndex: "email",
        key: "email",
      },
      {
        title: "Phone",
        dataIndex: "phone",
        key: "phone",
      },
      {
        title: "Start date",
        dataIndex: "startDate",
        key: "startDate",
      },
      {
        title: "End date",
        dataIndex: "endDate",
        key: "endDate",
      },
      {
        title: "People No",
        dataIndex: "numberOfPeople",
        key: "numberOfPeople",
      },
      {
        title: "Edit",
        dataIndex: "edit",
      },
      {
        title: "Delete",
        dataIndex: "delete",
      },
    ];

    const onOk = () => {
      formRef.value
        .validateFields()
        .then(async (values) => {
          const {
            firstname,
            lastname,
            email,
            phone,
            date,
            time,
            numberOfPeople,
          } = values;
          const [startTime, endTime] = time;

          const startTimeFormatted = new Date(
            JSON.parse(JSON.stringify(startTime))
          ).toLocaleTimeString();
          const endTimeFormatted = new Date(
            JSON.parse(JSON.stringify(endTime))
          ).toLocaleTimeString();

          const startDate =
            date && startTimeFormatted
              ? new Date(`${date} ${startTimeFormatted}`).toISOString()
              : "";

          const endDate =
            date && endTimeFormatted
              ? new Date(`${date} ${endTimeFormatted}`).toISOString()
              : "";

          visible.value = false;
          formRef.value.resetFields();

          try {
            error.value = false;
            success.value = false;

            await axios.post(
              "/api/reservation",
              {
                email,
                endDate,
                firstname,
                lastname,
                numberOfPeople,
                phone,
                startDate,
              },
              {
                headers: {
                  Authorization: "Bearer " + localStorage.getItem("jwt"),
                },
              }
            );

            success.value = true;
          } catch (err) {
            error.value = true;
          }
        })
        .catch((info) => {
          console.log("Validate Failed:", info);
        });
    };

    return {
      success,
      error,
      today,
      formState,
      formRef,
      visible,
      columns,
      onOk,
    };
  },
};
</script>

<style>
#custom-layout > section > main > div:nth-child(2) > div:nth-child(1) {
  display: flex;
}

#custom-layout > section > main > div:nth-child(2) > div:nth-child(1) > button {
  margin-bottom: 6px;
}

#custom-layout > section > main > div:nth-child(2) > div.ant-table-wrapper {
  height: 100%;
}

.alert-container {
  margin: 15px auto;
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

.collection-create-form_last-form-item {
  margin-bottom: 0;
}
</style>
