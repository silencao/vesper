<template>
    <div style="position: relative;height: 1440px;">
        <div
            v-for="(part,index) in aiDang"
            :key="index"
            :style="{
                width: part.width+'px',
                height: part.height+'px',
                display: 'inline-block',
                position:'absolute',
                background: `url(/Texture2D/aidang_2.png) ${part.x}px ${part.y}px no-repeat`,
                bottom:part.bottom,
                left:part.left
            }"
        ></div>
        <img src="public/Texture2D/aidang_2.png" />
    </div>
</template>

<script>
import aiDang from '../assets/json/aidang_2-mesh.json';

console.log(aiDang);
export default {
    name: 'Test',
    data() {
        return {
            aiDang
        };
    }
};
</script>

<style></style>
