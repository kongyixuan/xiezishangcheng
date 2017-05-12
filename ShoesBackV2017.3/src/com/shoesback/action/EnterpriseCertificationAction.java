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
	private File uploadImage;//�ϴ�ͼƬ����
    private String uploadImageContentType;//�õ��ϴ��ļ�����   
	private String uploadImageFileName;//�õ��ϴ��ļ�����
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
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=new PageBean();
		pb=enterpriseCertificationBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowEnterpriseCertifications",pb);
		return SUCCESS;
	}
	//����ɾ�����
	public String BatchDeleteEnterpriseCertification(){
		enterpriseCertificationBiz.BatchDeleteAds("("+chk_busiid+")");
		return execute();
	}
	public String SelectFindAll() {
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=new PageBean();
		pb=enterpriseCertificationBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowEnterpriseCertifications",pb);
		return execute();
	}
	//ɾ���������
	public String DeleteEnterpriseCertification(){
		enterpriseCertificationBiz.DeleteAds(enterpriseCertification.getBusiid());
		return execute();
	}
	//��������Ϣ
	public String SaveEnterpriseCertification(){
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
			filename=String.valueOf(System.nanoTime())+uploadImageContentType.replaceAll("image/",".");
			//����struts2CopyFile�ϴ�ͼƬ                                                                              image/jpeg
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