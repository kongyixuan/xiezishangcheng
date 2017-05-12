package com.shoesback.biz.impl;

import java.util.List;

import com.shoesback.biz.ITypesBiz;
import com.shoesback.dao.ITypesDao;
import com.shoesback.po.Types;
import com.shoesback.vo.PageBean;

public class TypesBizImpl implements ITypesBiz {
    ITypesDao typesDao;
	public ITypesDao getTypesDao() {
		return typesDao;
	}
	public void setTypesDao(ITypesDao typesDao) {
		this.typesDao = typesDao;
	}
	@Override
	public List<Types> FindAll() {
		// TODO Auto-generated method stub
		return typesDao.findAll();
	}
	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return typesDao.findByPageBean("from Types order by tid",new Object[]{}, currentpage, pageSize);
	}
	@Override
	public void DeleteType(int state, int tid) {
		// TODO Auto-generated method stub
		typesDao.bulkUpdate("update Types set tdelete=? where tid=?",new Object[]{state,tid});
	}
	@Override
	public void BatchDeleteTypes(String hql) {
		// TODO Auto-generated method stub
		hql="update Types set "+hql;
		typesDao.bulkUpdate(hql, new Object[]{});
	}
	@Override
	public PageBean FuzzySearchType(String params, int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		String hql="from Types where tname like ?";
		return typesDao.findByPageBean(hql, new Object[]{"%"+params+"%"}, currentpage, pageSize);
	}
	@Override
	public Types FindByTid(int tid) {
		// TODO Auto-generated method stub
		return typesDao.findById(tid);
	}
	@Override
	public void ModifyTypes(Types type) {
		// TODO Auto-generated method stub
		typesDao.update(type);
	}
	@Override
	public void SaveTypes(Types type) {
		// TODO Auto-generated method stub
		typesDao.create(type);
	}

}
