<!--
Denna Vue-komponent, Home.vue, fungerar som startsidan för applikationen och visar en lista med kunder. 
Den innehåller en rubrik, en informativ text, en knapp för att navigera till en sida för att lägga till nya kunder och en datatabell som visar kundernas information. 
När komponenten laddas, hämtar den data från backend API och visar en laddningsindikation under hämtningen. 
Data presenteras i DataTable-komponenten, som är ansvarig för att visa kunder i tabellformat.
-->

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
  loading.value = true;
  try {
    const response = await fetch("http://localhost:8080/digg/user");
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    items.value = await response.json();
  } catch (error) {
    console.error(
      "There was a problem with the fetch operation:",
      error.message
    );
  } finally {
    loading.value = false;
  }
};
onMounted(() => {
  fetchData();
});
</script>

<style></style>
