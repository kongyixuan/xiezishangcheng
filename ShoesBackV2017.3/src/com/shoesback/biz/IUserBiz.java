package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Users;
import com.shoesback.vo.PageBean;

public interface IUserBiz {
    //分页获取前台用户信息
	public PageBean FindByPageUser(int currentpage,int pageSize);
	
	//获取单个用户对象信息
	public Users FindByUid(int uid);
	//更新用户信息
	public void UpdateUser(Users use);
	//禁/反禁用用户方法            禁用标志        用户id
	public void DeleteUser(int state,int uid);
	//获取用户全部信息
	public List<Users> FindAll();
	//批量更新前台用户信息
	public void BatchDeleteUser(String hql);
	//分页模糊搜索
	public PageBean FuzzySearchUser(String params,int currentPage,int pagesize);
}
