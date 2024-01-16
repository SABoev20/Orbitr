<script setup lang="ts">
import RegisterForm from '@/components/RegisterForm.vue'
</script>

<template>
  <div class="welcome">
    <RegisterForm />
  </div>
</template>

<style>
body {
  margin: 0;
  overflow: hidden;
}

.welcome {
  box-sizing: border-box;
  z-index: 1;
}
</style>
