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
    this.$http.get('/growth').then(res => {
      console.log(res);
    });
    this.$http.post('/growth/test?rp=123', {
      sum: '666.2t',
      level: '750'
    });
    this.$http.put('/growth/test', {
      test: 'put'
    });
  }
};
</script>

<style lang="stylus"></style>
