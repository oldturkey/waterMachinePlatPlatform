import React from 'react';
import { Tabs } from 'antd';
import AlertTable from './alertTable';
import UseWaterTable from './UseWaterTable';
import AuthWidget from '@/components/widget/AuthWidget';
const TabPane = Tabs.TabPane;



const HomeTable = () => (
      <AuthWidget
          	children={auth => (
              <div>       
                {
                   auth.roleType &&
                     <div> 
                        {((auth.roleType===1) &&
                          <Tabs defaultActiveKey="1" onChange={this.callback} style={{padding: '0px 32px'}}>
                    		    <TabPane tab="报警记录" key="1"><AlertTable /></TabPane>
                    		    <TabPane tab="供水记录" key="2"><UseWaterTable /></TabPane>
                    	   </Tabs>
                        ) ||
    											<Tabs defaultActiveKey="1" onChange={this.callback} style={{padding: '0px 32px'}}>										
    												<TabPane tab="供水记录" key="2"><UseWaterTable /></TabPane>
    											</Tabs>
                        }
                    </div>
                }
              </div>        
             )}
      />
);

export default HomeTable;



