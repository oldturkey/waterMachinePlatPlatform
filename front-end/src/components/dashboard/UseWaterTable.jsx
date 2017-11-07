import React from 'react';
import { Table } from 'antd';


export default class UseWaterTable extends React.Component {
  constructor(props) {
    super(props);
    this.state={date: data}
  }

  componentDidMount() {
    
  }

  componentWillUnmount() {
    
  }
 // constructor(props) {
 //    super(props);
 //    this.state={
 //      data: [],
 //      loading: false,
 //    }
 //  }

 //  componentDidMount() {
 //   this.start();
 //    }
 //    start = () => {
 //        this.setState({ loading: true });
 //        homeSupply().then(res => {
 //          let i = 0;
 //            this.setState({
 //                data: [...res.offlineTerminal.map(val => {
 //                    val.key = ++i;
 //                    return val;
 //                })],
 //                loading: false
 //            });
 //        });
 //    };

  render() {
    return (
      <Table columns={columns} dataSource={data} bordered/>
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
}, {
    title: '取水金额',
    dataIndex: 'payment',
    key: 'payment',
}, {
    title: '取水量',
    dataIndex: 'flow',
    key: 'flow',
}, {
    title: '取水时间',
    dataIndex: 'time',
    key: 'time',
}];

const data = [{
    key: '1',
    displayId: '001',
    location: '西湖',
    payment: '1',
}, {
    key: '2',
    displayId: '002',
    location: '之江',
    payment: '2',
}, {
    key: '3',
    displayId: '003',
    location: '钱塘江',
    payment: '3',
}];

