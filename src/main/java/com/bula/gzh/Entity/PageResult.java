package com.bula.gzh.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

@ApiModel("返回的数据说明")
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("总记录数")
    private int totalCount;
    @ApiModelProperty("每页记录数")
    private int pageSize;
    @ApiModelProperty("总页数")
    private int totalPage;
    @ApiModelProperty("当前页数")
    private int currPage;
    @ApiModelProperty("查询到的数据")
    private List<T> list;

    public PageResult() {
    }

    public PageResult(int totalCount, int pageSize, int totalPage, int currPage, List<T> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.currPage = currPage;
        this.list = list;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return this.currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

