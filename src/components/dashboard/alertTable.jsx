import React from 'react';
import { Table } from 'antd';
import { getAlertTable } from '../../axios';

export default class alertTable extends React.Component {
  constructor(props) {
    super(props);
    this.state={
      data: [],
      loading: false,
    }
  }

  componentDidMount() {
   this.start();
    }
    start = () => {
        this.setState({ loading: true });
        getAlertTable().then(res => {
          let i = 0;
            this.setState({
                data: [...res.offlineTerminal.map(val => {
                    val.key = ++i;
                    return val;
                })],
                loading: false
            });
        });
    };
  render() {
    return (
       <Table columns={columns} dataSource={this.state.data} loading={this.state.loading} bordered/>
    );
  }
}

const columns = [{
    title: '设备编号',
    dataIndex: 'displayId',
    key: 'displayId',
}, {
    title: '设备位置',
    dataIndex: 'location',
    key: 'location',
},{
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

