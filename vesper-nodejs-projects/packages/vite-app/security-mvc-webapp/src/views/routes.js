import Login from './main/Login.vue';
import UserEdit from './user/UserEdit.vue';
import UserList from './user/UserList.vue';


export default [
    {path: '/login', component: Login},
    {path: '/user', component: UserList},
    {path: '/user/:id', component: UserEdit},

]