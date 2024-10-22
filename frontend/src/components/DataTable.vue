<template>
  <div class="table-wrapper">
    <table class="fl-table">
      <thead>
        <tr>
          <th>Namn</th>
          <th>Adress</th>
          <th>Epost</th>
          <th>Telefon</th>
          <th>Åtgärder</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in paginatedItems" :key="item.id">
          <td>{{ item.name }}</td>
          <td>{{ item.address }}</td>
          <td>{{ item.email }}</td>
          <td>{{ item.telephone }}</td>
          <td>
            <button
              @click="openUpdateModal(item)"
              class="action-btn update-btn"
            >
              Uppdatera
            </button>
            <button
              @click="opendeleteModal(item)"
              class="action-btn delete-btn"
            >
              Ta bort
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="pagination">
      <button @click="backPage" :disabled="page === 1">Föregående</button>
      <button
        v-for="pageNumber in totalPages"
        :key="pageNumber"
        @click="goToPage(pageNumber)"
        :class="{ active: page === pageNumber }"
      >
        {{ pageNumber }}
      </button>
      <button @click="nextPage" :disabled="page === totalPages">Nästa</button>
    </div>
    <UpdateModal
      v-model="showModal"
      :item-data="selectedItem"
      @save="handleUpdate"
    />
    <DeleteModal
      v-model="showDeleteModal"
      :item="selectedItem"
      @confirm="handleDelete"
    />
  </div>
</template>

<script setup>
import { computed, ref } from "vue";
import UpdateModal from "./UpdateModal.vue";
import DeleteModal from "./DeleteModal.vue";

const props = defineProps({
  items: {
    type: Array,
    required: true,
  },
});

const emit = defineEmits(["update-item"]);

const page = ref(1);
const perPage = 10;
const showModal = ref(false);
const showDeleteModal = ref(false);
const selectedItem = ref({});

const paginatedItems = computed(() =>
  props.items.slice((page.value - 1) * perPage, page.value * perPage)
);

const totalPages = computed(() => Math.ceil(props.items.length / perPage));

const nextPage = () => {
  if (page.value < totalPages.value) {
    page.value += 1;
  }
};

const backPage = () => {
  if (page.value > 1) {
    page.value -= 1;
  }
};

const goToPage = (numPage) => {
  page.value = numPage;
};

const openUpdateModal = (item) => {
  selectedItem.value = { ...item };
  showModal.value = true;
};
const opendeleteModal = (item) => {
  selectedItem.value = { ...item };
  showDeleteModal.value = true;
};

const handleUpdate = async (updatedItem) => {
  try {
    const response = await fetch("http://localhost:8080/digg/edituser", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id: updatedItem.id,
        name: updatedItem.name,
        address: updatedItem.address,
        email: updatedItem.email,
        telephone: updatedItem.telephone, // Fixed property name
      }),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const data = await response.json();
    console.log("Success:", data);

    emit("update-item", updatedItem);
    emit("refresh-data");
  } catch (error) {
    console.error("Error:", error);
  }
};
const handleDelete = async (item) => {
  if (!item) {
    console.error("No item to delete!");
    return; // Prevent proceeding if item is undefined
  }

  try {
    const response = await fetch("http://localhost:8080/digg/deleteuser", {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id: item.id,
        name: item.name,
        address: item.address,
        email: item.email,
        telephone: item.telephone,
      }),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    if (response.status !== 204) {
      const data = await response.json();
      console.log("Success:", data);
    } else {
      console.log("Success: No content returned");
    }

    emit("update-item", item);
    emit("refresh-data");
  } catch (error) {
    console.error("Error:", error);
  }
};
</script>

<style scoped>
.pagination {
  margin-top: 1rem;
  display: flex;
  justify-content: center;
  gap: 0.5rem;
}

.pagination button {
  padding: 0.5rem 1rem;
  border: 1px solid #ccc;
  background-color: #fff;
  cursor: pointer;
}

.pagination button.active {
  background-color: #007bff;
  color: #fff;
}

.pagination button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
.action-btn {
  padding: 0.25rem 0.5rem;
  margin: 0 0.25rem;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.update-btn {
  background-color: #4caf50;
  color: white;
}

.delete-btn {
  background-color: #f44336;
  color: white;
}
</style>
