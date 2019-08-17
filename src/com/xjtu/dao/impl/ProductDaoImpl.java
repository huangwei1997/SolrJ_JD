package com.xjtu.dao.impl;

import com.xjtu.dao.ProductDao;
import com.xjtu.pojo.PageBean;
import com.xjtu.pojo.Product;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class ProductDaoImpl implements ProductDao {
    public PageBean queryProduct(SolrQuery solrQuery) throws IOException, SolrServerException {
        final String solrURL = "http://localhost:8983/solr/";
        final String solrCore = "meta_db";
        //1.获取solr的服务器用于连接solr项目 solr8.0移除了solrServer,使用solrClient代替
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrURL).withConnectionTimeout(10000).withSocketTimeout(60000).build();
        //执行查询
        QueryResponse response = solrClient.query(solrCore, solrQuery);
        //获取文档列表
        SolrDocumentList solrDocuments = response.getResults();

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        PageBean pageBean = new PageBean();
        pageBean.setRecordCount(solrDocuments.getNumFound());
        List<Product> list = new ArrayList<>();
        for(SolrDocument document : solrDocuments){
            Product product = new Product();
            product.setPid((String) document.get("id"));
            product.setDescription(null);
            //获取高亮显示的商品名
            Map<String, List<String>> map = highlighting.get((String) document.get("id"));
            List<String> list1 = map.get("product_name");
            //保证显示的商品名不为空
            String product_name = "";
            if(list1!=null && list1.size()>0){
                product_name = list1.get(0);
            }else {
                product_name = (String) document.get("product_name");
            }

            product.setName(product_name);
            product.setPicture((String) document.get("product_picture"));
            product.setPrice((Float) document.get("product_price"));
            list.add(product);
        }
        pageBean.setProductList(list);
        return pageBean;
    }
}
