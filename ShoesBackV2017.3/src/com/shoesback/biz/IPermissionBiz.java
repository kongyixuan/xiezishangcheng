package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Permission;
import com.shoesback.vo.PageBean;

public interface IPermissionBiz {
    //分页获取权限信息
	public PageBean FindByPage(int currentpage,int pageSize);
	//保存添加权限
	public Permission SavePermission(Permission permission);
	//获取单个权限对象
	public Permission FindByperid(int perid);
	//更新权限单个对象
    public Permission UpdatePermission(Permission permission);
    //删除单个权限信息
    public void DeletePermission(int perid);
    //获取全部权限信息
    public List<Permission> FindAll();
}
