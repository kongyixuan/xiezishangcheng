package com.shoesback.biz.impl;

import java.util.List;

import com.shoesback.biz.IUserBiz;
import com.shoesback.dao.IUserDao;
import com.shoesback.po.Users;
import com.shoesback.vo.PageBean;

public class UserBizImpl implements IUserBiz {
    IUserDao userDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public PageBean FindByPageUser(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return userDao.findByPageBean("from Users", new Object[]{}, currentpage, pageSize);
		
	}
	
	@Override
	public Users FindByUid(int uid) {
		// TODO Auto-generated method stub
		return userDao.findById(uid);
	}
	@Override
	public void UpdateUser(Users use) {
		// TODO Auto-generated method stub
		userDao.update(use);
	}
	@Override
	public void DeleteUser(int state, int uid) {
		// TODO Auto-generated method stub
		String hql="update Users set udelete=? where uid=?";
		userDao.bulkUpdate(hql, new Object[]{state,uid});
	}
	@Override
	public List<Users> FindAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	@Override
	public void BatchDeleteUser(String hql) {
		// TODO Auto-generated method stub
	    hql="update Users set "+hql;
	    userDao.bulkUpdate(hql, new Object[]{});
	}
	@Override
	public PageBean FuzzySearchUser(String params, int currentPage, int pagesize) {
		// mysql的concat函数用法
		String hql="from Users where concat (uaccount,uname,uemail) like ?";
		return userDao.findByPageBean(hql, new Object[]{"%"+params+"%"}, currentPage, pagesize);
	}
    
}
