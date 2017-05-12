package com.shoesback.biz.impl;

import com.shoesback.biz.IInquirysBiz;
import com.shoesback.dao.IInquirysDao;
import com.shoesback.po.ShoesInquirys;
import com.shoesback.vo.PageBean;

public class InquirysBizImpl implements IInquirysBiz {
    IInquirysDao inquirysDao;
	public IInquirysDao getInquirysDao() {
		return inquirysDao;
	}

	public void setInquirysDao(IInquirysDao inquirysDao) {
		this.inquirysDao = inquirysDao;
	}

	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return inquirysDao.findByPageBean("from ShoesInquirys order by sqid",new Object[]{}, currentpage, pageSize);
	}

	@Override
	public ShoesInquirys FindBysqid(int sqid) {
		// TODO Auto-generated method stub
		return inquirysDao.findById(sqid);
	}

	@Override
	public void AnswerInquirys(ShoesInquirys inquirys) {
		// TODO Auto-generated method stub
		String hql="update ShoesInquirys set sqanswer=?,sqanswertime=? where sqid=?";
		inquirysDao.bulkUpdate(hql, new Object[]{inquirys.getSqanswer(),inquirys.getSqanswertime(),inquirys.getSqid()});
	}

	@Override
	public PageBean FuzzySearchInquirys(String params, int currentpage,	int pageSize) {
		String hql="from ShoesInquirys where sqquestion like ?";
		return inquirysDao.findByPageBean(hql, new Object[]{"%"+params+"%"},currentpage, pageSize);
		
	}

}
