import React from 'react';
import { Row, Col, Card, Icon,Select,Input,Popconfirm,Button,Table } from 'antd';
//可编辑组件  可完成select选择和文本输入两种场景使用
class EditableCell extends React.Component {
  state = {
    value: this.props.value,
    editable: false,
    user:this.props.user?true:false,
  }
  handleChange = (e) => {
    this.state.user?
    this.setState({ value:e.join(",") }):this.setState({ value:e.target.value });
  }
  check = () => {
    this.setState({ editable: false });
    if (this.props.onChange) {
      this.props.onChange(this.state.value);
    }
  }
  edit = () => {
    this.setState({ editable: true });
  }
  render() {
    const { value, editable ,user} = this.state;
    return (
      <div className="editable-cell">
        {
          editable ?
            user?
            <div className="editable-cell-input-wrapper">
              <Select  
                mode="tags"
                style={{ width: '100%' }}
                onChange={this.handleChange}
                onPressEnter={this.check}
                tokenSeparators={[',']}
              >
              {children}
            </Select>
              <Icon
                type="check"
                className="editable-cell-icon-check"
                onClick={this.check}
              />
            </div>
            :
            <div className="editable-cell-input-wrapper">
              <Input
                value={value}
                onChange={this.handleChange}
                onPressEnter={this.check}
              />
              <Icon
                type="check"
                className="editable-cell-icon-check"
                onClick={this.check}
              />
            </div>
            :
            <div className="editable-cell-text-wrapper">
              {value || ' '}
              <Icon
                type="edit"
                className="editable-cell-icon"
                onClick={this.edit}
              />
            </div>
        }
      </div>
    );        
  }
}  