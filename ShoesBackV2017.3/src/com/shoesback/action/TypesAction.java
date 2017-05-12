package com.shoesback.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.ITypesBiz;
import com.shoesback.po.Types;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class TypesAction extends ActionSupport implements ModelDriven<Types>,Preparable{
	ITypesBiz typesBiz;
	Types type;
	ActionContext ac;
	String pagesize,currentPage,fuzzy;
	int[] chk_aid;
	public int[] getChk_aid() {
		return chk_aid;
	}
	public void setChk_aid(int[] chk_aid) {
		this.chk_aid = chk_aid;
	}
	public Types getType() {
		return type;
	}
	public void setType(Types type) {
		this.type = type;
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
	public ITypesBiz getTypesBiz() {
		return typesBiz;
	}
	public void setTypesBiz(ITypesBiz typesBiz) {
		this.typesBiz = typesBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		type=new Types();
		ac=ActionContext.getContext();
	}
	@Override
	public Types getModel() {
		// TODO Auto-generated method stub
		return type;
	}
	/**
	 * ��ҳ��ȡ������Ϣ
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
			pb=typesBiz.FuzzySearchType(fuzzy, currentpage, pages);
			//Ϊ����ʾҳ��ģ����������װrequest�����ǵ���ҳ��ѯ�����ģ�������������ҳ��ʾģ�����������Ϣ����
			ac.put("fuzzy", fuzzy);
		}else{
            //û����������
			pb=typesBiz.FindByPage(currentpage, pages);	
		}		
		ac.getSession().put("ShowTypes",pb);
		return SUCCESS;
	}
	//��������
	@SuppressWarnings("unchecked")
	public String BatchDeleteTypes(){
		String typedel="(",untypedel="(";
		List<Types> lisTypes=new ArrayList<Types>();
		if(ac.getSession().get("ShowTypes")!=null){			
			lisTypes=(List<Types>)((PageBean)ac.getSession().get("ShowTypes")).getData();
		}else{
			lisTypes=typesBiz.FindAll();
		}
		for (Types types : lisTypes) {
			for (int i = 0; i < chk_aid.length; i++) {
			    if(chk_aid[i]==types.getTid()){
			    	if(types.getTdelete().equals(0)){//��������
			    		typedel+=chk_aid[i]+",";
			    	}else{  //����������
			    		untypedel+=chk_aid[i]+",";
			    	}
			    	break;
			    }	
			}			
		}
		if(!typedel.equals("(")){
			typedel="tdelete=1 where tid in "+typedel.substring(0, typedel.length()-1)+")";
			System.out.println("typedel��"+typedel);
			typesBiz.BatchDeleteTypes(typedel);
		}
		if(!untypedel.equals("(")){
			untypedel="tdelete=0 where tid in "+untypedel.substring(0, untypedel.length()-1)+")";
			System.out.println("untypedel��"+untypedel);
			typesBiz.BatchDeleteTypes(untypedel);
		}
		return execute();
	}
	//��ȡ�������Ͷ���
	@SuppressWarnings("unchecked")
	public String TypesUpdate(){
		System.out.println("tid:"+type.getTid());
		List<Types> lisTypes=new ArrayList<Types>();
		if(ac.getSession().get("ShowTypes")!=null){
			lisTypes=(List<Types>)((PageBean)ac.getSession().get("ShowTypes")).getData();
			for (Types types : lisTypes) {
				if(types.getTid().equals(this.type.getTid())){
					this.type=types;
					break;
				}
			}
		}else{
			type=typesBiz.FindByTid(type.getTid());
		}
		ac.put("type", type);
		return INPUT;
	}
	//��������
	public String ModifyTypes(){
		System.out.println("typid:"+type.getTid());
		//�ж�״̬ʮ��Ϊ��
		if(type.getTdelete()==null){
			type.setTdelete(1);
		}
		typesBiz.ModifyTypes(type);
		return execute();
	}
	//��������
	public String DeleteTypes(){
		typesBiz.DeleteType(1,type.getTid());
		return execute();
	}
	//����������
	public String UnDeleteTypes(){
		typesBiz.DeleteType(0,type.getTid());
		return execute();
	}	
	//�������
	public String AddTypes(){
		if(type.getTdelete()==null){
			type.setTdelete(1);
		}else{
			type.setTdelete(0);
		}
		typesBiz.SaveTypes(type);
		return execute();
	}
}