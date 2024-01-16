<script setup lang="ts">
import LoginForm from '@/components/LoginForm.vue'
</script>

<template>
  <div class="welcome">
    <LoginForm />
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
