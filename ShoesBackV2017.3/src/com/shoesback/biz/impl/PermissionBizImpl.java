package com.shoesback.biz.impl;

import java.util.List;

import com.shoesback.biz.IPermissionBiz;
import com.shoesback.dao.IPermissionDao;
import com.shoesback.po.Permission;
import com.shoesback.vo.PageBean;

public class PermissionBizImpl implements IPermissionBiz {
    IPermissionDao permissionDao;
	public IPermissionDao getPermissionDao() {
		return permissionDao;
	}
	public void setPermissionDao(IPermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return permissionDao.findByPageBean("from Permission order by perid",new Object[]{} , currentpage, pageSize);
	}
	@Override
	public Permission SavePermission(Permission permission) {
		//保存权限
		permissionDao.SavePermission(permission);
		return permissionDao.findByObject("from Permission where pername=? and percont=?", new Object[]{permission.getPername(),permission.getPercont()}).get(0);
	}
	@Override
	public Permission FindByperid(int perid) {
		// TODO Auto-generated method stub
		return permissionDao.findById(perid);
	}
	@Override
	public Permission UpdatePermission(Permission permission) {
		//更新权限
		permissionDao.UpdatePermission(permission);		
		return permissionDao.findByObject("from Permission where pername=? and percont=?", new Object[]{permission.getPername(),permission.getPercont()}).get(0);
	}
	@Override
	public void DeletePermission(int perid) {
		// TODO Auto-generated method stub
		permissionDao.delete(perid);
	}
	@Override
	public List<Permission> FindAll() {
		// TODO Auto-generated method stub
		return permissionDao.findAll();
	}

}
