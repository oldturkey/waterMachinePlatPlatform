/**
 * Created by hao.cheng on 2017/4/13.
 */
import React, { Component } from 'react';
import { Layout, Menu, Icon } from 'antd';
import { Link } from 'react-router';
const { Sider } = Layout;
const SubMenu = Menu.SubMenu;

class SiderCustom extends Component {
    state = {
        collapsed: false,
        mode: 'inline',
        openKey: '',
        selectedKey: '',
        firstHide: true,        // 点击收缩菜单，第一次隐藏展开子菜单，openMenu时恢复
    };
    componentDidMount() {
        this.setMenuOpen(this.props);
    }
    componentWillReceiveProps(nextProps) {
        console.log(nextProps);
        this.onCollapse(nextProps.collapsed);
        this.setMenuOpen(nextProps)
    }
    setMenuOpen = props => {
        const {path} = props;
        this.setState({
            openKey: path.substr(0, path.lastIndexOf('/')),
            selectedKey: path
        });
    };
    onCollapse = (collapsed) => {
        console.log(collapsed);
        this.setState({
            collapsed,
            firstHide: collapsed,
            mode: collapsed ? 'vertical' : 'inline',
        });
    };
    menuClick = e => {
        this.setState({
            selectedKey: e.key
        });
        console.log(this.state);
        const { popoverHide } = this.props;     // 响应式布局控制小屏幕点击菜单时隐藏菜单操作
        popoverHide && popoverHide();
    };
    openMenu = v => {
        console.log(v);
        this.setState({
            openKey: v[v.length - 1],
            firstHide: false,
        })
    };
    render() {
        return (
            <Sider
                trigger={null}
                breakpoint="lg"
                collapsed={this.props.collapsed}
                style={{overflowY: 'auto'}}
            >
                <div className="logo" />
                <Menu
                    onClick={this.menuClick}
                    theme="dark"
                    mode="inline"
                    selectedKeys={[this.state.selectedKey]}
                    openKeys={this.state.firstHide ? null : [this.state.openKey]}
                    onOpenChange={this.openMenu}
                >
                    <Menu.Item key="/app/dashboard/index">
                        <Link to={'/app/dashboard/index'}><Icon type="home" /><span className="nav-text">首页</span></Link>
                    </Menu.Item>
                    <SubMenu
                        key="/app/device"
                        title={<span><Icon type="database" /><span className="nav-text">设备管理</span></span>}
                    >
                        <Menu.Item key="/app/device/statistics"><Link to={'/app/device/statistics'}>设备统计</Link></Menu.Item>
                        <Menu.Item key="/app/device/supply"><Link to={'/app/device/supply'}>设备供水信息</Link></Menu.Item>
                        <Menu.Item key="/app/device/connect"><Link to={'/app/device/connect'}>设备连接信息</Link></Menu.Item>
                        <Menu.Item key="/app/device/manage"><Link to={'/app/device/manage'}>设备权限管理</Link></Menu.Item>
                    </SubMenu>
                    <SubMenu
                        key="/app/user"
                        title={<span><Icon type="user" /><span className="nav-text">用户管理</span></span>}
                    >
                        <Menu.Item key="/app/user/statistics"><Link to={'/app/user/statistics'}>用户统计</Link></Menu.Item>
                        <Menu.Item key="/app/user/info"><Link to={'/app/user/info'}>用户信息及操作</Link></Menu.Item>
                    </SubMenu>
                    <SubMenu
                        key="/app/query"
                        title={<span><Icon type="search" /><span className="nav-text">查询管理</span></span>}
                    >
                        <Menu.Item key="/app/query/payment"><Link to={'/app/query/payment'}>消费记录查询</Link></Menu.Item>
                        <Menu.Item key="/app/query/recharge"><Link to={'/app/query/recharge'}>充值记录查询</Link></Menu.Item>
                        <Menu.Item key="/app/query/supply"><Link to={'/app/query/supply'}>设备供水查询</Link></Menu.Item>
                    </SubMenu>
                    <Menu.Item key="/app/account/manage">
                        <Link to={'/app/account/manage'}><Icon type="usergroup-add" /><span className="nav-text">账号管理</span></Link>
                    </Menu.Item>
                    <Menu.Item key="/app/feedback">
                        <Link to={'/app/feedback'}><Icon type="customer-service" /><span className="nav-text">反馈管理</span></Link>
                    </Menu.Item>
                    <Menu.Item key="/app/log">
                        <Link to={'/app/log'}><Icon type="calendar" /><span className="nav-text">系统日志</span></Link>
                    </Menu.Item>
                    <SubMenu
                        key="sub4"
                        title={<span><Icon type="switcher" /><span className="nav-text">页面</span></span>}
                    >
                        <Menu.Item key="/login"><Link to={'/login'}>登录</Link></Menu.Item>
                        <Menu.Item key="/404"><Link to={'/404'}>404</Link></Menu.Item>
                    </SubMenu>
                    <SubMenu
                        key="/app/auth"
                        title={<span><Icon type="safety" /><span className="nav-text">权限管理</span></span>}
                    >
                        <Menu.Item key="/app/auth/basic"><Link to={'/app/auth/basic'}>基础演示</Link></Menu.Item>
                        <Menu.Item key="/app/auth/routerEnter"><Link to={'/app/auth/routerEnter'}>路由拦截</Link></Menu.Item>
                    </SubMenu>
                </Menu>
                <style>
                    {`
                    #nprogress .spinner{
                        left: ${this.state.collapsed ? '70px' : '206px'};
                        right: 0 !important;
                    }
                    `}
                </style>
            </Sider>
        )
    }
}

export default SiderCustom;