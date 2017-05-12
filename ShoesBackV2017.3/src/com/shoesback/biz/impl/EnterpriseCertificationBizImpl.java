package com.shoesback.biz.impl;

import com.shoesback.biz.IAdsBiz;
import com.shoesback.biz.IEnterpriseCertificationBiz;
import com.shoesback.dao.IAdsDao;
import com.shoesback.dao.IEnterpriseCertificationDao;
import com.shoesback.po.Ads;
import com.shoesback.po.EnterpriseCertification;
import com.shoesback.vo.PageBean;

public class EnterpriseCertificationBizImpl implements IEnterpriseCertificationBiz {
    IEnterpriseCertificationDao enterpriseCertificationDao;
	

	public IEnterpriseCertificationDao getEnterpriseCertificationDao() {
		return enterpriseCertificationDao;
	}

	public void setEnterpriseCertificationDao(
			IEnterpriseCertificationDao enterpriseCertificationDao) {
		this.enterpriseCertificationDao = enterpriseCertificationDao;
	}

	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return enterpriseCertificationDao.findByPageBean("from EnterpriseCertification order by busiid",new Object[]{}, currentpage, pageSize);
	}
	@Override
	public EnterpriseCertification findById(int busiid) {
		// TODO Auto-generated method stub
		return enterpriseCertificationDao.findById(busiid);
	}

	
	@Override
	public void SaveAds(EnterpriseCertification ads) {
		// TODO Auto-generated method stub
		enterpriseCertificationDao.create(ads);
	}

	@Override
	public void DeleteAds(int  busiid) {
		// TODO Auto-generated method stub
		enterpriseCertificationDao.delete( busiid);
	}

	@Override
	public void BatchDeleteAds(String hql) {
		// TODO Auto-generated method stub
		enterpriseCertificationDao.bulkUpdate("delete EnterpriseCertification where busiid in "+hql,new Object[]{});
	}

}
