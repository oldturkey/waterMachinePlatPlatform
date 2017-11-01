import React from 'react';
import { Tabs } from 'antd';
import AlertTable from './alertTable';
import UseWaterTable from './UseWaterTable';
const TabPane = Tabs.TabPane;


export default class HomeTable extends React.Component {
  componentDidMount() {
    
  }

  componentWillUnmount() {
    
  }


  render() {
    return (
       <Tabs defaultActiveKey="1" onChange={this.callback}>
          <TabPane tab="报警记录" key="1"><AlertTable /></TabPane>
          <TabPane tab="供水记录" key="2"><UseWaterTable /></TabPane>
      </Tabs>
    );
  }
}


