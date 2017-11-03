/**
 * Created by hao.cheng on 2017/5/3.
 */
import React from 'react';
import { Row, Col, Card, Icon } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import DeviceChart from './DeviceChart';


class DeviceStatistics extends React.Component {
    render() {
        return (
            <div className="gutter-example button-demo">
                <BreadcrumbCustom first="设备管理" second="设备统计"/>
                <Row gutter={30}>
                    <Col className="gutter-row" md={8}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="database" className="text-2x text-info"/>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">设备总数</div>
                                        <h2>301</h2>
                                    </div>
                                </div>
                            </Card>
                        </div>   
                    </Col>
                    <Col className="gutter-row" md={8}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="area-chart" className="text-2x text-success"/>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当日/历史供水总量</div>
                                        <h2>301/500</h2>
                                    </div>                                   
                                </div>
                            </Card>
                        </div>   
                    </Col>
                    <Col className="gutter-row" md={8}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="pay-circle" className="text-2x text-danger"/>
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">当日/历史收入额</div>
                                        <h2>¥ 301/2000</h2>
                                    </div>   
                                </div>
                            </Card>
                        </div>   
                    </Col>
                    <Col className="gutter-row" md={24}>
                        <div className="gutter-box">
                            <Card bordered={false} title="设备使用趋势（30天）" className={'no-padding'}>
                                <DeviceChart />
                            </Card>
                        </div>
                    </Col>                      
                </Row>
            </div>
        )
    }
}

export default DeviceStatistics;