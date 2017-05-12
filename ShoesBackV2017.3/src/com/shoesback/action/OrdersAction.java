package com.shoesback.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IOrdersBiz;
import com.shoesback.po.Orders;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class OrdersAction extends ActionSupport implements ModelDriven<Orders>,Preparable{
	IOrdersBiz ordersBiz;
	Orders order;
	ActionContext ac;             //�������ڵ�	
	String pagesize,currentPage,fuzzy,info;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
    public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public String getPagesize() {
		return pagesize;
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getFuzzy() {
		return fuzzy;
	}
	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
	}
	public IOrdersBiz getOrdersBiz() {
		return ordersBiz;
	}
	public void setOrdersBiz(IOrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
		order=new Orders();
	}
	@Override
	public Orders getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	/**
	 * @return
	 */
	public String execute() {
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=new PageBean();
		//�ж��Ƿ���ģ��������������
		if(fuzzy!=null&&!fuzzy.trim().equals("")){
			System.out.println("Fuzzy:"+fuzzy);
			//��ҳ��ȡģ��������ѯ���
			pb=ordersBiz.FuzzySearchOrders(fuzzy, currentpage, pages);
			//Ϊ����ʾҳ��ģ����������װrequest�����ǵ���ҳ��ѯ�����ģ�������������ҳ��ʾģ�����������Ϣ����
			ac.put("fuzzy", fuzzy);
		}else if(order.getOstate()!=null&&!order.getOstate().equals("")){
			System.out.println("osate:"+order.getOstate());
			pb=ordersBiz.FindByState(order.getOstate(), currentpage, pages);
			ac.put("ostate", order.getOstate());
		}else{
            //û����������
			pb=ordersBiz.FindByPage(currentpage, pages);	
		}		
		ac.getSession().put("ShowOrders",pb);
		return SUCCESS;
	}
	//���¶���״̬
	public String UpdateOrders(){
		ordersBiz.UpdateOrderStates(order.getOid(),order.getOstate());
		return execute();
	}
	//�鿴������������
	public String FindOrdersInfo(){
		order=ordersBiz.FindByoid(order.getOid());
		ac.put("order", order);
		return INPUT;
	}
	//��ȡ����������Ϣ
	public String GetExpOrders(){
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=ordersBiz.FindExpOrders(currentpage, pages);		
		ac.put("OrderExp",pb);
		return "getExp";
	}
	//���������ڵ�
	public String AddExp(){
		ac.put("nowId", order.getOid());
		return "AddExp";
	}
	//�鿴������Ϣ
	public String GetExpInfo(){
		System.out.println("OID:"+this.order.getOid());
		String expinfo=ordersBiz.FindByoid(order.getOid()).getOexpinfo();
		ac.put("expinfo", expinfo);
		return "ExpInfo";
	}
	//������ڵ�
	public String SaveExpSpot(){
		ordersBiz.UpdateExpInfo(order.getOid(), info);
		return GetExpOrders();
	}
}