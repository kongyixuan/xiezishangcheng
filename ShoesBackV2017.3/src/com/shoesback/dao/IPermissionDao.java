package com.shoesback.dao;

import com.shoesback.po.Permission;

public interface IPermissionDao extends IGenericDao<Permission, Integer> {
     //创建保存权限信息
	public Integer SavePermission(final Permission permission);
	//更新权限单个信息
	public Integer UpdatePermission(final Permission permission);
}
