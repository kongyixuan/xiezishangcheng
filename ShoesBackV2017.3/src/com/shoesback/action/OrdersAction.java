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
	ActionContext ac;             //物流根节点	
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
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=new PageBean();
		//判断是否有模糊搜索参数存在
		if(fuzzy!=null&&!fuzzy.trim().equals("")){
			System.out.println("Fuzzy:"+fuzzy);
			//分页获取模糊搜索查询结果
			pb=ordersBiz.FuzzySearchOrders(fuzzy, currentpage, pages);
			//为了显示页面模糊参数，封装request，考虑到分页查询如果有模糊搜索条件则分页显示模糊搜索后的信息内容
			ac.put("fuzzy", fuzzy);
		}else if(order.getOstate()!=null&&!order.getOstate().equals("")){
			System.out.println("osate:"+order.getOstate());
			pb=ordersBiz.FindByState(order.getOstate(), currentpage, pages);
			ac.put("ostate", order.getOstate());
		}else{
            //没有搜索参数
			pb=ordersBiz.FindByPage(currentpage, pages);	
		}		
		ac.getSession().put("ShowOrders",pb);
		return SUCCESS;
	}
	//更新订单状态
	public String UpdateOrders(){
		ordersBiz.UpdateOrderStates(order.getOid(),order.getOstate());
		return execute();
	}
	//查看单个订单详情
	public String FindOrdersInfo(){
		order=ordersBiz.FindByoid(order.getOid());
		ac.put("order", order);
		return INPUT;
	}
	//获取物流订单信息
	public String GetExpOrders(){
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=ordersBiz.FindExpOrders(currentpage, pages);		
		ac.put("OrderExp",pb);
		return "getExp";
	}
	//新增物流节点
	public String AddExp(){
		ac.put("nowId", order.getOid());
		return "AddExp";
	}
	//查看物流信息
	public String GetExpInfo(){
		System.out.println("OID:"+this.order.getOid());
		String expinfo=ordersBiz.FindByoid(order.getOid()).getOexpinfo();
		ac.put("expinfo", expinfo);
		return "ExpInfo";
	}
	//保存根节点
	public String SaveExpSpot(){
		ordersBiz.UpdateExpInfo(order.getOid(), info);
		return GetExpOrders();
	}
}