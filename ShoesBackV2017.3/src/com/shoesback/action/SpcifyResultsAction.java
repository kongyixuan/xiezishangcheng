package com.shoesback.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.ISpcifyResultsBiz;
import com.shoesback.po.SpcifyResults;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class SpcifyResultsAction extends ActionSupport implements ModelDriven<SpcifyResults>,Preparable{
    ISpcifyResultsBiz spcifyResultsBiz;
    ActionContext ac;
    String pagesize,currentPage,fuzzy;
	SpcifyResults spcifyResult;
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
	public SpcifyResults getSpcifyResult() {
		return spcifyResult;
	}
	public void setSpcifyResult(SpcifyResults spcifyResult) {
		this.spcifyResult = spcifyResult;
	}	
	public ISpcifyResultsBiz getSpcifyResultsBiz() {
		return spcifyResultsBiz;
	}
	public void setSpcifyResultsBiz(ISpcifyResultsBiz spcifyResultsBiz) {
		this.spcifyResultsBiz = spcifyResultsBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
		spcifyResult=new SpcifyResults();
	}
	@Override
	public SpcifyResults getModel() {
		// TODO Auto-generated method stub
		return spcifyResult;
	}
	/**
	 *显示定制订单详情
	 */
	public String execute() {
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=new PageBean();
		//判断是否有模糊搜索参数存在
		if(fuzzy!=null&&!fuzzy.trim().equals("")){
			System.out.println("Fuzzy:"+fuzzy);
			//分页获取模糊搜索查询结果
			pb=spcifyResultsBiz.FuzzySearchSpcifyResults(fuzzy, currentpage, pages);
			//为了显示页面模糊参数，封装request，考虑到分页查询如果有模糊搜索条件则分页显示模糊搜索后的信息内容
			ac.put("fuzzy", fuzzy);
		}else{
            //没有搜索参数
			pb=spcifyResultsBiz.FindByPage(currentpage, pages);	
		}		
		ac.getSession().put("ShowSpcifyResult",pb);
		return SUCCESS;
	}
	//更新定制订单状态
	public String UpdateSpcifyResults(){
		System.out.println("spstate:"+spcifyResult.getSprid()+"-"+spcifyResult.getSpstate());
		spcifyResultsBiz.UpdateSpcifyResults(spcifyResult.getSprid(),spcifyResult.getSpstate());
		return execute();
	}
	//订单详情
	public String FindSpcifyResultsInfo(){
		spcifyResult=spcifyResultsBiz.FindBysprid(spcifyResult.getSprid());
		ac.put("spcifyResult",spcifyResult);
		return INPUT;
	}
}