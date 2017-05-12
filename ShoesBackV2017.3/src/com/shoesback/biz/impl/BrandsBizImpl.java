package com.shoesback.biz.impl;

import java.util.List;

import com.shoesback.biz.IBrandsBiz;
import com.shoesback.dao.IBrandsDao;
import com.shoesback.po.Brands;
import com.shoesback.vo.PageBean;

public class BrandsBizImpl implements IBrandsBiz {
    IBrandsDao brandsDao;
	@Override
	public List<Brands> FindAll() {
		// TODO Auto-generated method stub
		return brandsDao.findAll();
	}
	public IBrandsDao getBrandsDao() {
		return brandsDao;
	}
	public void setBrandsDao(IBrandsDao brandsDao) {
		this.brandsDao = brandsDao;
	}
	@Override
	public PageBean FindByPage(int currentpage, int pagesize) {
		// TODO Auto-generated method stub
		return brandsDao.findByPageBean("from Brands order by bid",new Object[]{}, currentpage, pagesize);
	}
	@Override
	public void DeleteBrand(int state, int bid) {
		// TODO Auto-generated method stub
	    brandsDao.bulkUpdate("update Brands set bstate=? where bid=?",new Object[]{state,bid});	
	}
	@Override
	public void BatchDeleteBrands(String hql) {
		// TODO Auto-generated method stub
		hql="update Brands set "+hql;
		brandsDao.bulkUpdate(hql, new Object[]{});
	}
	@Override
	public PageBean FuzzySearchBrands(String params, int currentpage,int pagesize) {
		// TODO Auto-generated method stub
		String hql="from Brands where concat (bname,bsex) like ?";
		return brandsDao.findByPageBean(hql, new Object[]{"%"+params+"%"}, currentpage, pagesize);
	}
	@Override
	public Brands FindBybid(int bid) {
		// TODO Auto-generated method stub
		return brandsDao.findById(bid);
	}
	@Override
	public void ModifyBrands(Brands brand) {
		// TODO Auto-generated method stub
		brandsDao.update(brand);
	}
	@Override
	public void AddBrands(Brands brand) {
		// TODO Auto-generated method stub
		brandsDao.create(brand);
	}

}
