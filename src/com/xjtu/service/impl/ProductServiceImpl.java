package com.xjtu.service.impl;

import com.xjtu.dao.ProductDao;
import com.xjtu.pojo.PageBean;
import com.xjtu.service.ProductService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class ProductServiceImpl implements ProductService {
    private static int rows= 60;
    @Autowired
    private ProductDao productDao;
    @Override
    public PageBean queryProduct(int page, String queryString, String catalog_name, String price, String sort) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        //设置查询条件的相关信息

        //设置主查询条件
        if(queryString!=null && !queryString.equals("") ){
            query.set("q",queryString);
        }else {
            query.set("q","*:*");
        }
        //设置过滤查询条件
        if(catalog_name!=null&&!catalog_name.equals("")){
            query.addFilterQuery("product_catalog_name:"+ catalog_name);
        }
        if(price!=null && !price.equals("")){
            String[] split = price.split("-");
            query.addFilterQuery("product_price:["+ split[0]+" TO "+ split[1] + "]");
        }

        //设置起始行数及每页显示的记录条数
        query.setStart((page-1)*rows);
        query.setRows(rows);
        //设置默认查询域
        query.set("df","product_name");
        //设置高亮的相关信息
        query.setHighlight(true);
        query.setHighlightSimplePre("<span style=\"color:red\">");
        query.setHighlightSimplePost("</span>");
        query.addHighlightField("product_name");
        //设置排序
        if(sort.equals("1")){
            query.setSort("product_price", SolrQuery.ORDER.desc);
        }else {
            query.setSort("product_price", SolrQuery.ORDER.asc);
        }
        PageBean pageBean = productDao.queryProduct(query);

        pageBean.setCurPage(page);
        int pageCount = (int) (pageBean.getRecordCount()/rows);
        if(pageBean.getRecordCount()%rows>0){
            pageCount++;
        }
        pageBean.setPageCount(pageCount);
        return  pageBean;
    }
}
