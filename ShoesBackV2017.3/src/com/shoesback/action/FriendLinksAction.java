package com.shoesback.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IFriendLinksBiz;
import com.shoesback.po.FriendLinks;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class FriendLinksAction extends ActionSupport implements ModelDriven<FriendLinks>,Preparable{
    IFriendLinksBiz friendLinksBiz;
    FriendLinks friend;
    ActionContext ac;
    String pagesize,currentPage,chk_flid;
    private File uploadImage;//�ϴ�ͼƬ����
    private String uploadImageContentType;//�õ��ϴ��ļ�����   
	private String uploadImageFileName;//�õ��ϴ��ļ�����
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
	public String getChk_flid() {
		return chk_flid;
	}
	public void setChk_flid(String chk_flid) {
		this.chk_flid = chk_flid;
	}
	public IFriendLinksBiz getFriendLinksBiz() {
		return friendLinksBiz;
	}
	public void setFriendLinksBiz(IFriendLinksBiz friendLinksBiz) {
		this.friendLinksBiz = friendLinksBiz;
	}	
	public FriendLinks getFriend() {
		return friend;
	}
	public void setFriend(FriendLinks friend) {
		this.friend = friend;
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
		ac=ActionContext.getContext();
		friend=new FriendLinks();
	}
	@Override
	public FriendLinks getModel() {
		// TODO Auto-generated method stub
		return friend;
	}
	/**
	 *��ҳ��ȡ��������
	 */
	public String execute() {
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=new PageBean();
		pb=friendLinksBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowFriendLinks",pb);
		return SUCCESS;
	}
	//����ɾ����������  
	public String BatchDeleteFriendLinks(){
		System.out.println("chk:"+chk_flid);		
		friendLinksBiz.BatchDeleteFriendLinks("("+chk_flid+")");
		return execute();
	}
	//ɾ��������������
	public String DeleteFriendLinks(){
		friendLinksBiz.DeleteFriendLinks(this.friend.getFlid());
		return execute();
	}
	//������������
	public String SaveFriendLinks(){
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
			//���������Ӹ�ֵͼƬ����
			this.friend.setFlimage(filename);
		}
		friendLinksBiz.SaveFriendLinks(friend);
		return execute();
	}
}