<template>
    <div>
        用户名：<input type="text" v-model="username" /> 密 码：<input
            type="text"
            v-model="password"
        />
        <button type="button" @click="reg">注册</button>

        <RouterView />
    </div>
</template>

<script lang="ts">
import axios from 'axios';
import { defineComponent, ref } from 'vue';

export default defineComponent(function App() {
    const username = ref('');
    const password = ref('');
    const reg = () => {
        axios
            .post('/user/add', {
                username: username.value,
                password: password.value
            })
            .then(res => console.log(res.data));
    };

    return { username, password, reg };
});
</script>

<style lang="stylus"></style>
