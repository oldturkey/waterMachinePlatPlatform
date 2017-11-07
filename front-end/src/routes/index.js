/**
 * Created by 叶子 on 2017/8/13.
 */
import React, { Component } from 'react';
import { Router, Route, hashHistory, IndexRedirect,IndexRoute  } from 'react-router';
import App from '../App';
import Page from '../components/Page';
import DeviceStatistics from '../components/device/deviceStatistics';
import DeviceSupply from '../components/device/DeviceSupply';
import DeviceConnect from '../components/device/DeviceConnect';
import DeviceManage from '../components/device/DeviceManage';
import UserStatistics from '../components/user/UserStatistics';
import UserInfo from '../components/user/UserInfo';
import Payment from '../components/query/Payment';
import Recharge from '../components/query/Recharge';
import Supply from '../components/query/Supply';
import AdminManage from '../components/admin/AdminManage';
import FeedBack from '../components/feedback/FeedBack';
import Log from '../components/log/Log';
import Login from '../components/pages/Login';
import Dashboard from '../components/dashboard/Dashboard';
import NotFound from '../components/pages/NotFound';
import AuthBasic from '../components/auth/Basic';
import RouterEnter from '../components/auth/RouterEnter';


export default class CRouter extends Component {
    requireAuth = (permission, component) => {
        const { store } = this.props;
        const { auth } = store.getState().httpData;
        if (!auth || !auth.data.permissions.includes(permission)) hashHistory.replace('/404');
        return component;
    };
    render() {
        return (
            <Router history={hashHistory}>
                <Route path={'/'} components={Page}>
                    <IndexRedirect to="/login" />
                    <Route path={'app'} component={App}>
                        <IndexRoute component={Dashboard}/>
                        <Route path={'device'}>
                            <Route path={'statistics'} component={DeviceStatistics} />
                            <Route path={'supply'} component={DeviceSupply} />
                            <Route path={'connect'} component={DeviceConnect} />
                            <Route path={'manage'} component={DeviceManage} />
                        </Route>
                        <Route path={'user'}>
                            <Route path={'statistics'} component={UserStatistics} /> 
                            <Route path={'info'} component={UserInfo} />   
                        </Route>
                        <Route path={'query'}>
                            <Route path={'payment'} component={Payment} /> 
                             <Route path={'recharge'} component={Recharge}/>
                             <Route path={'supply'} component={Supply}/>
                        </Route>
                        <Route path={'account'}>
                            <Route path={'manage'} component={AdminManage} /> 
                        </Route>
                        <Route path={'feedback'} component={FeedBack} />
                        <Route path={'log'} component={Log} />                        
                        <Route path="auth">
                            <Route path="basic" component={AuthBasic} />
                            <Route path="routerEnter" component={(props) => this.requireAuth('auth/testPage', <RouterEnter {...props} />)} />
                        </Route>
                        <Route path={'dashboard'}>
                            <Route path={'index'} component={Dashboard} /> 
                        </Route>
                    </Route>

                    <Route path={'login'} components={Login} />
                    <Route path={'404'} component={NotFound} />
                </Route>
            </Router>
        )
    }
}