# multi-modules-example
用maven构建的一个多模块java工程，用作以后搭建项目的标准模板。

目前包含模块和简介：

* qct-common: 一些公共的工具
* qct-dao：DAO层，hibernate + proxool连接池
* qct-service：业务逻辑层
* qct-web：web页面，这个可有可无，可以是动态页面，也可以是静态。我主要关注后台架构
* qct-webservice：RESTful接口，使用CXF。

后续可能会往里面添加自己比较感兴趣的内容，比如redis。
