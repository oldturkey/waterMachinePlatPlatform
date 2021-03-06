import React from 'react';
import { Row, Col ,Table,Form,Input, Button,Card } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import styles from '../query/TableList.less';
const FormItem = Form.Item;

const flowColumns = [{
        title: '设备编号',
        dataIndex: 'displayId',
      },{
        title: '设备地址',
        dataIndex: 'location',
      }, {
        title: '日供水量',
        dataIndex: 'dailyFlow',
      },{
        title: '日收入额',
        dataIndex: 'dailyIncome',
      },{
        title: '日供水量',
        dataIndex: 'monthFlow',
      },{
        title: '月收入额',
        dataIndex: 'monthIncome',
      }];
      
class DeviceSupply extends React.Component {
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
      wrapperCol: { span: 12 },
    };
    return (
      <div className="gutter-example button-demo">
      <BreadcrumbCustom first="设备管理" second="设备供水信息"/>
      	<div className="gutter-box">
            <Card bordered={false} title="设备供水信息查询" className={'no-padding'}>
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
                <FormItem label="设备地址">
                  {getFieldDecorator('location')(
                    <Input placeholder="请输入" />
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
	      </Card>
        </div>
      </div>  
    );
  }
}
const DevSupply = Form.create()(DeviceSupply);
export default DevSupply;

