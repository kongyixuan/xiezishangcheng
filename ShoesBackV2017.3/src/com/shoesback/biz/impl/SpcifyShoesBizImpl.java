package com.shoesback.biz.impl;

import java.util.List;

import com.shoesback.biz.ISpcifyShoesBiz;
import com.shoesback.dao.ISpcifyShoesDao;
import com.shoesback.po.SpcifyShoes;
import com.shoesback.vo.PageBean;

public class SpcifyShoesBizImpl implements ISpcifyShoesBiz {
    ISpcifyShoesDao spcifyShoesDao;
	public ISpcifyShoesDao getSpcifyShoesDao() {
		return spcifyShoesDao;
	}
	public void setSpcifyShoesDao(ISpcifyShoesDao spcifyShoesDao) {
		this.spcifyShoesDao = spcifyShoesDao;
	}
	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return spcifyShoesDao.findByPageBean("from SpcifyShoes order by spsid ",new Object[]{}, currentpage, pageSize);
	}
	@Override
	public List<SpcifyShoes> FindAll() {
		// TODO Auto-generated method stub
		return spcifyShoesDao.findAll();
	}
	@Override
	public void DeleteSpcifyShoes(int spsid) {
		// TODO Auto-generated method stub
		spcifyShoesDao.delete(spsid);
	}
	@Override
	public SpcifyShoes FindBySpsid(int spsid) {
		// TODO Auto-generated method stub
		return spcifyShoesDao.findById(spsid);
	}
	@Override
	public void UpdateSpcifyShoes(SpcifyShoes spcify) {
		// TODO Auto-generated method stub
		spcifyShoesDao.update(spcify);
	}
	@Override
	public void SaveSpcifyShoes(SpcifyShoes spcify) {
		// TODO Auto-generated method stub
		spcifyShoesDao.create(spcify);
	}

}
