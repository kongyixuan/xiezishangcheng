package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Brands;
import com.shoesback.vo.PageBean;

public interface IBrandsBiz {
    //获取全部品牌信息
	public List<Brands> FindAll();
	//分页获取品牌信息
	public PageBean FindByPage(int currentpage,int pagesize);
	//禁用品牌
	public void DeleteBrand(int state,int bid);
	//批量禁用反禁用品牌
	public void BatchDeleteBrands(String hql);
	//模糊查询品牌信息
	public PageBean FuzzySearchBrands(String params,int currentpage,int pagesize);
	//获取单个对象
	public Brands FindBybid(int bid);
    //更新品牌
	public void ModifyBrands(Brands brand);
	//添加品牌
	public void AddBrands(Brands brand);
}
