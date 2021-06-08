<template>
<ul>
    <li v-for="user in users">{{ user.name }}</li>
</ul>
</template>

<script setup>
import {reactive} from 'vue';
// This starter template is using Vue 3 experimental <script setup> SFCs
// Check out https://github.com/vuejs/rfcs/blob/script-setup-2/active-rfcs/0000-script-setup.md

const users = reactive([
    {name: 'cjy', id: '259', data: '曹锦岩'}
]);

</script>

<style scoped>

</style>