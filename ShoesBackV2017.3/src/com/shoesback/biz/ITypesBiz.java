package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Types;
import com.shoesback.vo.PageBean;

public interface ITypesBiz {
   //获取类型全部信息
	public List<Types> FindAll();
	//分页获取类型信息
	public PageBean FindByPage(int currentpage,int pageSize);
	//禁\反禁用类型
	public void DeleteType(int state,int tid);
	//批量更新类型
	public void BatchDeleteTypes(String hql);
	//模糊搜索类型
	public PageBean FuzzySearchType(String params,int currentpage,int pageSize);
	//获取单个类型信息
	public Types FindByTid(int tid);
	//单个更新类型信息
	public void ModifyTypes(Types type);
	//添加类型
	public void SaveTypes(Types type);
}
