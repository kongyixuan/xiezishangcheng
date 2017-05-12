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
import com.shoesback.biz.IEnterpriseCertificationBiz;
import com.shoesback.po.Ads;
import com.shoesback.po.EnterpriseCertification;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class EnterpriseCertificationAction extends ActionSupport implements ModelDriven<EnterpriseCertification>,Preparable{
    IEnterpriseCertificationBiz enterpriseCertificationBiz;
    EnterpriseCertification enterpriseCertification;
    public IEnterpriseCertificationBiz getEnterpriseCertificationBiz() {
		return enterpriseCertificationBiz;
	}
	public void setEnterpriseCertificationBiz(
			IEnterpriseCertificationBiz enterpriseCertificationBiz) {
		this.enterpriseCertificationBiz = enterpriseCertificationBiz;
	}
	public EnterpriseCertification getEnterpriseCertification() {
		return enterpriseCertification;
	}
	public void setEnterpriseCertification(
			EnterpriseCertification enterpriseCertification) {
		this.enterpriseCertification = enterpriseCertification;
	}
	ActionContext ac;
    String pagesize,currentPage,chk_busiid;   
	private File uploadImage;//上传图片变量
    private String uploadImageContentType;//得到上传文件类型   
	private String uploadImageFileName;//得到上传文件名称
	public void setChk_busiid(String chk_busiid) {
		this.chk_busiid = chk_busiid;
	}
	public String getChk_busiid() {
	   return chk_busiid;
	}
	public void setChk_adid(String chk_busiid) {
	   this.chk_busiid = chk_busiid;
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
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
		enterpriseCertification=new EnterpriseCertification();
	}
	@Override
	public EnterpriseCertification getModel() {
		// TODO Auto-generated method stub
		return enterpriseCertification;
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
		pb=enterpriseCertificationBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowEnterpriseCertifications",pb);
		return SUCCESS;
	}
	//批量删除广告
	public String BatchDeleteEnterpriseCertification(){
		enterpriseCertificationBiz.BatchDeleteAds("("+chk_busiid+")");
		return execute();
	}
	public String SelectFindAll() {
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=new PageBean();
		pb=enterpriseCertificationBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowEnterpriseCertifications",pb);
		return execute();
	}
	//删除单个广告
	public String DeleteEnterpriseCertification(){
		enterpriseCertificationBiz.DeleteAds(enterpriseCertification.getBusiid());
		return execute();
	}
	//保存广告信息
	public String SaveEnterpriseCertification(){
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
		enterpriseCertification.setBusiimage(filename);
		enterpriseCertificationBiz.SaveAds(enterpriseCertification);
		return execute();
	}
}