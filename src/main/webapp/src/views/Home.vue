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
export default {
    name: 'Home',
    data() {
        return {
            item: {
                date: new Date().toJSON()
            },
            list: JSON.parse(localStorage.getItem('list')) || [],
            text: ''
        };
    },
    methods: {
        handleClick() {
            this.item.sum = this.item.sum.padStart(6);
            this.text = JSON.stringify(this.item, ['date', 'level', 'sum']);
            this.list.push(this.item);
            this.item = {
                date: new Date().toJSON()
            };
            localStorage.setItem('list', JSON.stringify(this.list));
            navigator.clipboard
                .writeText(this.text)
                .then(() => console.log('复制成功！'));
        }
    },
    created() {
        this.$http.get('/growth').then(
            res => {
                console.log(res);
            },
            ({ response: { data, status } }) => {
                console.log(this);

                if (status === 401) {
                    console.log(data);
                    this.$router.push('/login');
                }
            }
        );
    }
};
</script>
