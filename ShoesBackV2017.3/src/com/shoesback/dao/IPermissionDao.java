package com.shoesback.dao;

import com.shoesback.po.Permission;

public interface IPermissionDao extends IGenericDao<Permission, Integer> {
     //��������Ȩ����Ϣ
	public Integer SavePermission(final Permission permission);
	//����Ȩ�޵�����Ϣ
	public Integer UpdatePermission(final Permission permission);
}
