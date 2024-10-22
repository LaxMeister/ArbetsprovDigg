<template>
  <div v-if="modelValue" class="modal-overlay" @click="closeModal">
    <div class="modal-content" @click.stop>
      <h2>Bekräftelse</h2>
      <p>Är du säker på att du vill ta bort denna kund?</p>
      <p>
        <b>{{ item.name }}</b>
      </p>
      <div class="modal-actions">
        <button @click="confirmDelete" class="confirm-btn">Bekräfta</button>
        <button @click="closeModal" class="cancel-btn">Avbryt</button>
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  modelValue: {
    type: Boolean,
    required: true,
  },
  item: {
    // Add the item prop to receive selectedItem
    type: Object,
    required: true,
  },
});

const emit = defineEmits(["update:modelValue", "confirm"]);

const closeModal = () => {
  emit("update:modelValue", false);
};

const confirmDelete = () => {
  emit("confirm", props.item);
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
  max-width: 400px;
  text-align: center;
}

h2 {
  margin-bottom: 1rem;
}

p {
  margin-bottom: 1.5rem;
  font-size: 1.2rem;
}

.modal-actions {
  display: flex;
  justify-content: center;
  gap: 1rem;
}

.confirm-btn {
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
