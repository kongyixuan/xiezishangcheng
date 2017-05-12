package com.shoesback.biz.impl;

import com.shoesback.biz.ICommentsBiz;
import com.shoesback.dao.ICommentsDao;
import com.shoesback.vo.PageBean;

public class CommentsBizImpl implements ICommentsBiz {
    ICommentsDao commentsDao;
	public ICommentsDao getCommentsDao() {
		return commentsDao;
	}

	public void setCommentsDao(ICommentsDao commentsDao) {
		this.commentsDao = commentsDao;
	}

	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return commentsDao.findByPageBean("from Comments order by cid",new Object[]{}, currentpage, pageSize);
	}

	@Override
	public PageBean FuzzySearchComments(String params, int currentpage,	int pageSize) {
		// TODO Auto-generated method stub
		String hql="from Comments where sccomments like ? ";
		return commentsDao.findByPageBean(hql,new Object[]{"%"+params+"%"}, currentpage, pageSize);
	}

	@Override
	public void DeleteComments(int cid) {
		// TODO Auto-generated method stub
		commentsDao.delete(cid);
	}

	@Override
	public void BatchDeleteComments(String hql) {
		commentsDao.bulkUpdate("delete Comments where cid in "+hql,new Object[]{});
	}

}
