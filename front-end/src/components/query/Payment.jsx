
import React from 'react';
import { Row, Col, Card, Form, Input, Select, Icon, Button, Dropdown, Menu, InputNumber, DatePicker, Modal, message,Table } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import styles from './TableList.less';
const FormItem = Form.Item;
const RangePicker = DatePicker.RangePicker;
const { Option } = Select;
const flowColumns = [{
        title: '设备编号',
        dataIndex: 'displayId',
      },{
        title: '手机号',
        dataIndex: 'phone',
      }, {
        title: '消费时间',
        dataIndex: 'time',
      },{
        title: '消费金额',
        dataIndex: 'payment',
      }];
class Payment extends React.Component {
    constructor(props) {
    super(props);
    this.state={date:[]}
  }
    handleSearch = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, fieldsValue) => {
      if (err) {
        return;
      }
    });
  }
  handleReset = () => {
    this.props.form.resetFields();
  }
    render() {
        const { getFieldDecorator } = this.props.form; 
        const formItemLayout = {
          labelCol: { span: 6 },
          wrapperCol: { span: 6 },
        };
        return (
            <div className="gutter-example button-demo">
                <BreadcrumbCustom first="查询管理" second="消费记录查询"/>
                <Row gutter={30}>                  
                  <Col className="gutter-row" md={24}>
                        <div className="gutter-box">
                            <Card bordered={false} title="消费记录查询" className={'no-padding'}>
                                <div className="gutter-box">
                                    <Form onSubmit={this.handleSearch} layout="inline">
                                      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
                                        <Col md={7} sm={24} offset={2}>
                                          <FormItem label="手机号">
                                            {getFieldDecorator('no')(
                                              <Input placeholder="请输入" />
                                            )}
                                          </FormItem>
                                        </Col>
                                        <Col md={7} sm={24}>
                                          <FormItem {...formItemLayout} label={`订单时间`} >
                                          {getFieldDecorator(`orderTime`)(
                                             <RangePicker showTime format="YYYY-MM-DD HH:mm:ss" />
                                          )}
                                          </FormItem>
                                        </Col>
                                        <Col md={4} sm={24} style={{marginTop:'20'}}>
                                          <span className={styles.submitButtons}>
                                            <Button type="primary" htmlType="submit">查询</Button>
                                            <Button style={{ marginLeft: 8 }} onClick={this.handleReset}>重置</Button>
                                          </span>
                                        </Col>
                                      </Row>
                                    </Form>
                                  <Row>
                                    <Col md={20} offset={2}>
                                    <Table columns={flowColumns} dataSource={[]}   bordered />
                                    </Col>
                                  </Row>
                                </div>
                            </Card>
                        </div>
                    </Col>                      
                </Row>
            </div>
        )
    }
}

const Pay = Form.create()(Payment);
export default Pay;