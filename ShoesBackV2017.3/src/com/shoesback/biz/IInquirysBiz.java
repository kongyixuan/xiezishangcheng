package com.shoesback.biz;

import com.shoesback.po.ShoesInquirys;
import com.shoesback.vo.PageBean;

public interface IInquirysBiz {
    //分页获取回复问题信息
	public PageBean FindByPage(int currentpage,int pageSize);
	//获取回复问题单个对象
	public ShoesInquirys FindBysqid(int sqid);
	//回复问题
	public void AnswerInquirys(ShoesInquirys inquirys);
	//模糊搜索回复问题
	public PageBean FuzzySearchInquirys(String params,int currentpage,int pageSize);
}
