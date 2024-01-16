<script setup lang="ts">
import WelcomeLabel from '@/components/WelcomeLabel.vue'
</script>

<template>
  <div class="welcome">
    <WelcomeLabel />
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
