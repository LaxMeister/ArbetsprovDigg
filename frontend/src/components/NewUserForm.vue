<!--
Denna Vue-komponent, NewUserForm.vue är en formulär-komponent för att lägga till en ny kund.
-->

<template>
    <div class="form-wrapper">
        <form @submit.prevent="submitForm">
        <h1>Ny Kund</h1>
        <div class="text"> 
            <p>Fyll i fälten och tryck skicka för att skapa en ny kund</p>
        </div>
      <div>
        <label for="name">Namn:</label>
        <input type="text" id="name" v-model="name" required />
      </div>
      <div>
        <label for="address">Adress:</label>
        <input type="text" id="adress" v-model="address" required />
      </div>
      <div>
        <label for="email">Epost:</label>
        <input type="email" id="email" v-model="email" required />
      </div>
      <div>
        <label for="phone">Telefon:</label>
        <input type="text" id="phone" v-model="phone" required></input>
      </div>
      <button class="submit-btn" type="submit">Skapa</button>
    </form>
  </div>
</template>

<script>
import { ref } from "vue";
import { useRouter } from 'vue-router';

export default {
  name: "NewUserForm",
  setup() {
    const router = useRouter();
    const name = ref("");
    const address = ref("");
    const email = ref("");
    const phone = ref("");

    const submitForm = () => {
        fetch('http://localhost:8080/digg/newuser', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    name: name.value,
    address: address.value,
    email: email.value,
    telephone: phone.value
  })
})
.then(response => response.json())
.then(data => {
  console.log('Success:', data);
    router.push({ name: "home" });
})
.catch((error) => {
  console.error('Error:', error);
});

      name.value = "";
      address.value =""
      email.value = "";
      phone.value = "";
    };

    return {
      name,
      address,
      email,
      phone,
      submitForm,
      
    };
  },
};
</script>

<style scoped>
.form-wrapper {
  margin-top: 20%;
  color: #fff;
}

.text{
    background: #9aa6b1;
    padding: 5px;
    border-radius: 3px;
    
}

form {
  display: flex;
  flex-direction: column;
  max-width: 300px;
  margin: 0 auto;
}

div {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input,
textarea {
  width: 100%;
  padding: 5px;
}

.submit-btn{
    appearance: none;
    background-color: #324960;
    border: 2px solid #1a1a1a;
    border-radius: 15px;
    box-sizing: border-box;
    color: #fff;
    cursor: pointer;
    display: inline-block;
    font-family: Roobert, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica,
      Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
    font-size: 16px;
    font-weight: 600;
    line-height: normal;
    margin: 0;
    margin-left: 80px;
    min-height: 60px;
    min-width: 0;
    outline: none;
    padding: 16px 24px;
    text-align: center;
    text-decoration: none;
    transition: all 300ms cubic-bezier(0.23, 1, 0.32, 1);
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
    width: 50%;
    will-change: transform;
  }
  
  .submit-btn:disabled {
    pointer-events: none;
  }
  
  .submit-btn:hover {
    color: #fff;
    background-color: #4fc3a1;
    box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
    transform: translateY(-2px);
  }
  
  .submit-btn:active {
    box-shadow: none;
    transform: translateY(0);
  }
</style>
