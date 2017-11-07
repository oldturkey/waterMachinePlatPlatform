
import React from 'react';
import { Row, Col ,Table,Form,Input, Button,Card,Icon,DatePicker} from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import styles from '../query/TableList.less';
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
                                    <Form onSubmit={this.handleSearch} layout="inline">
                                      <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
                                        <Col md={7} sm={24} offset={2}>
                                          <FormItem label="设备编号">
                                            {getFieldDecorator('displayId')(
                                              <Input placeholder="请输入" />
                                            )}
                                          </FormItem>
                                        </Col>
                                        <Col md={7} sm={24}>
                                          <FormItem {...formItemLayout} label={`掉线时间`} >
                                          {getFieldDecorator(`lastConnectTime`)(
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

const DevConnect = Form.create()(DeviceConnect);
export default DevConnect;

 