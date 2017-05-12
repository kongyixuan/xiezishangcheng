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
//  页大小，  当前第几页        模糊搜索参数
    String pagesize,currentPage,fuzzy;
    //批量删除鞋子复选框name
    int[] chk_aid;
                 
    private File uploadImage;//上传图片变量
    private String uploadImageContentType;//得到上传文件类型   
	private String uploadImageFileName;//得到上传文件名称
	private String bid,tid;//更新品牌和类型变量 
	int year,month,day;//年，月，日	
	String[] chk,inp;//选中尺寸，相应数量	
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
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=new PageBean();
		//判断是否有模糊搜索参数存在
		if(fuzzy!=null&&fuzzy.trim()!=""){
			System.out.println("Fuzzy:"+fuzzy);
			//分页获取模糊搜索查询结果
			pb=shoesBiz.FuzzySearchShoe(fuzzy, currentpage, pages);
			//为了显示页面模糊参数，封装request，考虑到分页查询如果有模糊搜索条件则分页显示模糊搜索后的信息内容
			ac.put("fuzzy", fuzzy);
		}else{
            //没有搜索参数
			pb=shoesBiz.FindShoesByPage(currentpage, pages);	
		}		
		ac.getSession().put("ShowShoes",pb);
		return SUCCESS;
	}	
	//更新获取鞋子单个对象，思路(所要做的功能步骤事情)：
	/*
	 * 1.首先在session "ShowShoes" 回话中获取单个修改鞋子的对象
	 * 2.类型表，数据全部获取
	 * 3.品牌表，全部信息获取
	 * 4.对应鞋子尺寸表(获取该鞋对应不同尺寸和不同数量的信息)和尺寸表信息获取
	 * 5.image独立放入一个回话中，原因是所有图片都要展示，以便于操作(删除，上传图片)
	 * 6.日期字段，对应下拉列表框对应日期。
	 */
	@SuppressWarnings("unchecked")
	public String ShoeUpdate(){
		System.out.println("Sid:"+shoe.getSid());
		//1.首先在session "ShowShoes" 回话中获取单个修改鞋子的对象
		//创建鞋子集合
		List<Shoes> lisShoes=new ArrayList<Shoes>();
		if(ac.getSession().get("ShowShoes")!=null){
			lisShoes=(List<Shoes>)((PageBean)ac.getSession().get("ShowShoes")).getData();
			//遍历对应鞋子对象
			for (Shoes shoes : lisShoes) {				
				if(shoes.getSid().equals(this.shoe.getSid())){
					//证明是当前用户要修改的鞋子对象
					this.shoe=shoes;
					break;
				}
			}
		}else{
			shoe=shoesBiz.Findbysid(shoe.getSid());
		}
//		2.类型表，数据全部获取
		List<Types> lstTypes=typesBiz.FindAll();
//		3.品牌表，全部信息获取
		List<Brands> lstBrands=brandsBiz.FindAll();
//		 4.对应鞋子尺寸表
		List<Sizes> lstSizes=sizesBiz.FindAll();
		//获取相应该鞋所以尺寸数量信息
		List<Shoesizes> lstShoesizes=shoesizesBiz.FindByShoes(shoe);
		for (Shoesizes shoesizes : lstShoesizes) {
			System.out.println("shoesize:"+shoesizes.getSizes().getSizenum());			
		}
		//创建image数组
		String[] arrayImage;
		if(ac.getSession().get("shoeimg")!=null){
			arrayImage=((String)ac.getSession().get("shoeimg")).split(";");
		}else{
			arrayImage=shoe.getSimage().split(";");
		}
		//创建image集合
		List<String> lstImage=new ArrayList<String>();
//		5.image独立放入一个回话中
		for (int i = 0; i < arrayImage.length; i++) {
			System.out.println("Images:"+arrayImage[i]);
			lstImage.add(arrayImage[i]);
		}
		//获取发布时间年月日
		Timestamp ts=shoe.getSpubtime();
		String date=ts.toString();
//		6.日期字段，对应下拉列表框对应日期。
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
		//封装数据
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
	//更新鞋子信息方法
	@SuppressWarnings("unchecked")
	public synchronized String ModifyShoes() throws IOException{
		System.out.println("修改鞋子sid::"+shoe.getSid());
		//struts2上传图片		
		String filename=null;
		//判断文件是否为空，判断用户是否上传了图片
		if(uploadImageFileName!=null&&uploadImageFileName!=""){
			//获取上传图片路径
			String realpath=ServletActionContext.getServletContext().getRealPath("/upload/img");
			//创建file对象
			File file=new File(realpath);
			if(!file.exists()){
				file.mkdirs();
			}
			//创建上传图片名称，原因如果同样图片上传两次，以不同文件名区分
			filename=String.valueOf(System.nanoTime());
			//利用struts2CopyFile上传图片                                                                              image/jpeg
			FileUtils.copyFile(uploadImage, new File(file,filename+uploadImageContentType.replaceAll("image/",".")));
		}
		//更新品牌,获取品牌集合,并赋值
		List<Brands> lstBrands=new ArrayList<Brands>();
		if(ac.getSession().get("lstBrands")!=null){
			lstBrands=(List<Brands>) ac.getSession().get("lstBrands");
		}else{
			lstBrands=this.brandsBiz.FindAll();
		}
		//更新鞋子品牌对象
		for (Brands brands : lstBrands) {
			if(Integer.parseInt(bid)==brands.getBid()){
				shoe.setBrands(brands);
			}
		}
		//更新类型，获取类型集合，并赋值
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
		//更新注册时间,格式化年月日
		String formatMonth=this.month<10?"0"+String.valueOf(this.month):String.valueOf(this.month);
		String formatDay=this.day<10?"0"+String.valueOf(this.day):String.valueOf(this.day);
		String formatYear=String.valueOf(this.year)+"-"+formatMonth+"-"+formatDay+" 00:00:00";
		System.out.println("time:"+formatYear);
		//创建timestamp对象
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		ts=Timestamp.valueOf(formatYear);
		//赋值shoe注册时间
		shoe.setSpubtime(ts);
		//赋值images,首先判断是否已经上传了图片
		if(filename!=null){
			//证明有图片上传操作
			shoe.setSimage(shoe.getSimage()+filename+uploadImageContentType.replaceAll("image/",".")+";");
		}
		//赋值禁用标志,默认正常状态
		shoe.setSdelete(0);
		//对鞋子更新操作
		shoesBiz.UpdateShoes(shoe);
		//对shoesizes表更新操作，首先获取相应shoesize集合
		List<Shoesizes> lstShoesizes=new ArrayList<Shoesizes>();
		if(ac.getSession().get("lstSizeid")!=null){
			lstShoesizes=(List<Shoesizes>) ac.getSession().get("lstSizeid");
		}else{
			lstShoesizes=shoesizesBiz.FindByShoes(shoe);
		}
		//创建Integer泛型集合,目的遍历所选择尺寸相应鞋子数量		
		List<Integer> inputnum=new ArrayList<Integer>();
		for (int i = 0; i < inp.length; i++) {
			if(!inp[i].equals("0")){
				inputnum.add(Integer.parseInt(inp[i]));
			}
		}		
		//创建判断更新尺寸数量标志
		int sizenum=0;
		for (Shoesizes shoesizes : lstShoesizes) {
			for (int i = 0; i < this.chk.length; i++) {
				if(chk[i].equals(shoesizes.getSizes().getSizeid())){
					if(inputnum.get(i).equals(shoesizes.getScount())){
						//证明没有更新过尺寸数量信息
						sizenum++;
					}
				}
			}	
		}
		//创建尺寸集合
		List<Sizes> lstSizes =new ArrayList<Sizes>();
		if(ac.getSession().get("lstSizes")!=null){
			lstSizes =(List<Sizes>) ac.getSession().get("lstSizes");
		}else{
			lstSizes =sizesBiz.FindAll();
		}
		if(sizenum!=chk.length){
			//证明发生改变,	首先批量删除原先数据
			shoesizesBiz.BatchDeleteShoesize(Integer.parseInt(shoe.getSid().toString()));
			Shoesizes shoesize=null;
			for (int i = 0; i < this.chk.length; i++) {
				shoesize=new Shoesizes();
				//赋值鞋子信息
				shoesize.setShoes(shoe);
				//赋值尺寸信息
				for (Sizes sizes : lstSizes) {
					if(Integer.parseInt(chk[i].toString())==sizes.getSizeid()){
						shoesize.setSizes(sizes);
						break;
					}
				}
				shoesize.setSstate(1);
				shoesize.setScount(inputnum.get(i));	
				//更新尺寸数量信息
				shoesizesBiz.CreateShoesize(shoesize);
			}
		}		
		//关闭存在的session回话
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
	//禁用鞋子方法
	public String DeleteShoes(){
		System.out.println("deleteshoesid:"+this.shoe.getSid());
		shoesBiz.DeleteShoe(1, shoe.getSid());
		return execute();
	}
	//反禁用鞋子方法
	public String UnDeleteShoes(){
		System.out.println("undeleteshoesid:"+this.shoe.getSid());
		shoesBiz.DeleteShoe(0, shoe.getSid());
		return execute();
	}
	//批量更新
	@SuppressWarnings("unchecked")
	public String BatchDelete(){
		for (int i = 0; i < chk_aid.length; i++) {
			System.out.println("cheaid:"+chk_aid[i]);
		}
		//创建更新hql语句
		String delshoe="(",undelshoe="(";
		//创建鞋子集合对象
		List<Shoes> lisShoes=new ArrayList<Shoes>();
		//判断显示鞋子回话是否存在
		if(ac.getSession().get("ShowShoes")!=null){
			lisShoes=(List<Shoes>)((PageBean)ac.getSession().get("ShowShoes")).getData();
		}else{
			//证明回话不存在，获取数据库全部信息
			lisShoes=shoesBiz.FindAllShoes();
		}
		//遍历集合结果
		for (Shoes shoes : lisShoes) {			
			for (int i = 0; i < chk_aid.length; i++) {
				//判断是否是选中鞋子id
				if(chk_aid[i]==shoes.getSid()){
					//判断该鞋子是否是禁用状态
					if(shoes.getSdelete().equals(0)){
						//证明是正常状态，添加拼接hql语句
						delshoe+=chk_aid[i]+",";
					}else{
						//证明是禁用状态，添加拼接hql语句
						undelshoe+=chk_aid[i]+",";
					}
					//搜索到相应结果，推出内层循环
					break;
				}
			}
		}
		//判断是否选中禁用鞋子信息
		if(!delshoe.equals("(")){
			//证明选中这个正常鞋子要禁用操作
			delshoe="sdelete=1 where sid in "+delshoe.substring(0, delshoe.length()-1)+")";
			System.out.println("sheldel:"+delshoe);
			//biz层数据库操作
			shoesBiz.BatchDeleteShoes(delshoe);
		}
		//判断是否选中反禁用鞋子信息
		if(!undelshoe.equals("(")){
			//证明选中这个禁用鞋子要反禁用为正常鞋子操作
			undelshoe="sdelete=0 where sid in "+undelshoe.substring(0, undelshoe.length()-1)+")";
			System.out.println("unsheldel:"+undelshoe);
			//biz层数据库操作
			shoesBiz.BatchDeleteShoes(undelshoe);
		}
		return execute();
	}
	//添加鞋界面方法
	public String AddShoes(){
		//要封装三个集合信息,品牌，类型，尺寸
		List<Types> lstTypes=typesBiz.FindAll();
		List<Brands> lstBrands=brandsBiz.FindAll();
		List<Sizes> lstSizes=sizesBiz.FindAll();
		ac.getSession().put("lstTypes",lstTypes);
		ac.getSession().put("lstBrands",lstBrands);
		ac.getSession().put("lstSizes", lstSizes);
		return NONE;
	}
	//删除鞋单个图片信息对象	
	public String DelImage(){
		System.out.println("img:"+shoe.getSid()+" - "+shoe.getSimage());
		Shoes sh=shoesBiz.DeleteImage(shoe.getSimage()+";", shoe.getSid());
		ac.getSession().put("shoeimg", sh.getSimage());
		return ShoeUpdate();
	}
	//保存添加新鞋信息
	@SuppressWarnings("unchecked")
	public String CreateShoes() throws IOException{
		System.out.println("tid,bid:"+tid+" - "+bid);
		//创建类型集合，并赋值
		List<Types> lstTypes=new ArrayList<Types>();
		if(ac.getSession().get("lstTypes")!=null){
			lstTypes=(List<Types>) ac.getSession().get("lstTypes");			
		}else{
			lstTypes=typesBiz.FindAll();
		}
		//创建品牌集合，并赋值
		List<Brands> lstBrands=new ArrayList<Brands>();
		if(ac.getSession().get("lstBrands")!=null){
			lstBrands=(List<Brands>) ac.getSession().get("lstBrands");			
		}else{
			lstBrands=brandsBiz.FindAll();
		}
		//创建尺寸集合，并赋值
		List<Sizes> lstSizes=new ArrayList<Sizes>();
		if(ac.getSession().get("lstSizes")!=null){
			lstSizes=(List<Sizes>) ac.getSession().get("lstSizes");
		}else{
			lstSizes=sizesBiz.FindAll();
		}
		//循环遍历赋值types对象
		for (Types types : lstTypes) {
			//判断类型id是否是用户所选中的类型信息
			if(types.getTid().equals(Integer.parseInt(tid))){
				shoe.setTypes(types);
				break;
			}
		}
		//循环遍历赋值brands对象
		for (Brands brands : lstBrands) {
			//判断品牌id是否是用户选中品牌信息
			if(brands.getBid().equals(Integer.parseInt(bid))){
				shoe.setBrands(brands);
				break;
			}
		}
		//上传图片并赋值，判断用户是否已经上传了图片
		if(uploadImageFileName!=null&&uploadImageFileName!=""){
			//证明用户上传了图片，获取上传图片路径
			String realpath=ServletActionContext.getServletContext().getRealPath("/upload/img");
			//创建file对象
			File file=new File(realpath);
			if(!file.exists()){
				file.mkdirs();
			}
			//创建上传图片名称，原因如果同样图片上传两次，以不同文件名区分
			String filename=String.valueOf(System.nanoTime())+uploadImageContentType.replaceAll("image/", ".");
			//利用struts2CopyFile上传图片                                                                     
			FileUtils.copyFile(uploadImage, new File(file,filename));
			//赋值图片信息
			shoe.setSimage(filename+";");
		}else{
			//证明没有上传图片
			shoe.setSimage("");
		}
		//对发布日期封装操作
		String formatMonth=this.month<10?"0"+String.valueOf(this.month):String.valueOf(this.month);
		String formatDay=this.day<10?"0"+String.valueOf(this.day):String.valueOf(this.day);
		String formatYear=String.valueOf(this.year)+"-"+formatMonth+"-"+formatDay+" 00:00:00";
		Timestamp now=new Timestamp(System.currentTimeMillis());
		now=Timestamp.valueOf(formatYear);
		//赋值注册鞋子时间
		shoe.setSpubtime(now);
		//赋值鞋状态
		if(shoe.getSdelete()==null){
			//证明如果没有选中状态，则证明此鞋信息是禁用状态
			shoe.setSdelete(1);
		}
		//添加新鞋信息,创建新鞋对象
		Shoes sh=new Shoes();
		sh=shoesBiz.SaveShoes(shoe);
		//进行shoesizes表操作，创建一个整形集合对象,并且赋值尺寸数量不为0的信息
		List<Integer> inputnum=new ArrayList<Integer>();
		for (int i = 0; i < inp.length; i++) {
			if(Integer.parseInt(inp[i])!=0){
				inputnum.add(Integer.parseInt(inp[i]));
			}
		}
		//创建shoesizes对象
		Shoesizes shoesizes=null;
		//判断用户是否选中了相应尺寸和数量的鞋
		if(chk.length!=0){
			//证明用户有选中尺寸和相应数量信息
			System.out.println("chklength:"+chk.length);
			for (int i = 0; i < chk.length; i++) {
				//实例化shoesizes
				shoesizes=new Shoesizes();
				//赋值shoes
				shoesizes.setShoes(sh);
				//遍历尺寸，来赋值sizes表
				for (Sizes sizes : lstSizes) {
					if(Integer.parseInt(chk[i].toString())==sizes.getSizeid()){
						//证明是用户选中的尺寸信息
						shoesizes.setSizes(sizes);
						//赋值数量
						shoesizes.setScount(inputnum.get(i));
						//赋值鞋子状态
						if(shoe.getSdelete()==0){
							shoesizes.setSstate(1);
						}else{
							shoesizes.setSstate(0);
						}
						break;
					}					
				}
				//插入shoesize表
			    shoesizesBiz.CreateShoesize(shoesizes);
			}
		}
		//删除相关回话信息
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