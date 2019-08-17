package com.xjtu.dao;

import com.xjtu.pojo.PageBean;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface ProductDao {
    PageBean queryProduct(SolrQuery solrQuery) throws IOException, SolrServerException;
}
