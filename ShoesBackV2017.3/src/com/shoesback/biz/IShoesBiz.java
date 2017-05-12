package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Shoes;
import com.shoesback.po.Shoesizes;
import com.shoesback.vo.PageBean;

public interface IShoesBiz {
    //分页显示鞋子信息
	public PageBean FindShoesByPage(int currentpage,int pagesize);
	//禁/反用鞋子方法
	public void DeleteShoe(int isdelete,int sid);
	//获取全部信息
	public List<Shoes> FindAllShoes();
	//批量更新鞋子信息
	public void BatchDeleteShoes(String hql);
	//模糊分页搜索
	public PageBean FuzzySearchShoe(String params,int currentpage,int pagesize);
	//获取单个鞋子对象
	public Shoes Findbysid(int sid);
	//单个更新鞋子信息
	public void UpdateShoes(Shoes shoe);
	//删除鞋图片
	public Shoes DeleteImage(String simage,int sid);
	//添加新鞋信息
	public Shoes SaveShoes(Shoes shoe);
	//excel导出获取模糊查询集合
	public List<Shoes> FuzzySearchShoe(String fuzzy);
	//Excel导入批量插入
	public boolean ImportShoes(List<Shoes> lstShoes,List<Shoesizes> lstShoeszies);
	//获取鞋子销量信息集合
	public List<Shoes> FindByOrders();
	//获取普通且非秒杀及非禁用商品信息
	public PageBean FindNoSecondKillsByPage(int currentpage,int pagesize);
	//更新秒杀商品状态
	public void UpdateSecondKills(int sid);
 }
