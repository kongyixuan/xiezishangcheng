package com.shoesback.biz.impl;

import com.shoesback.biz.ISpcifyResultsBiz;
import com.shoesback.dao.ISpcifyResultsDao;
import com.shoesback.po.SpcifyResults;
import com.shoesback.vo.PageBean;

public class SpcifyResultsBizImpl implements ISpcifyResultsBiz {
    ISpcifyResultsDao spcifyResultsDao;	
	public ISpcifyResultsDao getSpcifyResultsDao() {
		return spcifyResultsDao;
	}

	public void setSpcifyResultsDao(ISpcifyResultsDao spcifyResultsDao) {
		this.spcifyResultsDao = spcifyResultsDao;
	}

	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return spcifyResultsDao.findByPageBean("from SpcifyResults order by sprid",new Object[]{}, currentpage, pageSize);
	}

	@Override
	public void UpdateSpcifyResults(int sprid, int spstate) {
		String hql="update SpcifyResults set spstate=? where sprid=? ";
		spcifyResultsDao.bulkUpdate(hql, new Object[]{spstate,sprid});
	}

	@Override
	public PageBean FuzzySearchSpcifyResults(String params, int currentpage,int pageSize) {
		// TODO Auto-generated method stub
		String hql="from SpcifyResults where spcifyShoes.spsname like ?";
		return spcifyResultsDao.findByPageBean(hql, new Object[]{"%"+params+"%"}, currentpage, pageSize);
	}

	@Override
	public SpcifyResults FindBysprid(int sprid) {
		// TODO Auto-generated method stub
		return spcifyResultsDao.findById(sprid);
	}

}
