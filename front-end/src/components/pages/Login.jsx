/**
 * Created by hao.cheng on 2017/4/16.
 */
import React from 'react';
import { Form, Icon, Input, Button, Checkbox } from 'antd';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { fetchData, receiveData } from '@/action';
import { Link } from 'react-router';

const FormItem = Form.Item;

class Login extends React.Component {
    componentWillMount() {
        const { receiveData } = this.props;
        receiveData(null, 'auth');
    }
    componentWillReceiveProps(nextProps) {
        const { auth: nextAuth = {} } = nextProps;
        const { router } = this.props;
        if (nextAuth.data && nextAuth.data.uid) {   // 判断是否登陆
            localStorage.setItem('user', JSON.stringify(nextAuth.data));
            router.push('/app');
        }
    }
    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                const { fetchData } = this.props;
                if (values.userName === 'admin' && values.password === 'admin') fetchData({funcName: 'admin', stateName: 'auth'});
                if (values.userName === 'guest' && values.password === 'guest') fetchData({funcName: 'guest', stateName: 'auth'});
                // fetchData({funcName:'login',stateName:'auth',params:[values.userName,values.password]});
            }
        });
    };
    
    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <div className="login">
                <div className="login-form" >
                    <div className="login-logo">
                        <span>管理平台</span>
                    </div>
                    <Form onSubmit={this.handleSubmit} style={{maxWidth: '300px'}}>
                        <FormItem>
                            {getFieldDecorator('userName', {
                                rules: [{ required: true, message: '请输入用户名!' }],
                            })(
                                <Input prefix={<Icon type="user" style={{ fontSize: 13 }} />} placeholder="管理员输入admin, 游客输入guest" />
                            )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('password', {
                                rules: [{ required: true, message: '请输入密码!' }],
                            })(
                                <Input prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="管理员输入admin, 游客输入guest" />
                            )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('remember', {
                                valuePropName: 'checked',
                                initialValue: true,
                            })(
                                <Checkbox>记住我</Checkbox>
                            )}
                            <Link to={'/forget'} className="login-form-forgot" href="" style={{float: 'right'}}>忘记密码</Link>
                            <Button type="primary" htmlType="submit" className="login-form-button" style={{width: '100%'}}>
                                登录
                            </Button>
                        </FormItem>
                    </Form>
                </div>
            </div>

        );
    }
}

const mapStateToPorps = state => {
    const { auth } = state.httpData;
    return { auth };
};
const mapDispatchToProps = dispatch => ({
    fetchData: bindActionCreators(fetchData, dispatch),
    receiveData: bindActionCreators(receiveData, dispatch)
});


export default connect(mapStateToPorps, mapDispatchToProps)(Form.create()(Login));