package com.xjtu.pojo;

import java.util.List;

public class PageBean {
    // 商品列表
    private List<Product> productList;
    // 商品总数
    private Long recordCount;
    // 总页数
    private int pageCount;
    // 当前页
    private int curPage;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public PageBean(){}

    public PageBean(List<Product> productList, Long recordCount, int pageCount, int curPage) {
        this.productList = productList;
        this.recordCount = recordCount;
        this.pageCount = pageCount;
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "productList=" + productList +
                ", recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", curPage=" + curPage +
                '}';
    }
}