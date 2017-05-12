package com.shoesback.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IBrandsBiz;
import com.shoesback.po.Brands;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class BrandsAction extends ActionSupport implements ModelDriven<Brands>,Preparable{
	IBrandsBiz brandsBiz;
	ActionContext ac;
	Brands brand;
	String pagesize,currentPage,fuzzy;
	int[] chk_aid;
	public int[] getChk_aid() {
		return chk_aid;
	}
	public void setChk_aid(int[] chk_aid) {
		this.chk_aid = chk_aid;
	}
	public Brands getBrand() {
		return brand;
	}
	public void setBrand(Brands brand) {
		this.brand = brand;
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
	public IBrandsBiz getBrandsBiz() {
		return brandsBiz;
	}
	public void setBrandsBiz(IBrandsBiz brandsBiz) {
		this.brandsBiz = brandsBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
		brand=new Brands();
	}
	@Override
	public Brands getModel() {
		// TODO Auto-generated method stub
		return brand;
	}
	/**
	 * ��ȡƷ����Ϣ����
	 */
	public String execute() {
		// TODO Auto-generated method stub
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=new PageBean();
		//�ж��Ƿ���ģ��������������
		if(fuzzy!=null&&!fuzzy.trim().equals("")){
			System.out.println("Fuzzy:"+fuzzy);
			//��ҳ��ȡģ��������ѯ���
			pb=brandsBiz.FuzzySearchBrands(fuzzy, currentpage, pages);
			ac.put("fuzzy", fuzzy);
		}else{
            //û����������
			System.out.println("pb:"+pb.getData().size());
			pb=brandsBiz.FindByPage(currentpage, pages);	
		}		
		ac.getSession().put("ShowBrands",pb);
		return SUCCESS;
	}
	//��������
	@SuppressWarnings("unchecked")
	public String BatchDeleteBrands(){
		String delhql="(",undelhql="(";
		List<Brands> lisBrands=new ArrayList<Brands>();
		if(ac.getSession().get("ShowBrands")!=null){
			lisBrands=(List<Brands>) ((PageBean)ac.getSession().get("ShowBrands")).getData();
		}else{
			lisBrands=brandsBiz.FindAll();
		}
		for (Brands brands : lisBrands) {
			for (int i = 0; i < chk_aid.length; i++) {
				if(chk_aid[i]==brands.getBid()){
					if(brands.getBstate().equals(0)){//����Ʒ��
						delhql+=chk_aid[i]+",";
					}else{//������Ʒ��
						undelhql+=chk_aid[i]+",";
					}
					break;
				}
			}
		}
		if(!delhql.equals("(")){
			//֤������Ʒ���и���
			delhql="bstate=1 where bid in "+delhql.substring(0, delhql.length()-1)+")";
			System.out.println("delhql:"+delhql);
			brandsBiz.BatchDeleteBrands(delhql);
		}
		if(!undelhql.equals("(")){
			//֤������Ʒ���и���
			undelhql="bstate=0 where bid in "+undelhql.substring(0, undelhql.length()-1)+")";
			System.out.println("undelhql:"+undelhql);
			brandsBiz.BatchDeleteBrands(undelhql);
		}
		return execute();
	}
	//��ȡ����Ʒ����Ϣ
	@SuppressWarnings("unchecked")
	public String UpdateBrands(){
		System.out.println("bid:"+brand.getBid());
		//����Ʒ�Ƽ��ϣ�Ŀ�������ȴӷ�ҳ�ػ��л�ȡ����Ʒ����Ϣ
		List<Brands> lisBrands=new ArrayList<Brands>();
		if(ac.getSession().get("ShowBrands")!=null){
			lisBrands=(List<Brands>)((PageBean)ac.getSession().get("ShowBrands")).getData();
			for (Brands brands : lisBrands) {
				if(brands.getBid().equals(brand.getBid())){
					this.brand=brands;
					break;
				}
			}
		}else{
			brand=brandsBiz.FindBybid(brand.getBid());
		}
		ac.put("brand",brand);
		return INPUT;
	}
	//����Ʒ�Ʋ���
	public String ModifyBrands(){
		//�ж�״̬ʮ��Ϊ��
		if(brand.getBstate()==null){
			brand.setBstate(1);
		}
		brandsBiz.ModifyBrands(brand);
		return execute();
	}
	//����Ʒ�Ʒ���
	public String DeleteBrands(){
		brandsBiz.DeleteBrand(1,brand.getBid());
		return execute();
	}
	//������Ʒ�Ʒ���
	public String UnDeleteBrands(){
		brandsBiz.DeleteBrand(0,brand.getBid());
		return execute();
	}
	//�����Ʒ��
	public String AddBrands(){
		//�ж�״̬ʮ��Ϊ��
		if(brand.getBstate()==null){
			brand.setBstate(1);
		}
		brandsBiz.AddBrands(brand);
		return execute();
	}
}