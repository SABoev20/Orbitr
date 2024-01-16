<template>
  <div class="background">
    <a @click="submitLogin"><p class="login-caption">Login Form</p></a>
    <div class="login-form">
      <form>
        <label for="usernameInput">Username</label>
        <input v-model="username" type="text" name="usernameInput" />
        <label for="passwordInput">Password</label>
        <input v-model="password" type="password" name="passwordInput" />
        <RouterLink to="/register" class="advice-text"
          >Don’t have an account? Register now:</RouterLink
        >
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import router from '@/router';

const username = ref(null)
const password = ref(null)
const jwt = ref<string | null>(null)
jwt.value = 'Login unsuccessful'

const submitLogin = async () => {
  try {
    const response = await axios.post('http://localhost:8080/login', {
      username: username.value,
      password: password.value
    });
    jwt.value = response.data;
    console.log(jwt.value)

    if(!(jwt.value === "Login unsuccessful")){
      sessionStorage.setItem('token', jwt.value as string);
      router.push('/dashboard')
    }
  } catch (error) {
    console.error('Error logging in:', error);
  }
};
</script>

<style scoped>
.background {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  background-image: url('../assets/images/welcome-background.png');
  background-repeat: no-repeat;
  background-size: cover;
  width: 100vw;
  height: 100vh;
  color: white;
}

form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 90%;
  height: 100%;
}

input {
  font-family: Poppins-light;
  font-size: 1.9rem;
  color: white;
  background-color: rgba(0, 0, 0, 0.01);
  outline: none;
  border-top-color: rgba(0, 0, 0, 0.01);
  border-left-color: rgba(0, 0, 0, 0.01);
  border-right-color: rgba(0, 0, 0, 0.01);
  border-bottom-color: white;
}

label {
  font-family: Poppins-regular;
  font-size: 2.3rem;
  margin-top: 11%;
  margin-bottom: 1%;
}

.login-caption {
  transition: 400ms;
  font-family: Poppins-regular;
  font-size: 3.6rem;
  margin: 0;
  margin-bottom: 1%;
}

a :hover {
  transition: 400ms;
  color: #b7b7b7;
}

.login-form {
  background-color: rgba(18, 20, 24, 0.24);
  border-radius: 25px;
  border-color: rgba(47, 53, 63, 0.6);
  border-style: solid;
  border-width: 4px;
  width: 36%;
  height: 48%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.advice-text {
  font-family: Poppins-regular;
  font-size: 1.25rem;
  text-decoration: none;
  color: #999999;
  margin-top: 10%;
  margin-bottom: 4%;
}
</style>
