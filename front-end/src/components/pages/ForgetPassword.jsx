/**
 * Created by hao.cheng on 2017/4/16.
 */
import React from 'react';
import { Form, Icon, Input, Button, Checkbox } from 'antd';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { fetchData, receiveData } from '@/action';
import { passwordFound } from '../../axios';

const FormItem = Form.Item;

class forget extends React.Component {
    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                 passwordFound({params:[values.userName,values.email]}).then(res => {
                    
                 })
            }
        });
    };
    
    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <div className="login">
                <div className="login-form" >
                    <div className="login-logo">
                        <span>修改密码</span>
                    </div>
                    <Form onSubmit={this.handleSubmit} style={{maxWidth: '300px'}}>
                        <FormItem>
                            {getFieldDecorator('userName', {
                                rules: [{ required: true, message: '请输入用户名!' }],
                            })(
                                <Input prefix={<Icon type="user" style={{ fontSize: 13 }} />} placeholder="请输入用户名" />
                            )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('email', {
                                rules: [{ required: true, message: '请输入邮箱地址!' }],
                            })(
                                <Input prefix={<Icon type="inbox" style={{ fontSize: 13 }} />} type="email" placeholder="请输入邮箱地址" />
                            )}
                        </FormItem>
                        <FormItem>
                            <Button type="primary" htmlType="submit" className="login-form-button" style={{width: '100%'}}>
                                发送邮件
                            </Button>
                        </FormItem>
                    </Form>
                </div>
            </div>

        );
    }
}



export default (Form.create()(forget));