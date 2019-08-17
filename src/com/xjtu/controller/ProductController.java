package com.xjtu.controller;

import com.xjtu.pojo.PageBean;
import com.xjtu.service.ProductService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @RequestMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "1")int page, String queryString, String catalog_name, String price , @RequestParam(defaultValue = "1") String sort) throws IOException, SolrServerException {

        PageBean pageBean = productService.queryProduct(page, queryString, catalog_name, price, sort);
        model.addAttribute("result",pageBean);

        //数据回显
        model.addAttribute("queryString",queryString);
        model.addAttribute("catalog_name",catalog_name);
        model.addAttribute("price",price);
        model.addAttribute("sort",sort);
        return "product_list";
    }
}
