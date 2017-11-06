/**
 * Created by hao.cheng on 2017/5/3.
 */
import React from 'react';
import { Row, Col, Card, Icon,Select,Input,Popconfirm,Button,Table } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import '../device/EditableCell.css';
const Option = Select.Option;
const children = [];

//可编辑组件
class EditableCell extends React.Component {
  state = {
    value: this.props.value,
    editable: false,
    user:this.props.user?true:false,
  }
  handleChange = (e) => {
    this.state.user?
    this.setState({ value:e }):this.setState({ value:e.target.value });
  }
  check = () => {
    this.setState({ editable: false });
    if (this.props.onChange) {
      this.props.onChange(this.state.value);
    }
  }
  edit = () => {
    this.setState({ editable: true });
  }
  render() {
    const { value, editable ,user} = this.state;
    return (
      <div className="editable-cell">
        {
          editable ?
            user?
            <div className="editable-cell-input-wrapper">
              <Select  
                style={{ width: '100%' }}
                onChange={this.handleChange}
                onPressEnter={this.check}
                tokenSeparators={[',']}
              >
              <Option value="平台管理员">平台管理员</Option>
              <Option value="平台运营员">平台运营员</Option>
              <Option value="物业管理员">物业管理员</Option>
              <Option value="系统调试员">系统调试员</Option>
              <Option value="物业分级管理员">物业分级管理员</Option>
            </Select>
              <Icon
                type="check"
                className="editable-cell-icon-check"
                onClick={this.check}
              />
            </div>
            :
            <div className="editable-cell-input-wrapper">
              <Input
                value={value}
                onChange={this.handleChange}
                onPressEnter={this.check}
              />
              <Icon
                type="check"
                className="editable-cell-icon-check"
                onClick={this.check}
              />
            </div>
            :
            <div className="editable-cell-text-wrapper">
              {value || ' '}
              <Icon
                type="edit"
                className="editable-cell-icon"
                onClick={this.edit}
              />
            </div>
        }
      </div>
    );        
  }
}  




class AdminManage extends React.Component {
    constructor(props) {
    super(props);
    this.state={dataSource: [],
      count: 0,}
    this.columns = [{
      title: '管理员编号',
      dataIndex: 'adminId',
      width: '12%',
      sorter: (a, b) => a.adminId - b.adminId,
    },{
      title: '管理员姓名',
      dataIndex: 'account',
      width: '17%',
      render: (text, record) => {
        if(!record.operation){
          return (
            <EditableCell
              value={text}
              onChange={this.onCellChange(record.key, 'location')}
            />
          )
        }else {
          return text
        }
      },
    },{
      title: '管理设备',
      dataIndex: 'displayid',
      width: '12%',
    },{
      title: '邮箱',
      dataIndex: 'email',
      width: '16%',
      render: (text, record) => {
        if(!record.operation){
          return (
            <EditableCell
              value={text}
              onChange={this.onCellChange(record.key, 'email')}
            />
          )
        }else {
          return text
        }
      },
    },{
      title: '管理员类型',
      dataIndex: 'adminType',
      width: '16%',
      render: (text, record) => {
        if(!record.operation){
          return (
            <EditableCell
              user={1}
              value={text}
              onChange={this.onCellChange(record.key, 'adminType')}
            />
          )
        }else {
          return text
        }
      },
    },{
      title: '初始密码',
      dataIndex: 'password',
      width: '16%',
    },{
      title: '操作',
      dataIndex: 'operation',
      render: (text, record) => {
        if(text === 1){
          return (
            <span>   
                <Popconfirm title="确定要删除该管理员?" onConfirm={() => this.onDelete(record.key)}>
                  <a href="">删除 </a>
                </Popconfirm>
              </span> 
          );
        }else{
          return (
            <span>   
                <Popconfirm title="确定要更新该管理员信息?" onConfirm={() => this.onUpDate(record.key)}>
                  <a href="">上传更新管理员信息</a>
                </Popconfirm>
              </span> 
            )
        }
      },
    }];
  }
   
    lodaDataFromServer=()=>{
    const token = window.localStorage["token"]; 
    // $.ajax({
    //   url:'/device/get',
    //   dataType:'json',
    //   headers: {
    //       'Authorization': token,
    //     },
    //   success:function(data){
    //     this.setState({
    //       dataSource:data.terminalArray,
    //       count:data.terminalArray.length+1
    //     });
    //   }.bind(this),
    //   error:function(xhr,status,err){
    //     console.error(this.props.url,status,err.toString());
    //   }.bind(this)
    // });
  }

  
  onCellChange = (key, dataIndex) => {
    return (value) => {
      const dataSource = [...this.state.dataSource];
      const target = dataSource.find(item => item.key === key);
      if (target) {
        target[dataIndex] = value;
        this.setState({ dataSource });
      }
    };
  }

  //删除设备
  onDelete = (key) => {   
    const token = window.localStorage["token"]; 
    const dataSource = [...this.state.dataSource];
    const _this = this;
    const deleteItem = dataSource.filter(item => item.key === key)[0];
  //   $.ajax({
  //       url:'/device/delete',
  //       dataType:'json',
  //       type:'POST',
  //       headers: {
  //         'Authorization': token,
  //       },
  //       data:{displayId:deleteItem.displayId},
  //       success:function(data){
  //         if(data.status===200){
  //            _this.setState({ dataSource: dataSource.filter(item => item.key !== key) });
  //         }else if(data.status===400){
  //           message.error("授权失败");
  //         }else if(data.status===401){
  //           message.error("displayId不存在");
  //         }else if(data.status===500){
  //           message.error("删除设备失败");
  //         }  
  //       },
  //       error:function(xhr,status,err){
  //         console.error(this.props.url,status,err.toString());
  //       }.bind(this)
  //     });
   }
  //添加设备
  onUpDate = (key) => {
    const _this = this;
    const token = window.localStorage["token"]; 
    const dataSource = [...this.state.dataSource];
    const upDateItem = dataSource.filter(item => item.key === key)[0];
    // $.ajax({
    //     url:'/device/add',
    //     dataType:'json',
    //     type:'POST',
    //     headers: {
    //       'Authorization': token,
    //     },
    //     data:{imei:upDateItem.imei,address:upDateItem.location,sim:upDateItem.simId},
    //     success:function(data){
    //       if(data.status===200){
    //         message.success("添加成功");
    //         _this.lodaDataFromServer();
    //       }else if(data.status===400){
    //         message.error("授权失败");
    //       }else if(data.status===501){
    //         message.error("新建设备失败，SIM卡号或者IMEI长度不足15");
    //       }else if(data.status===502){
    //         message.error("重复添加");
    //       } 
    //     },
    //     error:function(xhr,status,err){
    //       console.error(this.props.url,status,err.toString());
    //     }.bind(this)
    //   });
  }
  handleAdd = () => {
    const { count, dataSource } = this.state;
    const newData = {
      key: count,
      account: '编辑',
      email: '编辑',
      adminType: '编辑',
      password:Math.floor(Math.random()*10)+''+Math.floor(Math.random()*10)+Math.floor(Math.random()*10)+Math.floor(Math.random()*10)+Math.floor(Math.random()*10),
    };
    this.setState({
      dataSource: [...dataSource, newData],
      count: count + 1,
    });
  }
  
    render() {
        return (
            <div className="gutter-example button-demo">
                <BreadcrumbCustom first="账号管理" />
                <Row>
                    <Col className="gutter-row" md={24}>
                        <div className="gutter-box">
                            <Card bordered={false} title="管理员账号管理" className={'no-padding'}>
                                <div style={{width:'90%' ,margin:'10px auto'}}>
                                    <Button className="editable-add-btn" style={{float:'right',backgroundColor: '#49a9ee',color:'#fff'}} onClick={this.lodaDataFromServer}>刷新状态</Button>
                                    <Button className="editable-add-btn"  onClick={this.handleAdd}>添加管理员</Button>
                                    <Table bordered dataSource={this.state.dataSource} columns={this.columns} />
                                </div>  
                            </Card>
                        </div>
                    </Col>
                      
                </Row>
            </div>
        )
    }
}
export default AdminManage;