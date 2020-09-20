import 'bootstrap/dist/css/bootstrap.min.css';

import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import axios from 'axios';

axios.defaults.baseURL = '/api';

(app => {
    const rootContainer = document.createElement('div');
    app.config.globalProperties.$http = axios;

    document.body.appendChild(app.mount(rootContainer).$el);
})(createApp(App)
    .use(store)
    .use(router)
);
