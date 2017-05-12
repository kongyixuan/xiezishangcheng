package com.shoesback.biz.impl;

import java.util.List;

import com.shoesback.biz.IShoesizesBiz;
import com.shoesback.dao.IShoesizesDao;
import com.shoesback.po.Shoes;
import com.shoesback.po.Shoesizes;

public class ShoesizesBizImpl implements IShoesizesBiz {
    IShoesizesDao shoesizesDao;
	public IShoesizesDao getShoesizesDao() {
		return shoesizesDao;
	}
	public void setShoesizesDao(IShoesizesDao shoesizesDao) {
		this.shoesizesDao = shoesizesDao;
	}
	@Override
	public List<Shoesizes> FindByShoes(Shoes shoe) {
		// TODO Auto-generated method stub
		String hql="from Shoesizes where shoes=? and sstate=1";
		return shoesizesDao.findByObject(hql, new Object[]{shoe});
	}
	@Override
	public void BatchDeleteShoesize(int sid) {
		// TODO Auto-generated method stub
		String hql="delete from Shoesizes where shoes.sid=?";
		shoesizesDao.bulkUpdate(hql, new Object[]{sid});
	}
	@Override
	public void CreateShoesize(Shoesizes shoesize) {
		// TODO Auto-generated method stub
		shoesizesDao.create(shoesize);
	}
	
}
