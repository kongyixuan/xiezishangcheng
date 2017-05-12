package com.shoesback.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.ICommentsBiz;
import com.shoesback.po.Comments;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class CommentsAction extends ActionSupport implements ModelDriven<Comments>,Preparable{
    ICommentsBiz commentsBiz;
    ActionContext ac;
	String pagesize,currentPage,fuzzy;
	Comments comment;
	//评论id
	String chk_aid;
	public String getChk_aid() {
		return chk_aid;
	}

	public void setChk_aid(String chk_aid) {
		this.chk_aid = chk_aid;
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

	public Comments getComment() {
		return comment;
	}

	public void setComment(Comments comment) {
		this.comment = comment;
	}

	public ICommentsBiz getCommentsBiz() {
		return commentsBiz;
	}

	public void setCommentsBiz(ICommentsBiz commentsBiz) {
		this.commentsBiz = commentsBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
		comment=new Comments();
	}

	@Override
	public Comments getModel() {
		// TODO Auto-generated method stub
		return comment;
	}
	/**
	 * 评论信息分页获取方法
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
			pb=commentsBiz.FuzzySearchComments(fuzzy, currentpage, pages);
			//为了显示页面模糊参数，封装request，考虑到分页查询如果有模糊搜索条件则分页显示模糊搜索后的信息内容
			ac.put("fuzzy", fuzzy);
		}else{
            //没有搜索参数
			pb=commentsBiz.FindByPage(currentpage, pages);	
		}		
		ac.getSession().put("ShowComments",pb);
		return SUCCESS;
	}
    //批量删除评论
	public String BatchDeleteComments(){
		System.out.println("hql:"+this.chk_aid);
		commentsBiz.BatchDeleteComments("("+chk_aid+")");
		return execute();
	}
	//删除评论
	public String DeleteComments(){
		System.out.println("dcid:"+this.comment.getCid());
		commentsBiz.DeleteComments(comment.getCid());
		return execute();
	}
}