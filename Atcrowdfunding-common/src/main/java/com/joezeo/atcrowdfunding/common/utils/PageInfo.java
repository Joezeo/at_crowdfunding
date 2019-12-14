package com.joezeo.atcrowdfunding.common.utils;

import java.util.List;

public class PageInfo {
    private Integer pageSize; // 每页显示数据条数
    private Integer pageNum;    // 当前页数
    private Integer startIndex; // 开始下标，sql查询用参数
    private Integer pageTotal;  // 总页数
    private List<?> datas;      // 查询到的数据

    public PageInfo(Integer pageSize, Integer pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.startIndex = (pageNum-1)*pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        // 在修改pageSize时startIndex同时也要进行修改
        this.startIndex = (pageNum-1)*pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        // 在修改pageNum时startIndex同时也要进行修改
        this.startIndex = (pageNum-1)*pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    // 不允许直接修改startIndex
    private void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<?> getDatas() {
        return datas;
    }

    public void setDatas(List<?> datas) {
        this.datas = datas;
    }
}
