package com.shoesback.biz;

import com.shoesback.vo.PageBean;

public interface ICommentsBiz {
   //��ҳ��ȡ������Ϣ
	public PageBean FindByPage(int currentpage,int pageSize);
	//ģ������������Ϣ
	public PageBean FuzzySearchComments(String params,int currentpage,int pageSize);
	//ɾ������������Ϣ
	public void DeleteComments(int cid);
	//����ɾ��������Ϣ
	public void BatchDeleteComments(String hql);
}
