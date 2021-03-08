<template>
    <div class="home">
        <label>
            level
            <input type="number" v-model.number="item.level" />
        </label>
        <label>
            sum
            <input type="text" v-model="item.sum" />
        </label>
        <label>
            <input type="checkbox" />
        </label>
        <button type="button" @click="handleClick">添加</button>
        <table>
            <thead>
                <tr>
                    <th>等级</th>
                    <th>勋章</th>
                    <th>日期</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="obj in list" :key="obj.sum">
                    <td>{{ obj.level }}</td>
                    <td>{{ obj.sum }}</td>
                    <td>{{ obj.date }}</td>
                </tr>
            </tbody>
        </table>
        <div>{{ text }}</div>
    </div>
</template>

<script>
import axios from 'axios';
import { defineComponent, ref, reactive } from 'vue';
import router from '../router';

export default defineComponent(function Home() {
    axios.get('/growth').then(
        res => {
            console.log(res.data);
        },
        ({ response: { data, status } }) => {
            if (status === 401) {
                console.log(data);
                router.push('/login');
            }
        }
    );

    let item = reactive({
        date: new Date().toJSON()
    });

    const list = reactive(JSON.parse(localStorage.getItem('list')) || []);
    const text = ref('');

    function handleClick() {
        item.sum = item.sum.padStart(6);
        text.value = JSON.stringify(item, ['date', 'level', 'sum']);
        list.push(item);
        item = reactive({
            date: new Date().toJSON()
        });
        localStorage.setItem('list', JSON.stringify(list));
        navigator.clipboard
            .writeText(text.value)
            .then(() => console.log('复制成功！'));
    }

    return {
        item,
        list,
        text,
        handleClick
    };
});
</script>
