package com.shoesback.biz.impl;

import java.util.List;

import com.shoesback.biz.IOrdersBiz;
import com.shoesback.dao.IOrdersDao;
import com.shoesback.po.Orders;
import com.shoesback.vo.PageBean;

public class OrdersBizImpl implements IOrdersBiz {
    IOrdersDao ordersDao;
	public IOrdersDao getOrdersDao() {
		return ordersDao;
	}
	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}
	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return ordersDao.findByPageBean("from Orders order by oid ",new Object[]{}, currentpage, pageSize);
	}
	@Override
	public PageBean FuzzySearchOrders(String params, int currentpage,int pageSize) {
		String hql="from Orders where onum like ?";		
		return ordersDao.findByPageBean(hql, new Object[]{"%"+params+"%"}, currentpage, pageSize);		
	}
	@Override
	public PageBean FindByState(int ostate, int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return ordersDao.findByPageBean("from Orders where ostate=? order by oid",new Object[]{ostate}, currentpage, pageSize);
	}
	@Override
	public void UpdateOrderStates(int oid, int ostate) {
		// TODO Auto-generated method stub
		String hql="update Orders set ostate=? where oid=?";
		ordersDao.bulkUpdate(hql, new Object[]{ostate,oid});
	}
	@Override
	public Orders FindByoid(int oid) {
		// TODO Auto-generated method stub
		return ordersDao.findById(oid);
	}
	@Override
	public List<Orders> FindAll() {
		// TODO Auto-generated method stub
		return ordersDao.findAll();
	}
	@Override
	public PageBean FindExpOrders(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return ordersDao.findByPageBean("from Orders where ostate=1 or ostate=7 order by oid",new Object[]{}, currentpage, pageSize);
	}
	@Override
	public void UpdateExpInfo(int oid, String info) {
		// TODO Auto-generated method stub
		Orders orders=this.FindByoid(oid);
		orders.setOexpinfo(orders.getOexpinfo()+","+info);
		ordersDao.update(orders);
	}

}
