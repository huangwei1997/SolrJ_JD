# SolrJ_JD
solr之京东搜索案例

使用solr实现京东商城的搜索功能

将lucene.sql导入到数据库中,再通过相应的配置(创建对应域及一些其他配置,具体见学习笔记)将数据库中的数据导入到solr中,构建成功的solr索引库数据如下图所示：

![image](https://github.com/huangwei1997/SolrJ_JD/blob/master/resultImages/SolrJIndexResp.png)

之后就是正常的在框架中编写相应的代码(controller->service->dao(查询时操作的不是数据库,而是连接solr查询索引)),运行效果如下图所示，可以进行条件查询,
并将查询的关键字进行高亮显示,运行的效果图如下图所示:

![image](https://github.com/huangwei1997/SolrJ_JD/blob/master/resultImages/JDSearch.png)
