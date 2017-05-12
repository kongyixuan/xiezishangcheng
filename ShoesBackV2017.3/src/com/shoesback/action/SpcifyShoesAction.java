package com.shoesback.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IBrandsBiz;
import com.shoesback.biz.ISizesBiz;
import com.shoesback.biz.ISpcifyShoesBiz;
import com.shoesback.biz.ISpsSizesBiz;
import com.shoesback.biz.ITypesBiz;
import com.shoesback.po.Brands;
import com.shoesback.po.Sizes;
import com.shoesback.po.SpcifyShoes;
import com.shoesback.po.SpsSizes;
import com.shoesback.po.Types;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class SpcifyShoesAction extends ActionSupport implements ModelDriven<SpcifyShoes>,Preparable{
    ISpcifyShoesBiz spcifyShoesBiz;
    ISizesBiz sizesBiz;
    IBrandsBiz brandsBiz;
    ITypesBiz typesBiz;   
    ISpsSizesBiz spsSizesBiz;	
	SpcifyShoes spcify;
    ActionContext ac;
    String pagesize,currentPage;
    //定制鞋多选复选框
    String[] chk_spsid,chk;    
	//品牌类型id
    String tid,bid;
    public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}
    public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public ISpsSizesBiz getSpsSizesBiz() {
		return spsSizesBiz;
	}

	public void setSpsSizesBiz(ISpsSizesBiz spsSizesBiz) {
		this.spsSizesBiz = spsSizesBiz;
	}
    public ISizesBiz getSizesBiz() {
		return sizesBiz;
	}

	public void setSizesBiz(ISizesBiz sizesBiz) {
		this.sizesBiz = sizesBiz;
	}

	public IBrandsBiz getBrandsBiz() {
		return brandsBiz;
	}

	public void setBrandsBiz(IBrandsBiz brandsBiz) {
		this.brandsBiz = brandsBiz;
	}

	public ITypesBiz getTypesBiz() {
		return typesBiz;
	}

	public void setTypesBiz(ITypesBiz typesBiz) {
		this.typesBiz = typesBiz;
	}
    public String[] getChk_spsid() {
		return chk_spsid;
	}

	public void setChk_spsid(String[] chk_spsid) {
		this.chk_spsid = chk_spsid;
	}

	public ISpcifyShoesBiz getSpcifyShoesBiz() {
		return spcifyShoesBiz;
	}

	public void setSpcifyShoesBiz(ISpcifyShoesBiz spcifyShoesBiz) {
		this.spcifyShoesBiz = spcifyShoesBiz;
	}

	public SpcifyShoes getSpcify() {
		return spcify;
	}

	public void setSpcify(SpcifyShoes spcify) {
		this.spcify = spcify;
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

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		spcify=new SpcifyShoes();
		ac=ActionContext.getContext();
	}

	@Override
	public SpcifyShoes getModel() {
		// TODO Auto-generated method stub
		return spcify;
	}
	/**
	 * @return
	 */
	public String execute() {
		// TODO Auto-generated method stub
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=new PageBean();		
        pb=spcifyShoesBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowSpcifyShoes",pb);
		return SUCCESS;
	}
    //批量删除
	@SuppressWarnings("unchecked")
	public String BatchDeleteSpcifyShoes(){
		List<SpcifyShoes> lisSps=new ArrayList<SpcifyShoes>();
		//获取定制鞋结果集合
		if(ac.getSession().get("ShowSpcifyShoes")!=null){
			lisSps=(List<SpcifyShoes>) ((PageBean)ac.getSession().get("ShowSpcifyShoes")).getData();
		}else{
			lisSps=spcifyShoesBiz.FindAll();
		}
		//遍历集合获取所选中单个对象
		for (SpcifyShoes spcifyShoes : lisSps) {
			for (int i = 0; i < this.chk_spsid.length; i++) {
				if(Integer.parseInt(chk_spsid[i])==spcifyShoes.getSpsid()){
					spcifyShoesBiz.DeleteSpcifyShoes(spcifyShoes.getSpsid());
					break;
				}
			}
		}
	    return execute();	
	}	
	//获取单个对象
	@SuppressWarnings("unchecked")
	public String UpdateSpcifyShoes(){
		System.out.println("spcifyid:"+this.spcify.getSpsid());
		spcify=spcifyShoesBiz.FindBySpsid(spcify.getSpsid());
		//创建类型集合
		List<Types> lstTypes=new ArrayList<Types>();
		if(ac.getSession().get("lstTypes")!=null){
			lstTypes=(List<Types>) ac.getSession().get("lstTypes");
		}else{
			lstTypes=typesBiz.FindAll();
		}
		//创建品牌集合
		List<Brands> lstBrands=new ArrayList<Brands>();
		if(ac.getSession().get("lstBrands")!=null){
			lstBrands=(List<Brands>) ac.getSession().get("lstBrands");
		}else{
			lstBrands=brandsBiz.FindAll();
		}
		List<Sizes> lstSizes=new ArrayList<Sizes>();
		if(ac.getSession().get("lstSizes")!=null){
			lstSizes=(List<Sizes>) ac.getSession().get("lstSizes");
		}else{
			lstSizes=sizesBiz.FindAll();
		}
		//遍历获取全部被选中的sizeid
		List<SpsSizes> lstSpsSizes=spsSizesBiz.FindBySpsShoes(spcify.getSpsid());
		List<Integer> lstSizeid=new ArrayList<Integer>();
		if(lstSpsSizes!=null){
			for (SpsSizes spsSize : lstSpsSizes) {
				lstSizeid.add(spsSize.getSizes().getSizeid());
			}	
		}		
		ac.getSession().put("lstTypes",lstTypes);
		ac.getSession().put("lstBrands",lstBrands);
		ac.getSession().put("lstSizes",lstSizes);
		ac.getSession().put("lstSizeid",lstSizeid);
		ac.put("spcifyShoes",spcify);
		return INPUT;
	}
	//删除定制鞋
	public String DeleteSpcifyShoes(){
		System.out.println("spsid:"+spcify.getSpsid());
		spcifyShoesBiz.DeleteSpcifyShoes(spcify.getSpsid());
		return execute();
	}
	//更新定制鞋
	@SuppressWarnings("unchecked")
	public String ModifySpcifyShoes(){
		System.out.println("tid,bid:"+bid+" - "+tid);
		//给定制鞋类型赋值
		List<Types> lstTypes=new ArrayList<Types>();
		if(ac.getSession().get("lstTypes")!=null){
			lstTypes=(List<Types>) ac.getSession().get("lstTypes");
		}else{
			lstTypes=typesBiz.FindAll();
		}
		for (Types types : lstTypes) {
			if(types.getTid().equals(Integer.parseInt(tid))){
				spcify.setTypes(types);
				break;
			}
		}
		//给定制鞋品牌赋值
		List<Brands> lstBrands=new ArrayList<Brands>();
		if(ac.getSession().get("lstBrands")!=null){
			lstBrands=(List<Brands>) ac.getSession().get("lstBrands");
		}else{
			lstBrands=brandsBiz.FindAll();
		}
		for (Brands brands : lstBrands) {
			if(brands.getBid().equals(Integer.parseInt(bid))){
				spcify.setBrands(brands);
				break;
			}
		}
		//更新定制鞋		
		spcifyShoesBiz.UpdateSpcifyShoes(spcify);
		//定制鞋尺寸操作
		if(chk!=null){
			//创建整形集合,以获取原先定制鞋尺寸信息
			List<Integer> lstSizeid=new ArrayList<Integer>();
			if(ac.getSession().get("lstSizeid")!=null){
				lstSizeid=(List<Integer>) ac.getSession().get("lstSizeid");
			}
			//获取全部尺寸信息
			List<Sizes> lstSizes=new ArrayList<Sizes>();
			if(ac.getSession().get("lstSizes")!=null){
				lstSizes=(List<Sizes>) ac.getSession().get("lstSizes");
			}else{
				lstSizes=sizesBiz.FindAll();
			}
			//更新定制鞋尺寸信息
			spsSizesBiz.ModifySpsSizes(spcify, chk, lstSizeid, lstSizes);
		}
		return execute();
	}
	//添加新定制鞋信息
	@SuppressWarnings("unchecked")
	public String AddSpcifyShoes(){
		//创建类型集合
		List<Types> lstTypes=new ArrayList<Types>();
		if(ac.getSession().get("lstTypes")!=null){
			lstTypes=(List<Types>) ac.getSession().get("lstTypes");
		}else{
			lstTypes=typesBiz.FindAll();
		}
		//创建品牌集合
		List<Brands> lstBrands=new ArrayList<Brands>();
		if(ac.getSession().get("lstBrands")!=null){
			lstBrands=(List<Brands>) ac.getSession().get("lstBrands");
		}else{
			lstBrands=brandsBiz.FindAll();
		}
		List<Sizes> lstSizes=new ArrayList<Sizes>();
		if(ac.getSession().get("lstSizes")!=null){
			lstSizes=(List<Sizes>) ac.getSession().get("lstSizes");
		}else{
			lstSizes=sizesBiz.FindAll();
		}
		ac.getSession().put("lstTypes",lstTypes);
		ac.getSession().put("lstBrands",lstBrands);
		ac.getSession().put("lstSizes",lstSizes);
		return NONE;
	}
	//保存定制鞋
	@SuppressWarnings("unchecked")
	public String SaveSpcifyShoes(){
		System.out.println("chklength:"+chk.length);
		//定制鞋添加,赋值品牌和类型
		List<Types> lstTypes=new ArrayList<Types>();
		if(ac.getSession().get("lstTypes")!=null){
			lstTypes=(List<Types>) ac.getSession().get("lstTypes");
		}else{
			lstTypes=typesBiz.FindAll();
		}
		for (Types types : lstTypes) {
			if(types.getTid().equals(Integer.parseInt(tid))){
				spcify.setTypes(types);
				break;
			}
		}
		List<Brands> lstBrands=new ArrayList<Brands>();
		if(ac.getSession().get("lstBrands")!=null){
			lstBrands=(List<Brands>) ac.getSession().get("lstBrands");
		}else{
			lstBrands=brandsBiz.FindAll();
		}
		for (Brands brands : lstBrands) {
			if(brands.getBid().equals(Integer.parseInt(bid))){
				spcify.setBrands(brands);				
				break;
			}
		}
		//添加定制鞋
		spcifyShoesBiz.SaveSpcifyShoes(spcify);
		//定制鞋尺寸操作,获取全部尺寸信息集合
		List<Sizes> lstSizes=new ArrayList<Sizes>();
		if(ac.getSession().get("lstSizes")!=null){
			lstSizes=(List<Sizes>) ac.getSession().get("lstSizes");
		}else{
			lstSizes=sizesBiz.FindAll();
		}
		List<SpsSizes> lstSpsSizes=new ArrayList<SpsSizes>();
		SpsSizes spsSizes=null;
		for (Sizes sizes : lstSizes) {
			for (int i = 0; i < chk.length; i++) {
			    if(sizes.getSizeid().equals(Integer.parseInt(chk[i]))){
			    	spsSizes=new SpsSizes();
			    	spsSizes.setSizes(sizes);
			    	spsSizes.setSpcifyShoes(spcify);
			    	spsSizes.setSpsstate(1);
			    	lstSpsSizes.add(spsSizes);
			    }	
			}			
		}
		//添加定制鞋尺寸信息
		spsSizesBiz.SaveSpsSizesList(lstSpsSizes);
		return execute();
	}
}