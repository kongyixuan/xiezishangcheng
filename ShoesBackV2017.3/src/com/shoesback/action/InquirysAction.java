package com.shoesback.action;

import java.sql.Timestamp;
import java.util.Date;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IInquirysBiz;
import com.shoesback.po.ShoesInquirys;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class InquirysAction extends ActionSupport implements ModelDriven<ShoesInquirys>,Preparable{
	ShoesInquirys inquirys;
    IInquirysBiz inquirysBiz;
    ActionContext ac;
	String pagesize,currentPage,fuzzy;
    public ShoesInquirys getInquirys() {
		return inquirys;
	}
	public void setInquirys(ShoesInquirys inquirys) {
		this.inquirys = inquirys;
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
	
	public IInquirysBiz getInquirysBiz() {
		return inquirysBiz;
	}
	public void setInquirysBiz(IInquirysBiz inquirysBiz) {
		this.inquirysBiz = inquirysBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
		inquirys=new ShoesInquirys();
	}
	@Override
	public ShoesInquirys getModel() {
		// TODO Auto-generated method stub
		return inquirys;
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
			pb=inquirysBiz.FuzzySearchInquirys(fuzzy, currentpage, pages);
			//为了显示页面模糊参数，封装request，考虑到分页查询如果有模糊搜索条件则分页显示模糊搜索后的信息内容
			ac.put("fuzzy", fuzzy);
		}else{
            //没有搜索参数
			pb=inquirysBiz.FindByPage(currentpage, pages);	
		}		
		ac.getSession().put("ShowInquirys",pb);
		return SUCCESS;
	}
	//获取单个对象
    public String AnswerResponse(){
    	inquirys=inquirysBiz.FindBysqid(inquirys.getSqid());
    	ac.put("inquiry",inquirys);
    	return INPUT;
    }
    //回复问题
    public String UpdateResponse(){
    	inquirys.setSqanswertime(new Timestamp(new Date().getTime()));
    	inquirysBiz.AnswerInquirys(inquirys);
    	return execute();
    }
}