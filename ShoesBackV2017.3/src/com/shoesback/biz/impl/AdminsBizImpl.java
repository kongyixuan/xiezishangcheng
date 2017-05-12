package com.shoesback.biz.impl;

import java.util.List;

import com.shoesback.biz.IAdminsBiz;
import com.shoesback.dao.IAdminsDao;
import com.shoesback.po.Admins;
import com.shoesback.vo.PageBean;

public class AdminsBizImpl implements IAdminsBiz {
    IAdminsDao adminsDao;
	public IAdminsDao getAdminsDao() {
		return adminsDao;
	}
	public void setAdminsDao(IAdminsDao adminsDao) {
		this.adminsDao = adminsDao;
	}
	@Override
	public Admins AdminLogin(String asn, String pwd) {
		// TODO Auto-generated method stub
		String hql="from Admins where acount=? and apwd=?";
		List<Admins> adminLis=adminsDao.findByObject(hql, new Object[]{asn,pwd});
		if(adminLis.size()>0){
			return adminLis.get(0);
		}else{
		    return null;
		}
	}
	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return adminsDao.findByPageBean("from Admins order by aid",new Object[]{}, currentpage, pageSize);
	}
	@Override
	public void SaveAdmins(Admins admin) {
		// TODO Auto-generated method stub
		adminsDao.create(admin);
	}
	@Override
	public Admins FindByaid(int aid) {
		// TODO Auto-generated method stub
		return adminsDao.findById(aid);
	}
	@Override
	public void UpdateAdmins(Admins admin) {
		// TODO Auto-generated method stub
		adminsDao.update(admin);
	}
	@Override
	public void BatchDeleteAdmins(String hql) {
		// TODO Auto-generated method stub
		hql="delete from Admins where aid in "+hql;
		adminsDao.bulkUpdate(hql, new Object[]{});
	}
	@Override
	public void DeleteAdmins(int aid) {
		// TODO Auto-generated method stub
	    adminsDao.delete(aid);	
	}
}