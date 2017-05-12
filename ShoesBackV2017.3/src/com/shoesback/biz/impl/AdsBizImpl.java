package com.shoesback.biz.impl;

import com.shoesback.biz.IAdsBiz;
import com.shoesback.dao.IAdsDao;
import com.shoesback.po.Ads;
import com.shoesback.vo.PageBean;

public class AdsBizImpl implements IAdsBiz {
    IAdsDao adsDao;
	public IAdsDao getAdsDao() {
		return adsDao;
	}

	public void setAdsDao(IAdsDao adsDao) {
		this.adsDao = adsDao;
	}

	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return adsDao.findByPageBean("from Ads order by adid",new Object[]{}, currentpage, pageSize);
	}

	@Override
	public void SaveAds(Ads ads) {
		// TODO Auto-generated method stub
       adsDao.create(ads);
	}

	@Override
	public void DeleteAds(int adid) {
		// TODO Auto-generated method stub
		adsDao.delete(adid);
	}

	@Override
	public void BatchDeleteAds(String hql) {
		// TODO Auto-generated method stub
		adsDao.bulkUpdate("delete Ads where adid in "+hql,new Object[]{});
	}

}
