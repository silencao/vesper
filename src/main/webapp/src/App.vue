<template>
  <div id="app">
    <label>
      level
      <input type="text" v-model="item.level" />
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
      list: get('list') || []
    };
  },
  components: {},
  methods: {
    handleClick() {
      this.list.push(this.item);
      this.item = {
        date: new Date().toJSON()
      };
      set('list', this.list);
    }
  },
  created() {
    this.$http.get('/api/growth').then(res => {
      console.log(res);
    });
  }
};
</script>

<style lang="stylus"></style>
