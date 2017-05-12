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
	//����id
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
	 * ������Ϣ��ҳ��ȡ����
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
			pb=commentsBiz.FuzzySearchComments(fuzzy, currentpage, pages);
			//Ϊ����ʾҳ��ģ����������װrequest�����ǵ���ҳ��ѯ�����ģ�������������ҳ��ʾģ�����������Ϣ����
			ac.put("fuzzy", fuzzy);
		}else{
            //û����������
			pb=commentsBiz.FindByPage(currentpage, pages);	
		}		
		ac.getSession().put("ShowComments",pb);
		return SUCCESS;
	}
    //����ɾ������
	public String BatchDeleteComments(){
		System.out.println("hql:"+this.chk_aid);
		commentsBiz.BatchDeleteComments("("+chk_aid+")");
		return execute();
	}
	//ɾ������
	public String DeleteComments(){
		System.out.println("dcid:"+this.comment.getCid());
		commentsBiz.DeleteComments(comment.getCid());
		return execute();
	}
}