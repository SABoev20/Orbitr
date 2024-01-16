<template>
  <div class="background">
    <p class="heading">Orbitr</p>
    <p class="sub-heading">
      The Interstellar marketplace.<br />
      Explore the vast universe today by trading, buying and collecting<br> spaceships for
      outer-space travel
    </p>
    <RouterLink class="login-button" to="/login" style="text-decoration: none;">Login</RouterLink>
    <RouterLink class="register-button" to="/register" style="text-decoration: none;">Register</RouterLink>
  </div>
</template>

<script setup lang="ts"></script>

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

.heading {
  font-family: Poppins-extra-light;
  font-size: 6.875rem;
  margin: 0;
}

.sub-heading {
  font-family: Poppins-light;
  font-size: 1.5rem;
  max-width: 954px;
  text-align: center;
  color: #cae4ff;
}

.login-button {
  font-family: Poppins-regular;
  width: 245px;
  height: 50px;
  color: #000;
  font-size: 1.75rem;
  background-color: white;
  border-radius: 40px;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 1.4%;
  margin-bottom: 1.2%;
  text-decoration: none;
}

.register-button {
  font-family: Poppins-regular;
  width: 245px;
  height: 50px;
  color: white;
  font-size: 1.75rem;
  border-color: white;
  border-style: solid;
  border-radius: 40px;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
