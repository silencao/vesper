<template>
  <div id="app">
    <label>
      level
      <input type="number" v-model.number="item.level" />
    </label>
    <label
      >sum
      <input type="text" v-model="item.sum" />
    </label>
    <button type="button" @click="handleClick">添加</button>
    <button type="button" @click="login">快速登录</button>
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
    <RouterView />
  </div>
</template>

<script>
import { set, get } from '@utils/localStorageUtil';

export default {
  data() {
    return {
      item: {
        date: new Date().toJSON()
      },
      list: get('list') || [],
      text: ''
    };
  },
  components: {},
  methods: {
    login() {
      this.$http.post('/doLogin', 'username=admin&password=jiny1').then(
        res => {
          console.log(res);
        },
        rej => {
          console.log(rej);
        }
      );
    },
    handleClick() {
      this.text = JSON.stringify(this.item);
      this.list.push(this.item);
      this.item = {
        date: new Date().toJSON()
      };
      set('list', this.list);
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
          console.log(data)
          this.$router.push('/login');
        }
      }
    );
    // this.$http
    //   .post('/growth/test?rp=123', {
    //     sum: '666.2t',
    //     level: '750'
    //   })
  }
};
</script>

<style lang="stylus"></style>
