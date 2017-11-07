/**
 * Created by hao.cheng on 2017/5/3.
 */
import React from 'react';
import { Row, Col, Card, Icon } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import UserChart from './UserChart';


class UserStatistics extends React.Component {
    render() {
        return (
            <div className="gutter-example button-demo">
                <BreadcrumbCustom first="用户管理" second="用户统计"/>
                <Row gutter={30}>
                    <Col className="gutter-row" md={6}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="user" className="text-2x text-info"/>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">用户总数</div>
                                        <h2>301</h2>
                                    </div>
                                </div>
                            </Card>
                        </div>   
                    </Col>
                    <Col className="gutter-row" md={6}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="area-chart" className="text-2x text-success"/>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当日/当月充值金额</div>
                                        <h2>¥ 301/500</h2>
                                    </div>                                   
                                </div>
                            </Card>
                        </div>   
                    </Col>
                    <Col className="gutter-row" md={6}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="pay-circle" className="text-2x text-danger"/>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当日/当月消费金额</div>
                                        <h2>¥ 301/2000</h2>
                                    </div>   
                                </div>
                            </Card>
                        </div>   
                    </Col>
                    <Col className="gutter-row" md={6}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="pay-circle-o" className="text-2x text-danger"/>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当日当月/赠送金额</div>
                                        <h2>¥ 301/2000</h2>
                                    </div>   
                                </div>
                            </Card>
                        </div>   
                    </Col>
                    <Col className="gutter-row" md={24}>
                        <div className="gutter-box">
                            <Card bordered={false} title="设备使用趋势（30天）" className={'no-padding'}>
                                <UserChart />
                            </Card>
                        </div>
                    </Col>
                      
                </Row>
            </div>
        )
    }
}

export default UserStatistics;