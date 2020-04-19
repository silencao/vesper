<template>
  <div id="app">
    <label>level
      <input type="text" v-model="item.level" />
    </label>
    <label>sum
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
        <tr v-for="obj in list" :key="obj.level">
          <td>{{ obj.level }}</td>
          <td>{{ obj.sum }}</td>
          <td>{{ obj.date }}</td>
        </tr>
      </tbody>
    </table>
    <Test/>
    <RouterView/>
  </div>
</template>

<script>
import { set, get } from '@utils/localStorageUtil';
import Test from "./views/test";


export default {
  data() {
    return {
      item: {
        date:new Date().toJSON()
      },
      list: get('list') || []
    };
  },
  components:{
    Test
  },
  methods: {
    handleClick() {
      this.list.push(this.item);
      this.item={
        date:new Date().toJSON()
      }
      set('list', this.list);
    }
  },
  created() {
  }
};
</script>

<style lang="stylus"></style>
