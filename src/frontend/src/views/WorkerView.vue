<template>
  <div>
    <a-button type="primary" @click="visible = true">Add worker</a-button>
    <div ref="addSuccess" v-if="addSuccess" class="alert-container">
      <a-alert message="New worker added" type="success" show-icon />
    </div>
    <div ref="deleteSuccess" v-if="deleteSuccess" class="alert-container">
      <a-alert message="The worker deleted" type="success" show-icon />
    </div>

    <div v-if="deleteError" class="alert-container">
      <a-alert
        message="Error occured when deleteing the worker"
        description="Please try again"
        type="error"
        show-icon
      />
    </div>
    <div v-if="addError" class="alert-container">
      <a-alert
        message="Error occured when adding new worker"
        description="Please try again"
        type="error"
        show-icon
      />
    </div>
    <a-modal
      v-model:visible="visible"
      title="Add new worker"
      ok-text="Add"
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
        <a-form-item
          name="city"
          label="city"
          :rules="[
            {
              required: true,
              message: 'Please fill in this field!',
            },
          ]"
        >
          <a-input v-model:value="formState.city" />
        </a-form-item>
        <a-form-item
          name="country"
          label="country"
          :rules="[
            {
              required: true,
              message: 'Please fill in this field!',
            },
          ]"
        >
          <a-input v-model:value="formState.country" />
        </a-form-item>
        <a-form-item
          name="street"
          label="street"
          :rules="[
            {
              required: true,
              message: 'Please fill in this field!',
            },
          ]"
        >
          <a-input v-model:value="formState.street" />
        </a-form-item>
        <a-form-item
          name="number"
          label="number"
          :rules="[
            {
              required: true,
              message: 'Please fill in this field!',
            },
          ]"
        >
          <a-input v-model:value="formState.number" />
        </a-form-item>
        <a-form-item
          name="postalCode"
          label="postal code"
          :rules="[
            {
              pattern: '[0-9]{2}-[0-9]{3}',
              required: true,
              message: 'Please fill in this field!',
            },
          ]"
        >
          <a-input v-model:value="formState.postalCode" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
  <a-table :dataSource="workers" :columns="columns">
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'delete'">
        <a-popconfirm
          v-if="workers.length"
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
import { reactive, ref } from "vue";

export default {
  name: "WorkerView",
  async created() {
    const jwt = localStorage.getItem("jwt");

    if (!jwt) {
      this.$router.push("/forbidden");
    } else {
      try {
        const response = await axios.get("/api/person", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        });

        this.workers = response.data;
      } catch (err) {
        console.log(err);
      }
    }
  },
  methods: {
    async onDelete(id) {
      try {
        this.deleteError = false;
        this.deleteSuccess = false;
        this.addError = false;
        this.addSuccess = false;

        await axios.delete(`/api/person/${id}`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        });

        const response = await axios.get("/api/person", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        });

        this.workers = response.data;

        this.deleteSuccess = true;
      } catch (err) {
        this.deleteError = true;
      }
    },
  },
  setup() {
    const d = new Date();
    const today = d.toISOString().split("T")[0];

    let workers = ref([]);

    const addError = ref(false);
    const deleteError = ref(false);
    const addSuccess = ref(false);
    const deleteSuccess = ref(false);
    const formRef = ref();
    const visible = ref(false);
    const formState = reactive({
      firstname: "",
      lastname: "",
      email: "",
      phone: "",
      city: "",
      country: "",
      number: "",
      postalCode: "",
      street: "",
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
            city,
            country,
            number,
            postalCode,
            street,
          } = values;

          visible.value = false;
          formRef.value.resetFields();

          try {
            addError.value = false;
            addSuccess.value = false;
            deleteError.value = false;
            deleteSuccess.value = false;

            await axios.post(
              "/api/person",
              {
                firstname,
                lastname,
                email,
                phone,
                objectId: 1,
                address: {
                  city,
                  country,
                  number,
                  postalCode,
                  street,
                },
              },
              {
                headers: {
                  Authorization: `Bearer ${localStorage.getItem("jwt")}`,
                },
              }
            );

            window.location.href = "http://localhost:3000/worker";

            addSuccess.value = true;
          } catch (err) {
            console.error(err);
            addError.value = true;
          }
        })
        .catch((info) => {
          console.log("Validate Failed:", info);
        });
    };

    return {
      workers,
      addSuccess,
      deleteSuccess,
      addError,
      deleteError,
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

body
  > div:nth-child(6)
  > div
  > div.ant-modal-wrap
  > div
  > div.ant-modal-content
  > div.ant-modal-body
  > form {
  width: 70% !important;
}

.ant-modal-root
  .ant-modal
  .ant-modal-content
  .ant-modal-body
  .ant-form
  .ant-form-vertical {
  width: 70%;
}

body
  > div:nth-child(6)
  > div
  > div.ant-modal-wrap
  > div
  > div.ant-modal-content
  > div.ant-modal-body
  > form
  > div:nth-child(1) {
  margin-bottom: 10px;
}

body
  > div:nth-child(6)
  > div
  > div.ant-modal-wrap
  > div
  > div.ant-modal-content
  > div.ant-modal-body
  > form
  > div:nth-child(1)
  > div.ant-col.ant-form-item-label {
  padding-bottom: 0;
}
</style>
