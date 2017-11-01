import React from 'react';
import { Tabs } from 'antd';
import AlertTable from './alertTable';
import UseWaterTable from './UseWaterTable';
const TabPane = Tabs.TabPane;



const HomeTable = () => (
    <Tabs defaultActiveKey="1" onChange={this.callback} style={{padding: '0px 32px'}}>
          <TabPane tab="报警记录" key="1"><AlertTable /></TabPane>
          <TabPane tab="供水记录" key="2"><UseWaterTable /></TabPane>
      </Tabs>
);

export default HomeTable;




