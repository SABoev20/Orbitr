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
        <p class="greet-label">Welcome {{userData?.username}},</p>
        <div class="divs-wrapper">
          <div class="transactions-wrapper">
            <img class="badge-image" src="../assets/images/rocket-badge.png" />
            <p class="transaction-label">Last transactions:</p>
            <div class="transaction-div" v-for="(item, index) in transactionDataList" :key="index">{{ item.description }}</div>
          </div>
          <div class="stats-wrapper-wrapper">
            <div class="stats-wrapper">
              <div class="stats-div">
                <img class="stats-icon" src="../assets/images/orbit-icon.png" />
                <p class="stats-text">Earth orbits running:</p>
                <p class="stats-data">3.81</p>
              </div>
              <div class="stats-div">
                <img class="stats-icon" src="../assets/images/orbit-icon.png" />
                <p class="stats-text">Ships you collected:</p>
                <p class="stats-data">{{ spaceshipsNum }}</p>
              </div>
              <div class="stats-div">
                <img class="stats-icon" src="../assets/images/orbit-icon.png" />
                <p class="stats-text">Current balance:</p>
                <p class="stats-data">${{userData?.credits}}</p>
              </div>
            </div>
            <div class="banner-wrapper">
              <img class="banner-image" src="../assets/images/orbit-banner.png" />
              <p class="banner-text">
                Orbitr is a platform that helps you explore the cosmos by trading with
                advanced-technology spaceships.
              </p>
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
  name: string;
  description: string;
  labelColor: string;
}

interface SpaceshipData {
  name: string;
  model: string;
  technology: TechnologyData;
  description: string;
  picture: string;
  user: UserData;
}

interface TransactionData {
  spaceship: SpaceshipData;
  transactionType: string;
  description: string;
  price: number;
  transactionDate: string;
}


const token = sessionStorage.getItem('token')
const userData = ref<UserData | null>(null)
const spaceshipsNum = ref<string | null>(null)
const transactionDataList = ref<TransactionData[]>([]);
const firstThreeItems = ref<TransactionData[]>([]);

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
    const response = await axios.get('http://localhost:8080/transactions/', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    transactionDataList.value = response.data as TransactionData[]
  } catch (error) {
    console.error('Error fetching user data:', error)
  }

  try {
    const response = await axios.get('http://localhost:8080/spaceships/collected', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    spaceshipsNum.value = response.data
  } catch (error) {
    console.error('Error fetching user data:', error)
  }
}

onMounted(() => {
  fetchUserData()
  firstThreeItems.value = transactionDataList.value.slice(0, 3);
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
  padding-left: 6%;
  padding-right: 6%;
}

.greet-label {
  font-family: Poppins-regular;
  font-size: 2.9rem;
  background: linear-gradient(50deg, rgba(207, 206, 206, 1) 0%, rgba(136, 136, 136, 0.22) 24%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin: 0;
  padding-top: 3%;
  padding-bottom: 1%;
}

.divs-wrapper {
  width: 88%;
  height: 69%;
  display: flex;
}

.transactions-wrapper {
  background-color: rgba(47, 53, 63, 0.3);
  width: 28%;
  height: 100%;
  border-radius: 20px;
  border-color: #2f353f;
  border-style: solid;
  display: flex;
  align-items: center;
  flex-direction: column;
}

.badge-image {
  margin: 10%;
  width: 40%;
}

.transaction-label {
  font-family: Poppins-regular;
  font-size: 1.37rem;
  background: linear-gradient(60deg, rgba(207, 206, 206, 1) 10%, rgba(136, 136, 136, 0.22) 115%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin: 0;
}

.transaction-div {
  background-color: #373d49;
  border-radius: 10px;
  margin-top: 6%;
  width: 90%;
  height: 15%;
}

.stats-wrapper-wrapper {
  display: flex;
  flex-direction: column;
  width: 72%;
  height: 100%;
  margin-left: 0%;
}

.stats-wrapper {
  display: flex;
  justify-content: space-between;
  width: 90%;
  height: 30%;
  margin-left: 10%;
}

.stats-div {
  width: 30%;
  height: 100%;
  background-color: #2f353f;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
}

.stats-icon {
  width: 12%;
  margin-top: 10%;
  margin-left: 10%;
}

.stats-text {
  font-family: Poppins-regular;
  font-size: 1.3rem;
  background: linear-gradient(90deg, rgba(207, 206, 206, 1) 0%, rgba(136, 136, 136, 0.22) 95%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin: 0;
  margin-top: 5%;
  margin-left: 10%;
}

.stats-data {
  font-family: Poppins-regular;
  font-size: 2.9rem;
  margin: 0;
  margin-top: 3%;
  margin-left: 10%;
}

.banner-wrapper {
  width: 90%;
  height: 67%;
  background-color: rgba(47, 53, 63, 0.3);
  border-color: #2f353f;
  border-radius: 20px;
  border-style: solid;
  margin-left: 10%;
  margin-top: 3%;
  margin-bottom: -0.5%;
  display: flex;
  align-items: center;
  flex-direction: column;
  text-align: center;
}

.banner-image {
  margin-top: 2%;
  width: 95%;
}

.banner-text {
  font-family: Poppins-extra-light;
  font-size: 1.6rem;
  margin: 1%;
}
</style>
