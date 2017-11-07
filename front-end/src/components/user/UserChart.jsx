import React from 'react';
var ReactHighcharts = require('react-highcharts');

export default class UserChart extends React.Component {
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
      <ReactHighcharts config = {config}/>
    );
  }
}



const data = {"date":["2017-10-03","2017-10-04","2017-10-05","2017-10-06","2017-10-07","2017-10-08","2017-10-09","2017-10-10","2017-10-11","2017-10-12","2017-10-13","2017-10-14","2017-10-15","2017-10-16","2017-10-17","2017-10-18","2017-10-19","2017-10-20","2017-10-21","2017-10-22","2017-10-23","2017-10-24","2017-10-25","2017-10-26","2017-10-27","2017-10-28","2017-10-29","2017-10-30","2017-10-31","2017-11-01"],"recharge":[0,17,5,0,0,5,5,10,0,6,0,0,5,0,10,21,0,5,16,30,4,0,0,5,7,2,10,0,0,6],"payment":[1.1,5.2,0.9,4,2.9,0.5,0,2.9,2.1,5.2,0.9,2.5,3.7,2.8,3.2,5.9,0,2.7,5.1,11.7,3.5,3.7,1.5,2.1,8.4,1.2,2.5,1.5,2,5.4]};
const config = {
      chart: {
             type: 'line',
             zoomType: 'x',
             height:'550px',
          },
          credits: {
            enabled:false
          },
          title: {
             text: null,
             margin:30
          },
          xAxis: {
             categories: data.date
          },
          yAxis: {
             title: {
                  text: '人数/金额(元)'
              },
          },
          legend: {
            align: 'right',
              verticalAlign: 'top',
          },
          plotOptions: {
            plotOptions: {
            series: {
                allowPointSelect: true
            }
        },
             line: {
                 dataLabels: {
                     enabled: true          // 开启数据标签
                  },  
              }
          },
          series: [{
              name: '用户总数',
              data: data.recharge,
              color:'#49a9ee'
          }, {
              name: '日消费额',
              data: data.payment,
              color:'#98d87d'
          }]
        };