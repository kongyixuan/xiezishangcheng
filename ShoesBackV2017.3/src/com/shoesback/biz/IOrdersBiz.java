package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Orders;
import com.shoesback.vo.PageBean;

public interface IOrdersBiz {
   //��ҳ��ȡ������Ϣ
	public PageBean FindByPage(int currentpage,int pageSize);
	//ģ����������
	public PageBean FuzzySearchOrders(String params,int currentpage,int pageSize);
	//��ѯ��ͬ����״̬
	public PageBean FindByState(int ostate,int currentpage,int pageSize);
	//���¶���״̬
	public void UpdateOrderStates(int oid,int ostate);
	//��ȡ������������
	public Orders FindByoid(int oid);
	//��ȡȫ��������Ϣ
	public List<Orders> FindAll();
	//��ȡ������Ϣ����
	public PageBean FindExpOrders(int currentpage,int pageSize);
	//�����������ڵ�
	public void UpdateExpInfo(int oid,String info);
}
