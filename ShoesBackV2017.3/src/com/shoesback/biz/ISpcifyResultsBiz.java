package com.shoesback.biz;

import com.shoesback.po.SpcifyResults;
import com.shoesback.vo.PageBean;

public interface ISpcifyResultsBiz {
    //分页获取定制鞋订单信息
	public PageBean FindByPage(int currentpage,int pageSize);
	//更新定制订单状态
	public void UpdateSpcifyResults(int sprid,int spstate);
	//模糊搜索定制订单
	public PageBean FuzzySearchSpcifyResults(String params,int currentpage,int pageSize);
	//获取单个定制订单对象
	public SpcifyResults FindBysprid(int sprid);
}
