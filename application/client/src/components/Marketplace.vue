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
        <div class="credits-wrapper">
          <p class="credits-text">Credits {{userData?.credits}}</p>
        </div>
        <div class="market-wrapper">
          <div class="offer-wrapper" v-for="(offer, index) in offersList" :key="index">
            <img class="offer-image" :src="offer.spaceship.picture">
            <div class="data-wrapper">
              <p class="ship-name">{{ offer.spaceship.name }}</p>
              <p class="ship-data-text">Model: {{ offer.spaceship.model }}</p>
              <p class="ship-data-text">Technology: {{ offer.spaceship.technology.name }}</p>
              <p class="ship-description">Description: {{ offer.spaceship.description }}</p>
            </div>
            <div class="secondary-wrapper">
              <div @click="buyOffer(offer)" class="buy-button">
                <p class="price-text">Price: {{ offer.price }}</p>
              </div>
                <div class="seller-wrapper">
                  <p class="seller-names">{{ offer.user.firstName }} {{ offer.user.lastName }}</p>
                  <img class="offer-profile-picture" :src="offer.user.profilePicture" />
                </div>
            </div>
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
  user: UserData
}

interface OfferData {
  user: UserData,
  spaceship: SpaceshipData,
  price: number
}

const token = sessionStorage.getItem('token')
const userData = ref<UserData | null>(null)
const offersList = ref<OfferData[]>([]);

async function buyOffer(offer: OfferData){
  try {
    axios.post('http://localhost:8080/offers/purchase', offer, {
  headers: {
    'Authorization': `Bearer ${token}`
  }
})
  } catch (error) {
    console.error('Error fetching user data:', error)
  }
}

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
    const response = await axios.get('http://localhost:8080/offers/available', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    offersList.value = response.data as OfferData[]
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
  padding-left: 4%;
  display: flex;
  flex-direction: column;
}

.credits-wrapper {
  width: 14%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 76%;
  margin-top: 2%;
  background-color: #3b424d;
  border-radius: 20px;
}

.credits-text {
  font-family: Poppins-regular;
  font-size: 1.37rem;
  margin: 4%;
  background: linear-gradient(90deg, rgba(207, 206, 206, 1) 20%, rgba(136, 136, 136, 0.22) 110%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.market-wrapper {
  background-color: #2f353f;
  border-radius: 20px;
  width: 85%;
  min-height: 70vh;
  overflow-y: scroll;
  display: flex;
  flex-direction: column;
  align-items: space-between;

  margin-top: 3%;
  padding-top: 2.5vh;
  padding-bottom: 100vh;
  padding-left: 4vw;
}

.offer-wrapper {
  margin-top: 2.5vh;
  width: 92%;
  min-height: 22vh;
  max-height: 22vh;
  background-color: #3B424D;
  border-radius: 15px;
  display: flex;
}

.offer-image{
  margin-top: 1vh;
  margin-left: 1vh;
  border-radius: 10px;
  width: 26.6vh;
  height: 20vh;
  object-fit: cover;
  object-position: center;
}

.data-wrapper{
  margin-left: 1vw;
}

.ship-name{
  font-family: Poppins-light;
  font-size: 1.75rem;
  margin: 0;
  margin-top: 1vh;
}

.ship-data-text{
  font-family: Poppins-extra-light;
  font-size: 1.25rem;
  margin: 0;
  margin-top: 0.5vh;
}

.ship-description{
  font-family: Poppins-extra-light;
  width: 30vw;
  margin: 0;
  margin-top: 1vh;
}

.secondary-wrapper{
  margin-left: 7.5vw;
}

.buy-button{
  margin-top: 1vh;
  margin-bottom: 7.5vh;
  border-radius: 10px;
  background-color: #373D49;
  width: 10.5vw;
  height: 5.5vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.price-text{
  font-family: Poppins-light;
  font-size: 1.75rem;
}

.seller-wrapper{
  display: flex; 
  justify-content: right;
}

.seller-names{
  font-family: Poppins-light;
  font-size: 1.25rem;
}

.offer-profile-picture{
  width: 2.8vw;
  height: 2.8vw;
  margin-top: 0.6vh;
  margin-left: 1vh;
  border-radius: 100%;
}

</style>
