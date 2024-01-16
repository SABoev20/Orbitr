<script setup lang="ts">
import Inventory from '@/components/Inventory.vue'
</script>

<template>
  <div class="welcome">
    <Inventory />
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
