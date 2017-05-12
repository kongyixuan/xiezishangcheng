package com.shoesback.biz;

import com.shoesback.vo.PageBean;

public interface ICommentsBiz {
   //分页获取评论信息
	public PageBean FindByPage(int currentpage,int pageSize);
	//模糊搜索评论信息
	public PageBean FuzzySearchComments(String params,int currentpage,int pageSize);
	//删除单个评论信息
	public void DeleteComments(int cid);
	//批量删除评论信息
	public void BatchDeleteComments(String hql);
}
