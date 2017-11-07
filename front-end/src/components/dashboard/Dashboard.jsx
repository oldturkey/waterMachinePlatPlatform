/**
 * Created by hao.cheng on 2017/5/3.
 */
import React from 'react';
import { Row, Col, Card, Icon } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import HomeTable from './homeTable';


class Dashboard extends React.Component {
    constructor(props) {
    super(props);
    this.state={date: []}
  }

  componentDidMount() {
    
  }

  componentWillUnmount() {
    
  }
    render() {
        return (
            <div className="gutter-example button-demo">
                <BreadcrumbCustom />
                <Row gutter={30}>
                    <Col className="gutter-row" md={12}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="user" className="text-2x text-danger"/>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">用户数</div>
                                        <h2>301</h2>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当日/当月消费</div>
                                        <h2>¥ 126,560</h2>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当日/当月充值</div>
                                        <h2>¥ 301</h2>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当日/当月赠送</div>
                                        <h2>¥ 301</h2>
                                    </div>
                                </div>
                            </Card>
                        </div>   
                    </Col>
                    <Col className="gutter-row" md={12}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="database" className="text-2x text-info" />
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">设备数</div>
                                        <h2>802</h2>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当日供水</div>
                                        <h2>301</h2>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当月供水</div>
                                        <h2>301</h2>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当日/当月收入</div>
                                        <h2>301</h2>
                                    </div>
                                </div>
                            </Card>
                        </div>
                    </Col>
                    <Col className="gutter-row" md={24}>
                        <div className="gutter-box">
                            <Card bordered={false} title="实时信息（1小时内）" className={'no-padding'}>
                                <HomeTable />
                            </Card>
                        </div>
                    </Col>
                      
                </Row>
            </div>
        )
    }
}

export default Dashboard;