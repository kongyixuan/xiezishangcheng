package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.SpcifyShoes;
import com.shoesback.vo.PageBean;

public interface ISpcifyShoesBiz {
    //分页获取定制鞋信息
	public PageBean FindByPage(int currentpage,int pageSize);
	//获取全部定制鞋信息
	public List<SpcifyShoes> FindAll();
    //删除定制鞋信息
	public void DeleteSpcifyShoes(int spsid);
	//获取单个定制鞋信息
	public SpcifyShoes FindBySpsid(int spsid);
	//更新定制鞋信息
	public void UpdateSpcifyShoes(SpcifyShoes spcify);
	//添加定制鞋
	public void SaveSpcifyShoes(SpcifyShoes spcify);
}


