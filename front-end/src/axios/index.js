/**
 * Created by hao.cheng on 2017/4/16.
 */
import axios from 'axios';
import { get } from './tools';
import * as config from './config';


// easy-mock数据交互
// 管理员权限获取
export const admin = () => get({url: config.MOCK_AUTH_ADMIN});

// 访问权限获取
export const guest = () => get({url: config.MOCK_AUTH_VISITOR});



//测试代码
export const getAlertTable = () => axios.get('./npm1.json').then(res => res.data).catch(err => console.log(err));
// export const getAlertTable = () =>({
//      method: 'post',
//      url: '/home/device/hour-alarm',
//      headers: {'Authorization': 'token'},
// }).then(function (response) {
//     return response.data;
// }).catch(function (error) {
//     console.log(error);
// });


export const login = (params) => axios.post('/login', {
    name:params.userName,
    password:params.password,
}).then(function (response) {
    return response.data;
}).catch(function (error) {
    console.log(error);
});


