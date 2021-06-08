<template>
<div>hello {{ who }}{{ '!' }}</div>
<router-view></router-view>
</template>

<script setup>
import {onMounted} from 'vue';
import {useRouter} from 'vue-router';

const who = 'world' + '!';

onMounted(() => {
    const router = useRouter();
    router.push({path:'/user'});

    fetch('/api/entity')
})

</script>

<style>

</style>