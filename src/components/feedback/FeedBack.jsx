import React from 'react';
import { Form,  Input, Button,Table, Icon ,Row,Col,Popconfirm,Modal,DatePicker,message,Card } from 'antd';
import styles from '../query/TableList.less';
import BreadcrumbCustom from '../BreadcrumbCustom';
const FormItem = Form.Item;
const RangePicker = DatePicker.RangePicker;


class FeedBack extends React.Component {
  state = {
    filterDropdownVisible: false,
    data:[],
    searchText: '',
    filtered: false,
    selectedRowKeys: [], 
    visible: false,
    message:''
  };
  //弹出反馈信息层
  showModal = (mes) => {
    this.setState({
      visible: true,
      message:mes
    });
  }
  handleCancel = (e) => {
    console.log(e);
    this.setState({
      visible: false,
    });
  }

  onInputChange = (e) => {
    this.setState({ searchText: e.target.value });
  }
  onSelectChange = (selectedRowKeys) => {
    console.log('selectedRowKeys changed: ', selectedRowKeys);
    this.setState({ selectedRowKeys });
  }
  onUpDate =(key) => {
    const token = window.localStorage["token"];
    const _this = this;
    // $.ajax({
    //     url:'/feedback/updateStatus',
    //     dataType:'json',
    //     type:'POST',
    //     headers: {
    //       'Authorization': token,
    //     },
    //     data:{id:key},
    //     success:function(data){
    //       if(data.updateResult===1){
    //           message.success('更新成功');
    //           let data = [..._this.state.data];
    //            data.forEach(function(item){
    //             if(item.key===key){
    //               item.status=66
    //               item.gmtSolve = new Date().Format("yyyy-MM-dd hh:mm:ss.0");
    //             }
    //           });
    //           _this.setState({ data: data});
    //       }else{
    //         message.error('更新失败');
    //       }
    //     },
    //     error:function(xhr,status,err){
    //       console.error(this.props.url,status,err.toString());
    //     }.bind(this)
    //   });
  }
  onDelete = (key) => {
    const _this = this;
    const token = window.localStorage["token"];
    // $.ajax({
    //     url:'/feedback/delete/id',
    //     dataType:'json',
    //     type:'POST',
    //     headers: {
    //       'Authorization': token,
    //     },
    //     data:{id:key},
    //     success:function(data){
    //       if(data.deleteResult===1){
    //            message.success('删除成功');
    //            _this.setState({ data: data.filter(item => item.key !== key) });
    //       }else{
    //         message.error('删除失败,请确认是否解决问题');
    //       }
    //     },
    //     error:function(xhr,status,err){
    //       console.error(this.props.url,status,err.toString());
    //     }.bind(this)
    //   });
  }
  onSearch = () => {
    const { searchText } = this.state;
    const reg = new RegExp(searchText, 'gi');
    this.setState({
      filterDropdownVisible: false,
      filtered: !!searchText,
      data: this.state.data.map((record) => {
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
    const { selectedRowKeys } = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectChange,
      selections: [{
        key: 'all-data',
        text: '选择所有',
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
      title: '用户昵称',
      dataIndex: 'nickname',
      key: 'nickname',
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
        title: 'email',
        dataIndex: 'email',
        key: 'email',
      }, {
        title: '反馈内容',
        dataIndex: 'feedback',
        key: 'feedback',
        render:(text) => {
          return <a onClick={ () => this.showModal(text)}>查看详情</a>
        }
      },{
        title: '反馈时间',
        dataIndex: 'gmtCreate',
        key: 'gmtCreate',
      },{
        title: '结束时间',
        dataIndex: 'gmtSolve',
        key: 'gmtSolve',
      },{
        title: '反馈状态',
        dataIndex: 'status',
        key: 'status',
        render: (text) => {
        if (text === 66) {
          return <div><span style={{color:"#87D068",fontSize: 15,paddingRight: '10px'}}>●</span>已解决</div>;
        }else if (text === 23) {
          return <div><span style={{color:"#CCC",fontSize: 15,paddingRight: '10px'}}>●</span>未解决</div>;
        } 
      },
      },{
      title: '操作',
      dataIndex: 'operation',
      render: (text, record) => {
        return (
        <span>   
            <Popconfirm title="确定要删除?" onConfirm={() => this.onDelete(record.key)}>
              <a href="">删除 </a>
            </Popconfirm><span>&nbsp;&nbsp;</span>
            <Popconfirm title="确定该反馈已解决?" onConfirm={() => this.onUpDate(record.key)}>
              <a href="">更新状态</a>
            </Popconfirm>
          </span> 
        );
      },
    }];

    return (
    <div className="gutter-example button-demo">
      <BreadcrumbCustom first="反馈管理" />
      <Row>
          <Col className="gutter-row" md={24}>
            <div className="gutter-box">
              <Card bordered={false} title="管理员账号管理" className={'no-padding'}>
                <Form onSubmit={this.handleSearch} layout="inline">
                  <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
                    <Col md={7} sm={24} offset={2}>
                      <FormItem label="用户手机号">
                        {getFieldDecorator('phone')(
                         <Input placeholder="请输入" />
                        )}
                      </FormItem>
                    </Col>
                    <Col md={7} sm={24}>
                      <FormItem {...formItemLayout} label={`反馈时间`} >
                      {getFieldDecorator(`gmtCreate`)(
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
              <Table columns={columns} rowSelection={rowSelection} dataSource={this.state.data} />
              </Card>
            </div>
          </Col>                     
        </Row>
        <Modal
          title="反馈意见"
          visible={this.state.visible}
          footer={null}
          onCancel={this.handleCancel}
        >
          <p>{this.state.message}</p>
        </Modal>
      </div>
    );
  }
}

const WrappedApp = Form.create()(FeedBack);
export default WrappedApp;