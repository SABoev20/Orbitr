<template>
  <div class="background">
    <a @click="submitRegister"><p class="register-caption">Register Form</p></a>
    <div class="register-form">
      <form>
        <label for="fnameInput">First Name</label>
        <input v-model="fname" type="text" name="fnameInput" />
        <label for="lnameInput">Last Name</label>
        <input v-model="lname" type="text" name="lnameInput" />
        <label for="emailInput">Email</label>
        <input v-model="email" type="text" name="emailInput" />
        <label for="usernameInput">Username</label>
        <input v-model="username" type="text" name="usernameInput" />
        <label for="passwordInput">Password</label>
        <input v-model="password" type="password" name="passwordInput" />
        <RouterLink to="/login" class="advice-text">Existing account? Login here:</RouterLink>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import router from '@/router'

const fname = ref(null)
const lname = ref(null)
const email = ref(null)
const username = ref(null)
const password = ref(null)

const user = ref(null)

const submitRegister = async () => {
  try {
    const response = await axios.post('http://localhost:8080/signup', {
      firstName: fname.value,
      lastName: lname.value,
      email: email.value,
      username: username.value,
      password: password.value
    })

    user.value = response.data
    console.log(user.value)

    if (!(user.value == null)) {
      const jwt = ref<string | null>(null)
      try {
        const response = await axios.post('http://localhost:8080/login', {
          username: username.value,
          password: password.value
        })
        jwt.value = response.data
        console.log(jwt.value)

        if (!(jwt.value === 'Login unsuccessful')) {
          sessionStorage.setItem('token', jwt.value as string)
          router.push('/')
        }
      } catch (error) {
        console.error('Error logging in:', error)
      }
      router.push('/dashboard')
    }
  } catch (error) {
    console.error('Error logging in:', error)
  }
}
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
  width: 68%;
  height: 100%;
}

input {
  font-family: Poppins-light;
  font-size: 1.4rem;
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
  font-size: 1.69rem;
  margin-top: 10%;
  margin-bottom: 2%;
}

.register-caption {
  transition: 400ms;
  font-family: Poppins-regular;
  font-size: 3.6rem;
  margin: 0;
  margin-bottom: 6%;
}

a :hover {
  transition: 400ms;
  color: #b7b7b7;
}

.register-form {
  background-color: rgba(18, 20, 24, 0.24);
  border-radius: 25px;
  border-color: rgba(47, 53, 63, 0.6);
  border-style: solid;
  border-width: 4px;
  width: 30%;
  height: 78%;
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
  margin-top: 12%;
  margin-bottom: 4%;
}
</style>
