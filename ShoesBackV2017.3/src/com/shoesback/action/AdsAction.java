package com.shoesback.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IAdsBiz;
import com.shoesback.po.Ads;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class AdsAction extends ActionSupport implements ModelDriven<Ads>,Preparable{
    IAdsBiz adsBiz;
    Ads ads;
    ActionContext ac;
    String pagesize,currentPage,chk_adid;   
	private File uploadImage;//上传图片变量
    private String uploadImageContentType;//得到上传文件类型   
	private String uploadImageFileName;//得到上传文件名称
	private int year;   // 发布时间-年份   8.29
	private int month;  // 发布时间-月份   8.29
	private int day;    // 发布时间-日期   8.29
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
	public String getChk_adid() {
	   return chk_adid;
	}
	public void setChk_adid(String chk_adid) {
	   this.chk_adid = chk_adid;
	}
	public Ads getAds() {
		return ads;
	}
	public void setAds(Ads ads) {
		this.ads = ads;
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
	public IAdsBiz getAdsBiz() {
		return adsBiz;
	}
	public void setAdsBiz(IAdsBiz adsBiz) {
		this.adsBiz = adsBiz;
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
		ads=new Ads();
	}
	@Override
	public Ads getModel() {
		// TODO Auto-generated method stub
		return ads;
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
		pb=adsBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowAds",pb);
		return SUCCESS;
	}
	//批量删除广告
	public String BatchDeleteAds(){
		adsBiz.BatchDeleteAds("("+chk_adid+")");
		return execute();
	}
	//删除单个广告
	public String DeleteAds(){
		adsBiz.DeleteAds(ads.getAdid());
		return execute();
	}
	//保存广告信息
	public String SaveAds(){
		String formatMonth=this.month<10?"0"+String.valueOf(this.month):String.valueOf(this.month);
		String formatDay=this.day<10?"0"+String.valueOf(this.day):String.valueOf(this.day);
		String formatYear=String.valueOf(this.year)+"-"+formatMonth+"-"+formatDay+" 00:00:00";		
		//赋值ads注册时间
		ads.setAdtime(Timestamp.valueOf(formatYear));
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
			filename=String.valueOf(System.nanoTime())+uploadImageContentType.replaceAll("image/",".");
			//利用struts2CopyFile上传图片                                                                              image/jpeg
			try {
				FileUtils.copyFile(uploadImage, new File(file,filename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		ads.setAdimage(filename);
		adsBiz.SaveAds(ads);
		return execute();
	}
}