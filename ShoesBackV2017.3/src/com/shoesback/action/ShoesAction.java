package com.shoesback.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IBrandsBiz;
import com.shoesback.biz.IShoesBiz;
import com.shoesback.biz.IShoesizesBiz;
import com.shoesback.biz.ISizesBiz;
import com.shoesback.biz.ITypesBiz;
import com.shoesback.po.Brands;
import com.shoesback.po.Shoes;
import com.shoesback.po.Shoesizes;
import com.shoesback.po.Sizes;
import com.shoesback.po.Types;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class ShoesAction extends ActionSupport implements ModelDriven<Shoes>,Preparable{
    IShoesBiz shoesBiz;
    ISizesBiz sizesBiz;
    IBrandsBiz brandsBiz;
    ITypesBiz typesBiz;
    IShoesizesBiz shoesizesBiz;    
	Shoes shoe;
    ActionContext ac;
//  ҳ��С��  ��ǰ�ڼ�ҳ        ģ����������
    String pagesize,currentPage,fuzzy;
    //����ɾ��Ь�Ӹ�ѡ��name
    int[] chk_aid;
                 
    private File uploadImage;//�ϴ�ͼƬ����
    private String uploadImageContentType;//�õ��ϴ��ļ�����   
	private String uploadImageFileName;//�õ��ϴ��ļ�����
	private String bid,tid;//����Ʒ�ƺ����ͱ��� 
	int year,month,day;//�꣬�£���	
	String[] chk,inp;//ѡ�гߴ磬��Ӧ����	
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String[] getInp() {
		return inp;
	}
	public void setInp(String[] inp) {
		this.inp = inp;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		shoe=new Shoes();
		ac=ActionContext.getContext();
	}
	@Override
	public Shoes getModel() {
		// TODO Auto-generated method stub
		return shoe;
	}
	 public File getUploadImage() {
			return uploadImage;
	}
	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}
	public String getUploadImageContentType() {
		return uploadImageContentType;
	}
	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}
	public String getUploadImageFileName() {
		return uploadImageFileName;
	}
	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
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
	public IShoesizesBiz getShoesizesBiz() {
		return shoesizesBiz;
	}
	public void setShoesizesBiz(IShoesizesBiz shoesizesBiz) {
		this.shoesizesBiz = shoesizesBiz;
	}
	public int[] getChk_aid() {
		return chk_aid;
	}
	public void setChk_aid(int[] chk_aid) {
		this.chk_aid = chk_aid;
	}
	public IShoesBiz getShoesBiz() {
		return shoesBiz;
	}
	public void setShoesBiz(IShoesBiz shoesBiz) {
		this.shoesBiz = shoesBiz;
	}
	public Shoes getShoe() {
		return shoe;
	}
	public void setShoe(Shoes shoe) {
		this.shoe = shoe;
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
	/**
	 * @return
	 */
	public String execute() {
		// TODO Auto-generated method stub
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=new PageBean();
		//�ж��Ƿ���ģ��������������
		if(fuzzy!=null&&fuzzy.trim()!=""){
			System.out.println("Fuzzy:"+fuzzy);
			//��ҳ��ȡģ��������ѯ���
			pb=shoesBiz.FuzzySearchShoe(fuzzy, currentpage, pages);
			//Ϊ����ʾҳ��ģ����������װrequest�����ǵ���ҳ��ѯ�����ģ�������������ҳ��ʾģ�����������Ϣ����
			ac.put("fuzzy", fuzzy);
		}else{
            //û����������
			pb=shoesBiz.FindShoesByPage(currentpage, pages);	
		}		
		ac.getSession().put("ShowShoes",pb);
		return SUCCESS;
	}	
	//���»�ȡЬ�ӵ�������˼·(��Ҫ���Ĺ��ܲ�������)��
	/*
	 * 1.������session "ShowShoes" �ػ��л�ȡ�����޸�Ь�ӵĶ���
	 * 2.���ͱ�����ȫ����ȡ
	 * 3.Ʒ�Ʊ�ȫ����Ϣ��ȡ
	 * 4.��ӦЬ�ӳߴ��(��ȡ��Ь��Ӧ��ͬ�ߴ�Ͳ�ͬ��������Ϣ)�ͳߴ����Ϣ��ȡ
	 * 5.image��������һ���ػ��У�ԭ��������ͼƬ��Ҫչʾ���Ա��ڲ���(ɾ�����ϴ�ͼƬ)
	 * 6.�����ֶΣ���Ӧ�����б���Ӧ���ڡ�
	 */
	@SuppressWarnings("unchecked")
	public String ShoeUpdate(){
		System.out.println("Sid:"+shoe.getSid());
		//1.������session "ShowShoes" �ػ��л�ȡ�����޸�Ь�ӵĶ���
		//����Ь�Ӽ���
		List<Shoes> lisShoes=new ArrayList<Shoes>();
		if(ac.getSession().get("ShowShoes")!=null){
			lisShoes=(List<Shoes>)((PageBean)ac.getSession().get("ShowShoes")).getData();
			//������ӦЬ�Ӷ���
			for (Shoes shoes : lisShoes) {				
				if(shoes.getSid().equals(this.shoe.getSid())){
					//֤���ǵ�ǰ�û�Ҫ�޸ĵ�Ь�Ӷ���
					this.shoe=shoes;
					break;
				}
			}
		}else{
			shoe=shoesBiz.Findbysid(shoe.getSid());
		}
//		2.���ͱ�����ȫ����ȡ
		List<Types> lstTypes=typesBiz.FindAll();
//		3.Ʒ�Ʊ�ȫ����Ϣ��ȡ
		List<Brands> lstBrands=brandsBiz.FindAll();
//		 4.��ӦЬ�ӳߴ��
		List<Sizes> lstSizes=sizesBiz.FindAll();
		//��ȡ��Ӧ��Ь���Գߴ�������Ϣ
		List<Shoesizes> lstShoesizes=shoesizesBiz.FindByShoes(shoe);
		for (Shoesizes shoesizes : lstShoesizes) {
			System.out.println("shoesize:"+shoesizes.getSizes().getSizenum());			
		}
		//����image����
		String[] arrayImage;
		if(ac.getSession().get("shoeimg")!=null){
			arrayImage=((String)ac.getSession().get("shoeimg")).split(";");
		}else{
			arrayImage=shoe.getSimage().split(";");
		}
		//����image����
		List<String> lstImage=new ArrayList<String>();
//		5.image��������һ���ػ���
		for (int i = 0; i < arrayImage.length; i++) {
			System.out.println("Images:"+arrayImage[i]);
			lstImage.add(arrayImage[i]);
		}
		//��ȡ����ʱ��������
		Timestamp ts=shoe.getSpubtime();
		String date=ts.toString();
//		6.�����ֶΣ���Ӧ�����б���Ӧ���ڡ�
		System.out.println("time:"+date);
		String temp[]=date.split(" ");
		date=temp[0];
		temp=date.split("-");
		String y=temp[0];
		String m=temp[1];
		String d=temp[2];
		int yyyy=Integer.parseInt(y);
		int mm=Integer.parseInt(m);
		int dd=Integer.parseInt(d);
		//��װ����
		ac.put("shoe", shoe);
		ac.getSession().put("lstTypes", lstTypes);
		ac.getSession().put("lstBrands", lstBrands);
		ac.getSession().put("lstSizes", lstSizes);
		ac.put("yyyy", yyyy);
		ac.put("mm", mm);
		ac.put("dd", dd);
		ac.getSession().put("lstSizeid",lstShoesizes);
		if(arrayImage[0]!=""){
			ac.put("lstImage",lstImage);
		}
		return INPUT;
	}
	//����Ь����Ϣ����
	@SuppressWarnings("unchecked")
	public synchronized String ModifyShoes() throws IOException{
		System.out.println("�޸�Ь��sid::"+shoe.getSid());
		//struts2�ϴ�ͼƬ		
		String filename=null;
		//�ж��ļ��Ƿ�Ϊ�գ��ж��û��Ƿ��ϴ���ͼƬ
		if(uploadImageFileName!=null&&uploadImageFileName!=""){
			//��ȡ�ϴ�ͼƬ·��
			String realpath=ServletActionContext.getServletContext().getRealPath("/upload/img");
			//����file����
			File file=new File(realpath);
			if(!file.exists()){
				file.mkdirs();
			}
			//�����ϴ�ͼƬ���ƣ�ԭ�����ͬ��ͼƬ�ϴ����Σ��Բ�ͬ�ļ�������
			filename=String.valueOf(System.nanoTime());
			//����struts2CopyFile�ϴ�ͼƬ                                                                              image/jpeg
			FileUtils.copyFile(uploadImage, new File(file,filename+uploadImageContentType.replaceAll("image/",".")));
		}
		//����Ʒ��,��ȡƷ�Ƽ���,����ֵ
		List<Brands> lstBrands=new ArrayList<Brands>();
		if(ac.getSession().get("lstBrands")!=null){
			lstBrands=(List<Brands>) ac.getSession().get("lstBrands");
		}else{
			lstBrands=this.brandsBiz.FindAll();
		}
		//����Ь��Ʒ�ƶ���
		for (Brands brands : lstBrands) {
			if(Integer.parseInt(bid)==brands.getBid()){
				shoe.setBrands(brands);
			}
		}
		//�������ͣ���ȡ���ͼ��ϣ�����ֵ
		List<Types> lstTypes=new ArrayList<Types>();
		if(ac.getSession().get("lstTypes")!=null){
			lstTypes=(List<Types>) ac.getSession().get("lstTypes");
		}else{
			lstTypes=this.typesBiz.FindAll();
		}
		for (Types types : lstTypes) {
			if(Integer.parseInt(tid)==types.getTid()){
				shoe.setTypes(types);
			}
		}
		//����ע��ʱ��,��ʽ��������
		String formatMonth=this.month<10?"0"+String.valueOf(this.month):String.valueOf(this.month);
		String formatDay=this.day<10?"0"+String.valueOf(this.day):String.valueOf(this.day);
		String formatYear=String.valueOf(this.year)+"-"+formatMonth+"-"+formatDay+" 00:00:00";
		System.out.println("time:"+formatYear);
		//����timestamp����
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		ts=Timestamp.valueOf(formatYear);
		//��ֵshoeע��ʱ��
		shoe.setSpubtime(ts);
		//��ֵimages,�����ж��Ƿ��Ѿ��ϴ���ͼƬ
		if(filename!=null){
			//֤����ͼƬ�ϴ�����
			shoe.setSimage(shoe.getSimage()+filename+uploadImageContentType.replaceAll("image/",".")+";");
		}
		//��ֵ���ñ�־,Ĭ������״̬
		shoe.setSdelete(0);
		//��Ь�Ӹ��²���
		shoesBiz.UpdateShoes(shoe);
		//��shoesizes����²��������Ȼ�ȡ��Ӧshoesize����
		List<Shoesizes> lstShoesizes=new ArrayList<Shoesizes>();
		if(ac.getSession().get("lstSizeid")!=null){
			lstShoesizes=(List<Shoesizes>) ac.getSession().get("lstSizeid");
		}else{
			lstShoesizes=shoesizesBiz.FindByShoes(shoe);
		}
		//����Integer���ͼ���,Ŀ�ı�����ѡ��ߴ���ӦЬ������		
		List<Integer> inputnum=new ArrayList<Integer>();
		for (int i = 0; i < inp.length; i++) {
			if(!inp[i].equals("0")){
				inputnum.add(Integer.parseInt(inp[i]));
			}
		}		
		//�����жϸ��³ߴ�������־
		int sizenum=0;
		for (Shoesizes shoesizes : lstShoesizes) {
			for (int i = 0; i < this.chk.length; i++) {
				if(chk[i].equals(shoesizes.getSizes().getSizeid())){
					if(inputnum.get(i).equals(shoesizes.getScount())){
						//֤��û�и��¹��ߴ�������Ϣ
						sizenum++;
					}
				}
			}	
		}
		//�����ߴ缯��
		List<Sizes> lstSizes =new ArrayList<Sizes>();
		if(ac.getSession().get("lstSizes")!=null){
			lstSizes =(List<Sizes>) ac.getSession().get("lstSizes");
		}else{
			lstSizes =sizesBiz.FindAll();
		}
		if(sizenum!=chk.length){
			//֤�������ı�,	��������ɾ��ԭ������
			shoesizesBiz.BatchDeleteShoesize(Integer.parseInt(shoe.getSid().toString()));
			Shoesizes shoesize=null;
			for (int i = 0; i < this.chk.length; i++) {
				shoesize=new Shoesizes();
				//��ֵЬ����Ϣ
				shoesize.setShoes(shoe);
				//��ֵ�ߴ���Ϣ
				for (Sizes sizes : lstSizes) {
					if(Integer.parseInt(chk[i].toString())==sizes.getSizeid()){
						shoesize.setSizes(sizes);
						break;
					}
				}
				shoesize.setSstate(1);
				shoesize.setScount(inputnum.get(i));	
				//���³ߴ�������Ϣ
				shoesizesBiz.CreateShoesize(shoesize);
			}
		}		
		//�رմ��ڵ�session�ػ�
		if(ac.getSession().get("lstSizeid")!=null){
			ac.getSession().remove("lstSizeid");
		}
		if(ac.getSession().get("lstSizes")!=null){
			ac.getSession().remove("lstSizes");
		}
		if(ac.getSession().get("lstTypes")!=null){
			ac.getSession().remove("lstTypes");
		}
		if(ac.getSession().get("lstBrands")!=null){
			ac.getSession().remove("lstBrands");
		}
		if(ac.getSession().get("shoeimg")!=null){
			ac.getSession().remove("shoeimg");
		}
		return execute();
	}
	//����Ь�ӷ���
	public String DeleteShoes(){
		System.out.println("deleteshoesid:"+this.shoe.getSid());
		shoesBiz.DeleteShoe(1, shoe.getSid());
		return execute();
	}
	//������Ь�ӷ���
	public String UnDeleteShoes(){
		System.out.println("undeleteshoesid:"+this.shoe.getSid());
		shoesBiz.DeleteShoe(0, shoe.getSid());
		return execute();
	}
	//��������
	@SuppressWarnings("unchecked")
	public String BatchDelete(){
		for (int i = 0; i < chk_aid.length; i++) {
			System.out.println("cheaid:"+chk_aid[i]);
		}
		//��������hql���
		String delshoe="(",undelshoe="(";
		//����Ь�Ӽ��϶���
		List<Shoes> lisShoes=new ArrayList<Shoes>();
		//�ж���ʾЬ�ӻػ��Ƿ����
		if(ac.getSession().get("ShowShoes")!=null){
			lisShoes=(List<Shoes>)((PageBean)ac.getSession().get("ShowShoes")).getData();
		}else{
			//֤���ػ������ڣ���ȡ���ݿ�ȫ����Ϣ
			lisShoes=shoesBiz.FindAllShoes();
		}
		//�������Ͻ��
		for (Shoes shoes : lisShoes) {			
			for (int i = 0; i < chk_aid.length; i++) {
				//�ж��Ƿ���ѡ��Ь��id
				if(chk_aid[i]==shoes.getSid()){
					//�жϸ�Ь���Ƿ��ǽ���״̬
					if(shoes.getSdelete().equals(0)){
						//֤��������״̬�����ƴ��hql���
						delshoe+=chk_aid[i]+",";
					}else{
						//֤���ǽ���״̬�����ƴ��hql���
						undelshoe+=chk_aid[i]+",";
					}
					//��������Ӧ������Ƴ��ڲ�ѭ��
					break;
				}
			}
		}
		//�ж��Ƿ�ѡ�н���Ь����Ϣ
		if(!delshoe.equals("(")){
			//֤��ѡ���������Ь��Ҫ���ò���
			delshoe="sdelete=1 where sid in "+delshoe.substring(0, delshoe.length()-1)+")";
			System.out.println("sheldel:"+delshoe);
			//biz�����ݿ����
			shoesBiz.BatchDeleteShoes(delshoe);
		}
		//�ж��Ƿ�ѡ�з�����Ь����Ϣ
		if(!undelshoe.equals("(")){
			//֤��ѡ���������Ь��Ҫ������Ϊ����Ь�Ӳ���
			undelshoe="sdelete=0 where sid in "+undelshoe.substring(0, undelshoe.length()-1)+")";
			System.out.println("unsheldel:"+undelshoe);
			//biz�����ݿ����
			shoesBiz.BatchDeleteShoes(undelshoe);
		}
		return execute();
	}
	//���Ь���淽��
	public String AddShoes(){
		//Ҫ��װ����������Ϣ,Ʒ�ƣ����ͣ��ߴ�
		List<Types> lstTypes=typesBiz.FindAll();
		List<Brands> lstBrands=brandsBiz.FindAll();
		List<Sizes> lstSizes=sizesBiz.FindAll();
		ac.getSession().put("lstTypes",lstTypes);
		ac.getSession().put("lstBrands",lstBrands);
		ac.getSession().put("lstSizes", lstSizes);
		return NONE;
	}
	//ɾ��Ь����ͼƬ��Ϣ����	
	public String DelImage(){
		System.out.println("img:"+shoe.getSid()+" - "+shoe.getSimage());
		Shoes sh=shoesBiz.DeleteImage(shoe.getSimage()+";", shoe.getSid());
		ac.getSession().put("shoeimg", sh.getSimage());
		return ShoeUpdate();
	}
	//���������Ь��Ϣ
	@SuppressWarnings("unchecked")
	public String CreateShoes() throws IOException{
		System.out.println("tid,bid:"+tid+" - "+bid);
		//�������ͼ��ϣ�����ֵ
		List<Types> lstTypes=new ArrayList<Types>();
		if(ac.getSession().get("lstTypes")!=null){
			lstTypes=(List<Types>) ac.getSession().get("lstTypes");			
		}else{
			lstTypes=typesBiz.FindAll();
		}
		//����Ʒ�Ƽ��ϣ�����ֵ
		List<Brands> lstBrands=new ArrayList<Brands>();
		if(ac.getSession().get("lstBrands")!=null){
			lstBrands=(List<Brands>) ac.getSession().get("lstBrands");			
		}else{
			lstBrands=brandsBiz.FindAll();
		}
		//�����ߴ缯�ϣ�����ֵ
		List<Sizes> lstSizes=new ArrayList<Sizes>();
		if(ac.getSession().get("lstSizes")!=null){
			lstSizes=(List<Sizes>) ac.getSession().get("lstSizes");
		}else{
			lstSizes=sizesBiz.FindAll();
		}
		//ѭ��������ֵtypes����
		for (Types types : lstTypes) {
			//�ж�����id�Ƿ����û���ѡ�е�������Ϣ
			if(types.getTid().equals(Integer.parseInt(tid))){
				shoe.setTypes(types);
				break;
			}
		}
		//ѭ��������ֵbrands����
		for (Brands brands : lstBrands) {
			//�ж�Ʒ��id�Ƿ����û�ѡ��Ʒ����Ϣ
			if(brands.getBid().equals(Integer.parseInt(bid))){
				shoe.setBrands(brands);
				break;
			}
		}
		//�ϴ�ͼƬ����ֵ���ж��û��Ƿ��Ѿ��ϴ���ͼƬ
		if(uploadImageFileName!=null&&uploadImageFileName!=""){
			//֤���û��ϴ���ͼƬ����ȡ�ϴ�ͼƬ·��
			String realpath=ServletActionContext.getServletContext().getRealPath("/upload/img");
			//����file����
			File file=new File(realpath);
			if(!file.exists()){
				file.mkdirs();
			}
			//�����ϴ�ͼƬ���ƣ�ԭ�����ͬ��ͼƬ�ϴ����Σ��Բ�ͬ�ļ�������
			String filename=String.valueOf(System.nanoTime())+uploadImageContentType.replaceAll("image/", ".");
			//����struts2CopyFile�ϴ�ͼƬ                                                                     
			FileUtils.copyFile(uploadImage, new File(file,filename));
			//��ֵͼƬ��Ϣ
			shoe.setSimage(filename+";");
		}else{
			//֤��û���ϴ�ͼƬ
			shoe.setSimage("");
		}
		//�Է������ڷ�װ����
		String formatMonth=this.month<10?"0"+String.valueOf(this.month):String.valueOf(this.month);
		String formatDay=this.day<10?"0"+String.valueOf(this.day):String.valueOf(this.day);
		String formatYear=String.valueOf(this.year)+"-"+formatMonth+"-"+formatDay+" 00:00:00";
		Timestamp now=new Timestamp(System.currentTimeMillis());
		now=Timestamp.valueOf(formatYear);
		//��ֵע��Ь��ʱ��
		shoe.setSpubtime(now);
		//��ֵЬ״̬
		if(shoe.getSdelete()==null){
			//֤�����û��ѡ��״̬����֤����Ь��Ϣ�ǽ���״̬
			shoe.setSdelete(1);
		}
		//�����Ь��Ϣ,������Ь����
		Shoes sh=new Shoes();
		sh=shoesBiz.SaveShoes(shoe);
		//����shoesizes�����������һ�����μ��϶���,���Ҹ�ֵ�ߴ�������Ϊ0����Ϣ
		List<Integer> inputnum=new ArrayList<Integer>();
		for (int i = 0; i < inp.length; i++) {
			if(Integer.parseInt(inp[i])!=0){
				inputnum.add(Integer.parseInt(inp[i]));
			}
		}
		//����shoesizes����
		Shoesizes shoesizes=null;
		//�ж��û��Ƿ�ѡ������Ӧ�ߴ��������Ь
		if(chk.length!=0){
			//֤���û���ѡ�гߴ����Ӧ������Ϣ
			System.out.println("chklength:"+chk.length);
			for (int i = 0; i < chk.length; i++) {
				//ʵ����shoesizes
				shoesizes=new Shoesizes();
				//��ֵshoes
				shoesizes.setShoes(sh);
				//�����ߴ磬����ֵsizes��
				for (Sizes sizes : lstSizes) {
					if(Integer.parseInt(chk[i].toString())==sizes.getSizeid()){
						//֤�����û�ѡ�еĳߴ���Ϣ
						shoesizes.setSizes(sizes);
						//��ֵ����
						shoesizes.setScount(inputnum.get(i));
						//��ֵЬ��״̬
						if(shoe.getSdelete()==0){
							shoesizes.setSstate(1);
						}else{
							shoesizes.setSstate(0);
						}
						break;
					}					
				}
				//����shoesize��
			    shoesizesBiz.CreateShoesize(shoesizes);
			}
		}
		//ɾ����ػػ���Ϣ
		if(ac.getSession().get("lstTypes")!=null){
			ac.getSession().remove("lstTypes");
		}
		if(ac.getSession().get("lstBrands")!=null){
			ac.getSession().remove("lstBrands");
		}
		if(ac.getSession().get("lstSizes")!=null){
			ac.getSession().remove("lstSizes");
		}   
		return execute();
	}
}