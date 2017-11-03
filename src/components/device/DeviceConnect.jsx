
import React from 'react';
import { Row, Col ,Table,Form,Input, Button,Card,Icon,DatePicker} from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
const FormItem = Form.Item;
const RangePicker = DatePicker.RangePicker;

const flowColumns = [{
        title: '设备编号',
        dataIndex: 'displayId',
      },{
        title: '设备地址',
        dataIndex: 'location',
      }, {
        title: 'SIM卡号',
        dataIndex: 'simId',
      },{
        title: 'IMEI号',
        dataIndex: 'imei',
      },{
        title: '状态',
        dataIndex: 'monthFlow',
      },{
        title: '掉线时间',
        dataIndex: 'lastConnectTime',
      },{
        title: '断线时长',
        dataIndex: 'offlineTime',
      }];
class DeviceConnect extends React.Component {
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
                <BreadcrumbCustom first="设备管理" second="设备连接信息"/>
                <Row gutter={30}>
                    <Col className="gutter-row" md={24}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="database" className="text-2x text-info"/>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">断线设备次数</div>
                                        <h2>301</h2>
                                    </div>
                                </div>
                            </Card>
                        </div>   
                    </Col>                   
                    <Col className="gutter-row" md={24}>
                        <div className="gutter-box">
                            <Card bordered={false} title="设备连接报警列表" className={'no-padding'}>
                                <div className="gutter-box">
                                    <Form
                                    className="ant-advanced-search-form"
                                    onSubmit={this.handleSearch}
                                    style={{padding:'30px 5px',marginBottom:20}}
                                     >
                                    <Row>
                                      <Col span={12} key={1} >
                                        <FormItem {...formItemLayout} label='设备编号'>
                                        {getFieldDecorator('displayId', {
                                        rules: [{ required: true, message: '请输入设备编号!' }],
                                      })(
                                        <Input placeholder="请输入设备编号" />
                                      )}
                                        </FormItem>
                                      </Col>
                                      
                                      <Col span={12} key={2} >
                                        <FormItem {...formItemLayout} label={`订单时间`} >
                                          {getFieldDecorator(`orderTime`)(
                                             <RangePicker showTime format="YYYY-MM-DD HH:mm:ss" />
                                          )}
                                        </FormItem>
                                      </Col>
                                      <Col span={24} offset={17}>
                                        <Button type="primary" htmlType="submit">搜索</Button>
                                        <Button style={{ marginLeft: 8 }} onClick={this.handleReset}>
                                          清空
                                        </Button>
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

const DevConnect = Form.create()(DeviceConnect);
export default DevConnect;