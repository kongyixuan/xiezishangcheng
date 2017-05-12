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
    private File uploadImage;//上传图片变量
    private String uploadImageContentType;//得到上传文件类型   
	private String uploadImageFileName;//得到上传文件名称
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
	 *分页获取友情链接
	 */
	public String execute() {
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=new PageBean();
		pb=friendLinksBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowFriendLinks",pb);
		return SUCCESS;
	}
	//批量删除友情链接  
	public String BatchDeleteFriendLinks(){
		System.out.println("chk:"+chk_flid);		
		friendLinksBiz.BatchDeleteFriendLinks("("+chk_flid+")");
		return execute();
	}
	//删除单个友情链接
	public String DeleteFriendLinks(){
		friendLinksBiz.DeleteFriendLinks(this.friend.getFlid());
		return execute();
	}
	//保存友情链接
	public String SaveFriendLinks(){
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
			//给友情链接赋值图片名称
			this.friend.setFlimage(filename);
		}
		friendLinksBiz.SaveFriendLinks(friend);
		return execute();
	}
}