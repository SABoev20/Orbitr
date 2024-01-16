<script setup lang="ts">
import Marketplace from '@/components/Marketplace.vue'
</script>

<template>
  <div class="welcome">
    <Marketplace />
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
