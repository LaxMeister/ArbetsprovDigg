<!--
Denna Vue-komponent, UpdateModal.vue är en modal-komponent för att uppdatera informationen om en kund.
-->

<template>
  <div v-if="modelValue" class="modal-overlay" @click="closeModal">
    <div class="modal-content" @click.stop>
      <h2>Uppdatera Information</h2>
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="name">Namn:</label>
          <input type="text" id="name" v-model="formData.name" required />
        </div>
        <div class="form-group">
          <label for="address">Adress:</label>
          <input type="text" id="address" v-model="formData.address" required />
        </div>
        <div class="form-group">
          <label for="email">Epost:</label>
          <input type="email" id="email" v-model="formData.email" required />
        </div>
        <div class="form-group">
          <label for="telephone">Telefon:</label>
          <input
            type="tel"
            id="telephone"
            v-model="formData.telephone"
            required
          />
        </div>
        <div class="modal-actions">
          <button type="submit" class="save-btn">Spara</button>
          <button type="button" @click="closeModal" class="cancel-btn">
            Avbryt
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";

const props = defineProps({
  modelValue: {
    type: Boolean,
    required: true,
  },
  itemData: {
    type: Object,
    required: true,
  },
});

const emit = defineEmits(["update:modelValue", "save"]); // Emit-funktioner för att kommunicera med föräldern

const formData = ref({
  id: null,
  name: "",
  address: "",
  email: "",
  telephone: "",
});

// Watch för att uppdatera formData när itemData ändras
watch(
  () => props.itemData,
  (newValue) => {
    formData.value = { ...newValue }; // Kopiera nya värden till formData
  },
  { immediate: true } // Kör vid första uppdatering
);

const closeModal = () => {
  emit("update:modelValue", false); // Stänger modal genom att uppdatera modelValue
};

const handleSubmit = () => {
  emit("save", formData.value); // Emitera sparade data till föräldern
  closeModal();
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: white;
  padding: 2rem;
  border-radius: 8px;
  width: 100%;
  max-width: 500px;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
}

.form-group input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.modal-actions {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  margin-top: 1.5rem;
}

.save-btn {
  background-color: #4caf50;
  color: white;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.cancel-btn {
  background-color: #f44336;
  color: white;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>
