package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Orders;
import com.shoesback.vo.PageBean;

public interface IOrdersBiz {
   //分页获取订单信息
	public PageBean FindByPage(int currentpage,int pageSize);
	//模糊搜索订单
	public PageBean FuzzySearchOrders(String params,int currentpage,int pageSize);
	//查询不同订单状态
	public PageBean FindByState(int ostate,int currentpage,int pageSize);
	//更新订单状态
	public void UpdateOrderStates(int oid,int ostate);
	//获取单个订单对象
	public Orders FindByoid(int oid);
	//获取全部订单信息
	public List<Orders> FindAll();
	//获取物流信息订单
	public PageBean FindExpOrders(int currentpage,int pageSize);
	//保存物流根节点
	public void UpdateExpInfo(int oid,String info);
}
