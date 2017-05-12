package com.shoesback.vo;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PageBean {
    /**
     * 分页对象类，涉及分页的五个参数
     */
	public PageBean() {
		// TODO Auto-generated constructor stub
	}
    private int totalRows;//总行数  
	private int totalPages;//总页数
    private int currentPage;//当前第几页
    private int pageSize;//每页大小   	
	private List data=new ArrayList();
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getTotalPages() {
		return totalRows%pageSize==0 ? totalRows/pageSize : totalRows/pageSize+1;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
}
