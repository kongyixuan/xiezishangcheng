package com.shoesback.biz.impl;

import java.util.List;

import com.shoesback.biz.ISizesBiz;
import com.shoesback.dao.ISizesDao;
import com.shoesback.po.Sizes;

public class SizesBizImpl implements ISizesBiz {
    ISizesDao sizesDao;
	public ISizesDao getSizesDao() {
		return sizesDao;
	}
	public void setSizesDao(ISizesDao sizesDao) {
		this.sizesDao = sizesDao;
	}
	@Override
	public List<Sizes> FindAll() {
		// TODO Auto-generated method stub
		return sizesDao.findAll();
	}

}
