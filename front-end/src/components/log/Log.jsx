import React from 'react';
import { Form,  Input, Button,Table, Icon ,Row,Col,Popconfirm,Modal,DatePicker,message,Card ,Select} from 'antd';
import styles from '../query/TableList.less';
import BreadcrumbCustom from '../BreadcrumbCustom';
const FormItem = Form.Item;
const RangePicker = DatePicker.RangePicker;
const Option = Select.Option;

class Log extends React.Component {
  state = {
    data:[],
  };
  
  handleSearch = (e) => {
    e.preventDefault();
    const token = window.localStorage["token"];
    this.props.form.validateFields((err, fieldsValue) => {
      if (err) {
        return;
      }
      let beginTime=null;
      let endTime=null;
      // Should format date value before submit.
      const rangeTimeValue = fieldsValue['orderTime'];
      if(rangeTimeValue){
         beginTime = rangeTimeValue[0].format('YYYY-MM-DD HH:mm:ss');
         endTime = rangeTimeValue[1].format('YYYY-MM-DD HH:mm:ss');
      }
      const _this = this;
      // $.ajax({
      //   url:'/feedback/get',
      //   dataType:'json',
      //   headers: {
      //     'Authorization': token,
      //   },
      //   data:{phone:fieldsValue['phone']?fieldsValue['phone']:'',beginTime:beginTime?beginTime:'',endTime:endTime?endTime:''},
      //   success:function(data){
      //     if(data.status===1){
      //         _this.setState({data:data.feedbackContent});
      //     }else{
      //       message.error('查询失败');
      //     }
      //   },
      //   error:function(xhr,status,err){
      //     console.error(this.props.url,status,err.toString());
      //   }.bind(this)
      // });
      // 
    });
  }
  handleReset = () => {
    this.props.form.resetFields();
  }
  render() {
    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 12 },
    };

    const columns = [{
      title: '操作类型',
      dataIndex: 'useType',
      key: 'useType',
    }, {
      title: '操作人',
      dataIndex: 'user',
      key: 'user',
      }, {
        title: '操作时间',
        dataIndex: 'useTime',
        key: 'useTime',
      },{
        title: '执行结果',
        dataIndex: 'result',
        key: 'result',
        render: (text) => {
        if (text === 66) {
          return <div><span style={{color:"#87D068",fontSize: 15,paddingRight: '10px'}}>●</span>成功</div>;
        }else if (text === 23) {
          return <div><span style={{color:"#CCC",fontSize: 15,paddingRight: '10px'}}>●</span>失败</div>;
        } 
      },
      },{
        title: '备注',
        dataIndex: 'Remarks',
        key: 'Remarks',
      }];

    return (
    <div className="gutter-example button-demo">
      <BreadcrumbCustom first="系统日志" />
      <Row>
          <Col className="gutter-row" md={24}>
            <div className="gutter-box">
              <Card bordered={false} title="系统操作日志查询" className={'no-padding'}>
                <Form onSubmit={this.handleSearch} layout="inline">
                  <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
                    <Col md={7} sm={24} offset={2}>
                      <FormItem label="操作人">
                        {getFieldDecorator('user')(
                         <Input placeholder="请输入" />
                        )}
                      </FormItem>
                    </Col>
                    <Col md={7} sm={24}>
                      <FormItem {...formItemLayout} label={`操作时间`} >
                      {getFieldDecorator(`useTime`)(
                        <RangePicker showTime format="YYYY-MM-DD HH:mm:ss" />
                      )}
                      </FormItem>
                    </Col>
                    <Col md={7} sm={24} offset={2}>
                      <FormItem label="操作类型">
                        {getFieldDecorator('useType')(
                         <Select  style={{ width: 120 }} >
                          <Option value="账户登录">账户登录</Option>
                          <Option value="账户创建">账户创建</Option>
                          <Option value="设备增删">设备增删</Option>
                          <Option value="金额发放">金额发放</Option>
                          <Option value="远程调试">远程调试</Option>
                        </Select>
                        )}
                      </FormItem>
                    </Col>
                    <Col md={4} sm={24} >
                      <FormItem label="执行结果">
                        {getFieldDecorator('result')(
                         <Select  style={{ width: 120 }} >
                          <Option value="success">成功</Option>
                          <Option value="fail">失败</Option>
                        </Select>
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
              <Table columns={columns}  dataSource={this.state.data} />
              </Card>
            </div>
          </Col>                     
        </Row>
      </div>
    );
  }
}

const WrappedApp = Form.create()(Log);
export default WrappedApp;