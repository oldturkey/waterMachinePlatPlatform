/**
 * Created by hao.cheng on 2017/4/16.
 */
import axios from 'axios';
import { get } from './tools';
import { post } from './tools';
import * as config from './config';


// easy-mock数据交互
// 管理员权限获取
export const admin = () => get({url: config.MOCK_AUTH_ADMIN});

// 访问权限获取
export const guest = () => get({url: config.MOCK_AUTH_VISITOR});



//测试代码
export const getAlertTable = () =>get({url: './npm1.json'}); 

//首页信息
export const HomeUserInfo = () =>get({url: '/home/user/info',headers: {'Authorization': 'token'}}); 
//首页报警表
export const homeAlertTable = () =>get({url: '/home/device/hour-alarm',headers: {'Authorization': 'token'}}); 
//首页供水表
export const homeSupply = () =>get({url: '/home/delivery/hour-record',headers: {'Authorization': 'token'}}); 

// 设备管理
// 设备统计
export const deviceStatistics = () =>get({url: '/device/statistics',headers: {'Authorization': 'token'}}); 
// 设备供水信息
export const deviceSupply = (params) =>post({
 	url: '/device/statistics',
 	data:{displayId:params.displayId,location:params.location},
 	headers: {'Authorization': 'token'}
 }); 
//设备连接信息
export const deviceConnect = (params) =>post({
 	url: '/device/connect',
 	data:{displayId:params.displayId,beginTime:params.beginTime,endTime:params.endTime},
 	headers: {'Authorization': 'token'}
 }); 
//设备权限管理
//获取所有设备信息
export const deviceManage = () =>get({url: '/device/manage',headers: {'Authorization': 'token'}}); 
//添加设备
export const deviceAdd = (params) =>post({
 	url: '/device/add',
 	data:{imei:params.imei,address:params.address,sim:params.sim,account:params.account},
 	headers: {'Authorization': 'token'}
 }); 
//删除设备
export const devicedelete = (params) =>post({
 	url: '/device/delete',
 	data:{displayId:params.displayId},
 	headers: {'Authorization': 'token'}
 });


//用户管理
// 用户统计
export const userStatistics = () =>get({url: '/user/statistics',headers: {'Authorization': 'token'}}); 
//用户信息
export const userInfo = (params) =>post({
 	url: '/user/info',
 	data:{phone:params.phone},
 	headers: {'Authorization': 'token'}
 });
//奖励金发放
export const recharge = (params) =>post({
 	url: '/user/info/provide',
 	data:{adminName:params.adminName,phone:params.phone,money:params.money,time:params.time},
 	headers: {'Authorization': 'token'}
 });

//查询管理
//设备供水查询
export const querySupply = (params) =>post({
 	url: '/query/device/supply',
 	data:{phone:params.phone,displayid:params.displayid,beginTime:params.beginTime,endTime:params.endTime},
 	headers: {'Authorization': 'token'}
 });
//充值查询
export const queryRecharge = (params) =>post({
 	url: '/query/recharge',
 	data:{phone:params.phone,beginTime:params.beginTime,endTime:params.endTime},
 	headers: {'Authorization': 'token'}
 });
//消费查询
export const queryPayment = (params) =>post({
 	url: '/query/payment',
 	data:{phone:params.phone,beginTime:params.beginTime,endTime:params.endTime},
 	headers: {'Authorization': 'token'}
 });

//反馈管理
export const queryFeedback = (params) =>post({
 	url: '/feedback/query',
 	data:{phone:params.phone,beginTime:params.beginTime,endTime:params.endTime},
 	headers: {'Authorization': 'token'}
 });
//删除反馈
export const deleteFeedback = (params) =>post({
 	url: '/feedback/delete',
 	data:{id:params.id},
 	headers: {'Authorization': 'token'}
 });
//更新反馈
export const updateFeedback = (params) =>post({
 	url: '/feedback/update',
 	data:{id:params.id,status:params.status},
 	headers: {'Authorization': 'token'}
 });


//账号管理
export const adminInfo = () =>get({url: '/account/info',headers: {'Authorization': 'token'}}); 
//修改密码
export const passwordChange = (params) =>post({
 	url: '/account/password/change',
 	data:{account:params.account,password:params.password,changePassword:params.changePassword},
 	headers: {'Authorization': 'token'}
 });
//创建账户
export const adminCreate = (params) =>post({
 	url: '/account/create',
 	data:{account:params.account,password:params.password,email:params.email,type:params.type},
 	headers: {'Authorization': 'token'}
 });

//远程调试
export const deviceDebug = (params) =>post({
 	url: '/debug/device',
 	data:{displayid:params.displayid,openTime:params.openTime,pulse:params.pulse,hotpulse:params.hotpulse,heartRate:params.heartRate},
 	headers: {'Authorization': 'token'}
 });
//系统日志
export const syestemLog = (params) =>post({
 	url: '/log',
 	data:{displayid:params.displayid,openTime:params.openTime,pulse:params.pulse,hotpulse:params.hotpulse,heartRate:params.heartRate},
 	headers: {'Authorization': 'token'}
 });
//登录
export const login = (params) => axios.post('/login', {
    name:params[0],
    password:params[1],
}).then(function (response) {
    return response.data;
}).catch(function (error) {
    console.log(error);
});


