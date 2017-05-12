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
	private File uploadImage;//�ϴ�ͼƬ����
    private String uploadImageContentType;//�õ��ϴ��ļ�����   
	private String uploadImageFileName;//�õ��ϴ��ļ�����
	private int year;   // ����ʱ��-���   8.29
	private int month;  // ����ʱ��-�·�   8.29
	private int day;    // ����ʱ��-����   8.29
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
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=new PageBean();
		pb=adsBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowAds",pb);
		return SUCCESS;
	}
	//����ɾ�����
	public String BatchDeleteAds(){
		adsBiz.BatchDeleteAds("("+chk_adid+")");
		return execute();
	}
	//ɾ���������
	public String DeleteAds(){
		adsBiz.DeleteAds(ads.getAdid());
		return execute();
	}
	//��������Ϣ
	public String SaveAds(){
		String formatMonth=this.month<10?"0"+String.valueOf(this.month):String.valueOf(this.month);
		String formatDay=this.day<10?"0"+String.valueOf(this.day):String.valueOf(this.day);
		String formatYear=String.valueOf(this.year)+"-"+formatMonth+"-"+formatDay+" 00:00:00";		
		//��ֵadsע��ʱ��
		ads.setAdtime(Timestamp.valueOf(formatYear));
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
		ads.setAdimage(filename);
		adsBiz.SaveAds(ads);
		return execute();
	}
}