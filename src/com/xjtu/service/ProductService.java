package com.xjtu.service;

import com.xjtu.pojo.PageBean;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface ProductService {
    public PageBean queryProduct(int page, String queryString, String catalog_name, String price , String sort) throws IOException, SolrServerException;
}
