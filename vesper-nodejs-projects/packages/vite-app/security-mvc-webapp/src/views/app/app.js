import App from './App.vue';
import {createApp} from 'vue';
import {createRouter, createWebHashHistory} from 'vue-router';
import routes from '../routes.js';


const VueRouter = createRouter({
    history: createWebHashHistory(),
    routes
});

createApp(App)
    .use(VueRouter)
    .mount('#app');

