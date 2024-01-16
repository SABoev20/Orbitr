<template>
  <div class="background">
    <Navbar></Navbar>
    <div class="wrapper-wrapper">
      <div class="topbar-wrapper">
        <div class="user-info-wrapper">
          <p class="user-info">{{ userData?.firstName }} {{ userData?.lastName }}</p>
          <img class="profile-picture" :src="userData?.profilePicture" />
        </div>
      </div>
      <div class="main-content-wrapper">
        <div class="inventory-wrapper">
          <div class="ship-wrapper" v-for="(spaceship, index) in spaceshipList" :key="index">
            <div class="ship-data-wrapper">
              <img class="ship-image" :src="spaceship.picture">
              <div class="ship-data">
                <p class="ship-name-text">{{ spaceship.name }}</p>
                <p class="ship-secondary-text">Model: {{ spaceship.model }}</p>
                <p class="ship-secondary-text">Technology type:<br>{{ spaceship.technology.name }}</p>
                <p class="ship-description-text">{{ spaceship.description }}</p>
              </div>
            </div>
            <div class="sale-wrapper"></div>
          </div>


        </div>
        <div class="technology-wrapper">
          <p class="tech-label">Technology type wiki:</p>
          <div class="tech-tech">
            <div class="circle-bullet" style="background-color: #8e3cc0"></div>
            <p class="tech-text">
              Quantum Drive - A futuristic propulsion system that allows for faster than light
              travel using space-time manipulation
            </p>
          </div>
          <div class="tech-tech">
            <div class="circle-bullet" style="background-color: #1f86c1"></div>
            <p class="tech-text">
              Ion Propulsion Engine - An advanced engine that utilizes ionized particles to generate
              thrust, ideal for long-duration space voyages with high fuel efficiency.
            </p>
          </div>
          <div class="tech-tech">
            <div class="circle-bullet" style="background-color: #0d8e58"></div>
            <p class="tech-text">
              Solar Sail - Harnesses solar radiation pressure for propulsion, enabling sustainable
              and energy-efficient travel across the solar system without fuel.
            </p>
          </div>
          <div class="tech-tech">
            <div class="circle-bullet" style="background-color: #bc2c4e"></div>
            <p class="tech-text">
              Antimatter Reactor - Utilizes antimatter for energy generation, providing an immense
              power source for spacecraft, ideal for interstellar exploration missions.
            </p>
          </div>
          <div class="tech-tech">
            <div class="circle-bullet" style="background-color: #202020"></div>
            <p class="tech-text">Unknown - ???</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import Navbar from '../components/Navbar.vue'
import { ref, onMounted } from 'vue'
import axios from 'axios'
import router from '@/router'

interface UserData {
  firstName: string
  lastName: string
  username: string
  email: string
  profilePicture: string
  credits: number
}

interface TechnologyData {
  name: string
  description: string
  labelColor: string
}

interface SpaceshipData {
  name: string
  model: string
  technology: TechnologyData
  description: string
  picture: string
}

interface OfferData {
  user: UserData
  spaceship: SpaceshipData
  price: number
}

const token = sessionStorage.getItem('token')
const userData = ref<UserData | null>(null)
const spaceshipList = ref<SpaceshipData[]>([]);

async function fetchUserData() {
  if (token === 'Login unsuccessful' || token == null) {
    router.push('/')
  }

  try {
    const response = await axios.get('http://localhost:8080/users/current', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    userData.value = response.data as UserData
  } catch (error) {
    console.error('Error fetching user data:', error)
  }

  try {
    const response = await axios.get('http://localhost:8080/spaceships/inventory', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    spaceshipList.value = response.data as SpaceshipData[]
  } catch (error) {
    console.error('Error fetching user data:', error)
  }
}

onMounted(() => {
  fetchUserData()
})
</script>

<style scoped>
@font-face {
  font-family: 'Poppins-regular';
  src: url(../assets/fonts/Poppins-Regular.ttf);
}

@font-face {
  font-family: 'Poppins-light';
  src: url(../assets/fonts/Poppins-Light.ttf);
}

@font-face {
  font-family: 'Poppins-extra-light';
  src: url(../assets/fonts/Poppins-ExtraLight.ttf);
}

::-webkit-scrollbar {
  width: 1.3vw;
  border-radius: 15px;
  background: #3b424d;
}

::-webkit-scrollbar-track {
  background-color: #3b424d;
  border-radius: 15px;
}

::-webkit-scrollbar-thumb {
  border-radius: 15px;
  -webkit-box-shadow: inset 0 0 2px rgba(0, 0, 0, 0.5);
  background: rgba(0, 0, 0, 0.349);
}

.background {
  background-color: #292c35;
  width: 100vw;
  height: 100vh;
  color: white;
  display: flex;
}

.wrapper-wrapper {
  width: 83%;
  height: 100vh;
}

.topbar-wrapper {
  border-bottom: #343942;
  border-left: transparent;
  border-right: transparent;
  border-top: transparent;
  border-style: solid;
  border-width: 0.2vw;
  width: 100%;
  height: 10%;
}

.user-info-wrapper {
  margin-top: -0.1%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: right;
}

.user-info {
  font-family: Poppins-light;
  font-size: 1.9rem;
  margin: 0;
}

.profile-picture {
  border-radius: 100%;
  border-color: #2f3468;
  border-style: solid;
  height: 76%;
  margin: 2.4%;
}

.main-content-wrapper {
  width: 100%;
  height: 100%;
  padding: 6%;
  display: flex;
}

.inventory-wrapper {
  width: 45vw;
  min-height: 70vh;
  background-color: #2f353f;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  overflow-y: scroll;
  padding-bottom: 24vh;
}

.ship-wrapper {
  margin-left: 3vw;
  margin-top: 4vh;
  width: 35vw;
  min-height: 26vh;
  max-height: 32vh;
  border-radius: 20px;
  background-color: #3B424D;
  display: flex;
  flex-direction: column;
}

.ship-data-wrapper{
  margin-top: 1.6vh;
  margin-left: 1.6vh;
  display: flex;
}

.ship-image{
  border-radius: 10px;
  width: 30vh;
  height: 22.5vh;
  object-fit: cover;
  object-position: center;
}

.ship-data{
  width: 19vw;
}

.ship-name-text{
  font-family: Poppins-extra-light;
  font-size: 1.75rem;
  margin: 0;
  margin-left: 1vw;
}

.ship-secondary-text{
  font-family: Poppins-extra-light;
  font-size: 1.25rem;
  margin: 0;
  margin-top: 0.8vh;
  margin-left: 1vw;
}

.ship-description-text{
  font-family: Poppins-extra-light;
  font-size: 0.9rem;
  margin-top: 0.5vh;
  margin-bottom: 0;
  margin-left: 1vw;
}



.technology-wrapper {
  width: 22vw;
  height: 75vh;
  background-color: #2b2f38;
  border-radius: 20px;
  border-style: solid;
  border-color: #2f353f;
  margin-left: 8vw;
  display: flex;
  flex-direction: column;
}

.tech-label {
  font-family: Poppins-regular;
  font-size: 2rem;
  text-align: center;
}

.tech-text {
  font-family: Poppins-extra-light;
  font-size: 1.2rem;
  width: 87%;
  margin: 0;
}

.circle-bullet {
  border-radius: 100%;
  width: 2vh;
  height: 2vh;
  margin: 0.5vh;
}

.tech-tech {
  display: flex;
  margin-left: 1vw;
  margin-top: 1.2vw;
}
</style>
