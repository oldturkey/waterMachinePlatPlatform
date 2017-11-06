import React from 'react';
import { Form, Input, Button,InputNumber,Table, Icon ,Row,Col,message,Card } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
const FormItem = Form.Item;
let dataX = [];
let dataY = [];

class UserInfo extends React.Component {
  state = {
    data:[],
    filterDropdownVisible: false,
    filtered: false,
    searchText: '', 
    selectedRowKeys: [], 
    loading: false,
  };
  lodaDataFromServer=()=>{
    const token = window.localStorage["token"]; 
    const _this = this;
    this.setState({ loading: true });
    // $.ajax({
    //   url:'/recharge/getAll',
    //   dataType:'json',
    //   headers: {
    //       'Authorization': token,
    //     },
    //   success:function(data){
    //     dataX = data.userInfoPOS;
    //     this.setState({
    //       data:data.userInfoPOS,
    //       loading: false,
    //     });
    //   }.bind(this),
    //   error:function(xhr,status,err){
    //     console.error(this.props.url,status,err.toString());
    //   }.bind(this)
    // });
  }
  componentDidMount(){
    this.lodaDataFromServer();
    this.timeRecharge=setInterval(this.lodaDataFromServer,120000);
  }
  componentWillUnmount(){
    clearInterval(this.timeRecharge);
  }
  getPhoneArrary = (key) => {            
    const dataAll = this.state.data;
    for(var i=0;i<dataAll.length;i++){
      if(dataAll[i].key===key){
        return dataAll[i].phone;
      }
    }
  }
  handleReset = () => {
    this.props.form.resetFields();
  }

  handleSubmit = (e) => {
    e.preventDefault();
    const _this = this;
    const rechargePerson = this.state.selectedRowKeys.map(this.getPhoneArrary);
    const token = window.localStorage["token"];
    let admin = this.props.admin;
    this.props.form.validateFields(['money'],(err, fieldsValue) => {
      // $.ajax({
      //   url:'/rechargePerson',
      //   dataType:'json',
      //   type:'POST',
      //   headers: {
      //     'Authorization': token,
      //   },
      //   data:{adminName:admin,phone:rechargePerson,money:fieldsValue['money']},
      //   success:function(data){
      //     if(data.rechargeStatus===1){
      //       message.success('充值成功');
      //       _this.lodaDataFromServer();
      //     }else{
      //       message.error('充值失败');
      //     }
      //   },
      //   error:function(xhr,status,err){
      //     console.error(this.props.url,status,err.toString());
      //   }.bind(this)
      // });
    });
  }
   callback(key) {
  console.log(key);
}
  onInputChange = (e) => {
    this.setState({ searchText: e.target.value });
  }

  onSelectChange = (selectedRowKeys) => {
    console.log('selectedRowKeys changed: ', selectedRowKeys);
    this.setState({ selectedRowKeys });
  }
  onSearch = () => {
    const { searchText } = this.state;
    const reg = new RegExp(searchText, 'gi');
    this.setState({
      filterDropdownVisible: false,
      filtered: !!searchText,
      data: dataX.map((record) => {
        const match = record.phone.match(reg);
        if (!match) {
          return null;
        }
        return {
          ...record,
          phone: (
            <span>
              {record.phone.split(reg).map((text, i) => (
                i > 0 ? [<span className="highlight">{match[0]}</span>, text] : text
              ))}
            </span>
          ),
        };
      }).filter(record => !!record),
    });
  }


  render() {
    const { getFieldDecorator } = this.props.form; 
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 16 },
    };
    const { selectedRowKeys } = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectChange,
      selections: [{
        key: 'all-data',
        text: '选择所有用户',
        onSelect: () => {
          this.setState({
            selectedRowKeys: this.state.data.map((data,i) => (data.key)),  
          });
        },
      },{
        key: 'all-close',
        text: '清空所有选择',
        onSelect: () => {
          this.setState({
            selectedRowKeys: [],  
          });
        },
      }],
      onSelection: this.onSelection,
    }; 
    const columns = [{
      title: '姓名',
      dataIndex: 'nickName',
      key: 'name',
    },{
      title: '用户性别',
      dataIndex: 'gender',
      key: 'sex',
      render: (text) => {
        if (text === "male") {
          return text="男";
        }else if (text === "female") {
          return text="女";
        }
      },
      filters: [{
        text: '男',
        value: "male",
      },{
        text: '女',
        value: "female",
      },],
      onFilter: (value, record) => record.gender === value,
    }, {
      title: '手机号码',
      dataIndex: 'phone',
      key: 'phone',
      filterDropdown: (
        <div className="custom-filter-dropdown">
          <Input
            ref={ele => this.searchInput = ele}
            placeholder="查询号码"
            value={this.state.searchText}
            onChange={this.onInputChange}
            onPressEnter={this.onSearch}
          />
          <Button type="primary" onClick={this.onSearch}>Search</Button>
        </div>
      ),
      filterIcon: <Icon type="search" style={{ color: this.state.filtered ? '#108ee9' : '#aaa' }} />,
      filterDropdownVisible: this.state.filterDropdownVisible,
      onFilterDropdownVisibleChange: (visible) => {
        this.setState({
          filterDropdownVisible: visible,
        }, () => this.searchInput.focus());
      },
    }, {
      title: '用户位置',
      dataIndex: 'city',
      key: 'city',
      filters: [{
        text: '杭州',
        value: '杭州',
      },{
        text: '南京',
        value: '南京',
      },],
      onFilter: (value, record) => record.city.indexOf(value) === 0,
    }, {
      title: '消费总额',
      dataIndex: 'consume',
      key: 'cost',
      sorter: (a, b) => a.consume - b.consume,
    }, {
      title: '充值总额',
      dataIndex: 'recharge',
      key: 'cost',
      sorter: (a, b) => a.recharge - b.recharge,
    },{
      title: '账户余额',
      dataIndex: 'remain',
      key: 'remain',
    },{
      title: '赠送余额',
      dataIndex: 'present',
      key: 'present',
    },{
      title: '注册时间',
      dataIndex: 'time',
      key: 'time',
    }];


    return (
    <div >
    <BreadcrumbCustom first="用户管理" second="用户信息及操作"/>
          <div style={{marginTop:'20px',borderBottom:'2px solid #eee'}}>
          <Card bordered={false} title="用户信息及发放奖励金" className={'no-padding'}>
          <Form
          className="ant-advanced-search-form"
          onSubmit={this.handleSearch}
          style={{padding:'30px 5px',marginBottom:20}}
           >
          <Row>
            <Col span={7} key={1} offset={2}>
              <FormItem {...formItemLayout} label='用户手机号'>
              {getFieldDecorator('displayId', {
              rules: [{ required: true, message: '请输入要查询的手机号码!' }],
            })(
              <Input placeholder="请输入设备编号" />
            )}
              </FormItem>
            </Col>
            <Col span={12} key={2}>
              <Button type="primary" htmlType="submit">搜索</Button>
              <Button  onClick={this.handleReset}>
                清空
              </Button>
            </Col>
          </Row>
        </Form>
            <Table columns={columns} rowSelection={rowSelection} dataSource={this.state.data} loading={this.state.loading}/>
            <Row style={{padding:'20px 0'}}>
              <Col span={18} offset={10}>
                <Form onSubmit={this.handleSubmit} layout='inline'>
                  <FormItem
                    label="为选中用户充值金额(元)"
                    labelCol={{ lg: 16 }}
                    wrapperCol={{ lg: 6 }}
                  >
                    {getFieldDecorator('money', {
                      rules: [{ required: true, message: '请输入充值金额!' }],
                    })(
                      <InputNumber min={0.1}  max={50}/>
                    )}
                  </FormItem>
                  <FormItem
                    wrapperCol={{ lg: 6, offset: 6 }}
                  >
                    <Button type="primary" htmlType="submit">
                      充值
                    </Button>
                  </FormItem>
                </Form>
             </Col>
            </Row>
            </Card>
          </div>
    </div>
    );
  }
}

const user = Form.create()(UserInfo);
export default user;
