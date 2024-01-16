<template>
  <div class="navbar-wrapper">
    <div class="logo-wrapper">
      <img class="logo" src="../assets/images/corner-logo.png" />
      <p class="sub-heading">Interstellar marketplace.</p>
    </div>
    <div class="nav-content-wrapper">
      <RouterLink to="/dashboard" class="content-wrapper">
        <img class="icon" src="../assets/images/dashboard-icon.png" />
        <p class="nav-text">Dashboard</p>
      </RouterLink>
      <RouterLink to="/marketplace" class="content-wrapper">
        <img class="icon" src="../assets/images/marketplace-icon.png" />
        <p class="nav-text">Marketplace</p>
      </RouterLink>
      <RouterLink to="/inventory" class="content-wrapper">
        <img class="icon" src="../assets/images/inventory-icon.png" />
        <p class="nav-text">Inventory</p>
      </RouterLink>
      <RouterLink to="/" class="content-wrapper">
        <img class="icon" src="../assets/images/profile-icon.png" />
        <p class="nav-text">Profile</p>
      </RouterLink>
    </div>
    <div @click="logout" id="logout-wrapper" class="content-wrapper">
      <img class="icon" src="../assets/images/logout-icon.png" />
      <p class="nav-text">Log Out</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import router from '@/router';

function logout(){
    sessionStorage.clear();
    router.push('/')
}
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


a{
    text-decoration: none;
}

.navbar-wrapper {
  width: 17vw;
  height: 100vh;
  border-right: #343942;
  border-left: transparent;
  border-top: transparent;
  border-style: solid;
  border-width: 0.2vw;
}

.logo-wrapper {
  width: 100%;
  padding-top: 8%;
  padding-left: 8%;
}

.logo {
  width: 50%;
}

.sub-heading {
  font-family: Poppins-extra-light;
  font-size: 1.25;
  margin: 0;
  margin-top: -4%;
  color: #b0b0b0;
}

.nav-content-wrapper {
  padding-left: 8%;
  margin-top: 40%;
}

.content-wrapper {
  display: flex;
  align-items: center;
  gap: 5%;
}

.content-wrapper :hover {
  color: #b0b0b0;
}

.icon {
  width: 30px;
}

.nav-text {
  font-family: Poppins-regular;
  font-size: 1.9rem;
  color: white;
  text-underline-offset: none;
}

#logout-wrapper {
  margin-top: 50%;
  margin-left: 10%;
}
</style>
