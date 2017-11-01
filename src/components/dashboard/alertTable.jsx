import React from 'react';
import { Table } from 'antd';


export default class alertTable extends React.Component {
  constructor(props) {
    super(props);
    this.state={date: data}
  }

  componentDidMount() {
    
  }

  componentWillUnmount() {
    
  }


  render() {
    return (
       <Table columns={columns} dataSource={data}/>
    );
  }
}

const columns = [{
    title: '设备编号',
    dataIndex: 'displayId',
    key: 'displayId',
}, {
    title: 'Sim卡号',
    dataIndex: 'simId',
    key: 'simId',
}, {
    title: 'Imei号',
    dataIndex: 'imei',
    key: 'imei',
}, {
    title: '掉线时间',
    dataIndex: 'lastConnectTime',
    key: 'lastConnectTime',
}, {
    title: '掉线时长',
    dataIndex: 'offlineTime',
    key: 'offlineTime',
}];

const data = [{
    key: '1',
    displayId: '001',
    simId: 32,
    imei: '111',
}, {
    key: '2',
    displayId: '002',
    simId: 42,
    imei: '222',
}, {
    key: '3',
    displayId: '003',
    simId: 32,
    imei: '333',
}];

