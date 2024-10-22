<template>
  <h1 class="title">Arbetsprov DIGG</h1>
  <div class="info">
    <p>
      Detta är ett arbetsprov för Digg, nedan ser vi min tabell med Kunder.<br />
      Vill du lägga till en Kund klicka på knappen "Ny Kund".
    </p>
  </div>
  <button class="newuser-btn" @click="$router.push('newuser')">Ny Kund</button>
  <div>
    <DataTable :items="items" @refresh-data="fetchData" v-if="!loading" />
    <p v-else>Laddar data...</p>
  </div>
</template>

<script setup>
import DataTable from "./DataTable.vue";
import { onMounted, ref } from "vue";
const items = ref([]);
const loading = ref(true);

const fetchData = async () => {
  loading.value = true; // Set loading true when fetching
  try {
    const response = await fetch("http://localhost:8080/digg/user");
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    items.value = await response.json(); // Update the items after fetching
  } catch (error) {
    console.error(
      "There was a problem with the fetch operation:",
      error.message
    );
  } finally {
    loading.value = false; // Set loading false after fetching
  }
};
onMounted(() => {
  fetchData();
});
</script>

<style></style>
