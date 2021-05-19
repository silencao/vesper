import {createApp} from 'vue'
import App from './App.vue'
import Lib from './lib'

console.log(Lib.now());
createApp(App).mount('#app')

