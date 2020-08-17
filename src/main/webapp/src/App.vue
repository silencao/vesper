<template>
    <div>{{ readersNumber }} {{ book.title }}

        <RouterView />
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, reactive } from 'vue';

export default defineComponent({
    name: 'App',
    props: {
        user: String
    },
    setup(props, ctx) {
        console.log(props);
        const readersNumber = ref(0);
        const book = reactive({ title: 'Vue 3 Guide' });

        // expose to template
        return {
            readersNumber,
            book
        };
    }
});
</script>

<style lang="stylus"></style>
