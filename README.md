# react-admin
react-admin system solution

### 最下方增加版本更新日志😁

### 前言
> 网上react后台管理开源免费的完整版项目比较少，所以利用空余时间集成了一个版本出来，已放到GitHub
  启动和打包的时间都稍长，请耐心等待两分钟



### 依赖模块
<span style="color: rgb(184,49,47);">项目是用create-react-app创建的，主要还是列出新加的功能依赖包</span>

<span style="color: rgb(184,49,47);">点击名称可跳转相关网站😄😄</span>

- [react@15.5.0](https://facebook.github.io/react/)
- [react-router@3.0.2](https://react-guide.github.io/react-router-cn/)(<span style="color: rgb(243,121,52);">react路由,4.x的差异还是比较大，暂时还是3.x的版本</span>)
- [antd@2.9.3](https://ant.design/index-cn)(<span style="color: rgb(243,121,52);">蚂蚁金服开源的react ui组件框架</span>)
- [axios@0.16.1](https://github.com/mzabriskie/axios)(<span style="color: rgb(243,121,52);">http请求模块，可用于前端任何场景，很强大👍</span>)
- [echarts-for-react@1.2.0](https://github.com/hustcc/echarts-for-react)(<span style="color: rgb(243,121,52);">可视化图表，别人基于react对echarts的封装，足够用了</span>)
- [recharts@0.22.3](http://recharts.org/#/zh-CN/)(<span style="color: rgb(243,121,52);">另一个基于react封装的图表，个人觉得是没有echarts好用</span>)
- [nprogress@0.2.0](https://github.com/rstacruz/nprogress)(<span style="color: rgb(243,121,52);">顶部加载条，蛮好用👍</span>)
- [react-draft-wysiwyg@1.9.6](https://github.com/jpuri/react-draft-wysiwyg)(<span style="color: rgb(243,121,52);">别人基于react的富文本封装，如果找到其他更好的可以替换</span>)
- [react-draggable@2.2.4](https://github.com/mzabriskie/react-draggable)(<span style="color: rgb(243,121,52);">拖拽模块，找了个简单版的</span>)
- [screenfull@3.2.0](https://github.com/sindresorhus/screenfull.js/)(<span style="color: rgb(243,121,52);">全屏插件</span>)
- [photoswipe@4.1.2](https://github.com/dimsemenov/photoswipe)(<span style="color: rgb(243,121,52);">图片弹层查看插件，不依赖jQuery，还是蛮好用👍</span>)
- [animate.css@3.5.1](http://daneden.me/animate)(<span style="color: rgb(243,121,52);">css动画库</span>)
- 其他小细节省略

### 功能模块
<span style="color: rgb(184,49,47);">备注：项目只引入了ant-design的部分组件，其他的组件antd官网有源码，可以直接复制到项目中使用，后续有时间补上全部组件。</span>
<span style="color: rgb(184,49,47);">项目使用了antd的自定义主题功能-->黑色，若想替换其他颜色，具体操作请查看antd官网</span>
<!--more-->

- 首页
    - 完整布局
    - 换肤(全局功能，暂时只实现了顶部导航的换肤，后续加上其他模块)
- 导航菜单
    - 顶部导航(菜单伸缩，全屏功能)
    - 左边菜单(增加滚动条以及适配路由的active操作)
- UI模块
    - 按钮(antd组件)
    - 图标(antd组件并增加彩色表情符)
    - 加载中(antd组件并增加顶部加载条)
    - 通知提醒框(antd组件)
    - 标签页(antd组件)
    - 轮播图(ant动效组件)
    - 富文本
    - 拖拽
    - 画廊
- 动画
    - 基础动画(animate.css所有动画)
    - 动画案例
- 表格
    - 基础表格(antd组件)
    - 高级表格(antd组件)
    - 异步表格(数据来自掘金酱的接口)
- 表单
    - 基础表单(antd组件)
- 图表
    - echarts图表
    - recharts图表
- 页面
    - 登录页面(包括GitHub第三方登录)
    - 404页面

### 功能截图
#### 首页

### 代码目录
```js
+-- build/                                  ---打包的文件目录
+-- config/                                 ---npm run eject 后的配置文件目录
+-- node_modules/                           ---npm下载文件目录
+-- public/                                 
|   --- index.html							---首页入口html文件
|   --- npm.json							---echarts测试数据
|   --- weibo.json							---echarts测试数据
+-- src/                                    ---核心代码目录
|   +-- axios                               ---http请求存放目录
|   |    --- index.js
|   +-- components                          ---各式各样的组件存放目录
|   |    +-- animation                      ---动画组件
|   |    |    --- ...   
|   |    +-- charts                         ---图表组件
|   |    |    --- ...   
|   |    +-- dashboard                      ---首页组件
|   |    |    --- ...   
|   |    +-- forms                          ---表单组件
|   |    |    --- ...   
|   |    +-- pages                          ---页面组件
|   |    |    --- ...   
|   |    +-- tables                         ---表格组件
|   |    |    --- ...   
|   |    +-- ui                             ---ui组件
|   |    |    --- ...   
|   |    --- BreadcrumbCustom.jsx           ---面包屑组件
|   |    --- HeaderCustom.jsx               ---顶部导航组件
|   |    --- Page.jsx                       ---页面容器
|   |    --- SiderCustom.jsx                ---左边菜单组件
|   +-- style                               ---项目的样式存放目录，主要采用less编写
|   +-- utils                               ---工具文件存放目录
|   --- App.js                              ---组件入口文件
|   --- index.js                            ---项目的整体js入口文件，包括路由配置等
--- .env                                    ---启动项目自定义端口配置文件
--- .eslintrc                               ---自定义eslint配置文件，包括增加的react jsx语法限制
--- package.json                                    
```
### 安装运行
##### 1.下载或克隆项目源码
##### 2.npm安装相关包文件(国内建议增加淘宝镜像源，不然很慢，你懂的😁)
```js
npm i
```
##### 3.启动项目
```js
npm start
```
##### 4.打包项目
```js
npm run build
```

### 更新日志

#### 2017-07-08
- 依赖包版本升级
    - react@15.6.1
    - antd@2.11.2
    - webpack@2.6.1
    - 等等
#### 2017-08-01
- 引入redux系列
    - redux@3.7.2
    - redux-thunk@2.2.0
    - react-redux@5.0.5
- 增加权限管理模块
    - 使用easy-mock模拟数据模拟登录接口
    - 使用redux系列将登录用户数据传递给权限组件
    - 权限组件采用Render Callback的方式传递权限给需要受控制的组件（具体做法请查看源代码。）
    - 用户状态保存在localStorage中
    - 具体做法请运行项目查看
    - PS：以上管理权限只是一种方式，但这绝对不是唯一的方式，也不是最好的方式。如果你有更好的方式，不妨加上面的群和大家一起分享下。😄😄
- 增加路径别名
    - 使用@别名处理引入组件相对路径过长问题。
    - 缺点：编辑器不能使用快捷提示和快捷跳转到相应的文件
#### 2017-08-13
- 权限管理模块增加页面跳转权限验证
    - 点击权限管理的路由拦截，若没有访问权限则会跳转到404页面。
    - 大致实现方式(非常简单)：通过向自定义router组件传入store，登录之后可获取到redux中的权限state数据，并通过判断是否包含权限进行跳转。ps: 该demo的效果是管理员登录之后才能跳转到路由拦截页面。具体操作请拉取代码尝试。
#### 2017-08-26
- 增加响应式布局
    - 替换antd Col 组件的响应式栅格为md(具体参数用法请查看antd官方文档)
    - 初始化页面是获取当前浏览器宽度设置菜单显示类型
    - 监听window的onresize函数，设置菜单显示类型。PS：浏览器宽度存入redux中，方便组件之间传递。
![截图](https://raw.githubusercontent.com/yezihaohao/react-admin/master/src/style/imgs/mobile.gif)
#### 2017-09-13
- 依赖包版本升级
    - antd@2.13.1(目前最新版)
    
#### 2017-10-21
- 开发环境增加react-hot-loader-保持状态刷新组件(译：实时调整组件),可参考以下相关项目
    - [react-hot-loader](https://github.com/gaearon/react-hot-loader)

### 结尾
该项目会不定时更新