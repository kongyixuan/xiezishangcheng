package com.shoesback.biz;

import com.shoesback.po.Admins;
import com.shoesback.vo.PageBean;

public interface IAdminsBiz {
   //后台管理员登陆
	public Admins AdminLogin(String asn,String pwd); 
	//分页获取后台管理员信息
	public PageBean FindByPage(int currentpage,int pageSize);
	//保存添加管理员
	public void SaveAdmins(Admins admin);
	//获取单个管理员对象
	public Admins FindByaid(int aid);
	//更新单个管理员对象
	public void UpdateAdmins(Admins admin);
	//批量删除管理员
	public void BatchDeleteAdmins(String hql);
	//单个删除管理员
	public void DeleteAdmins(int aid);
}
