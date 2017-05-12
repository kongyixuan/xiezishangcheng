package com.shoesback.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IPercontentBiz;
import com.shoesback.biz.IPermissionBiz;
import com.shoesback.po.Admins;
import com.shoesback.po.Percontent;
import com.shoesback.po.Permission;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class PermissionAction extends ActionSupport implements ModelDriven<Permission> ,Preparable{
    IPermissionBiz permissionBiz;
    IPercontentBiz percontentBiz;    
	ActionContext ac;
    Permission permission;
    String pagesize,currentPage;
    //Ȩ��items
    String[] chk,chk_aid;
    public String[] getChk_aid() {
		return chk_aid;
	}
	public void setChk_aid(String[] chk_aid) {
		this.chk_aid = chk_aid;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public IPercontentBiz getPercontentBiz() {
		return percontentBiz;
	}
	public void setPercontentBiz(IPercontentBiz percontentBiz) {
		this.percontentBiz = percontentBiz;
	}    
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
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
	public IPermissionBiz getPermissionBiz() {
		return permissionBiz;
	}
	public void setPermissionBiz(IPermissionBiz permissionBiz) {
		this.permissionBiz = permissionBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		ac=ActionContext.getContext();
		permission=new Permission();
	}
	@Override
	public Permission getModel() {
		// TODO Auto-generated method stub
		return permission;
	}
	/**
	 * ��ҳ��ȡȨ����Ϣ
	 */
	public String execute() {
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=new PageBean();
		pb=permissionBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowPermission",pb);
		return SUCCESS;
	}
	//��ȡ����Ȩ����Ϣ
	@SuppressWarnings("unchecked")
	public String UpdatePermission(){
		System.out.println("perid:"+permission.getPerid());
		//��ȡȨ�޵�����Ϣ����
		permission=permissionBiz.FindByperid(permission.getPerid());
		ac.put("permission",permission);
		//Ȩ��items����
		if(ac.getSession().get("PercontentManager")==null){
			Admins admins=new Admins();
			if(ac.getSession().get("admininfo")!=null){
				admins=	(Admins) ac.getSession().get("admininfo");
			}
			//����Ȩ�����ݼ���
			List<Percontent> content=new ArrayList<Percontent>();
			if("root".equals(admins.getAcount())){
				//��ȡ����Աȫ��Ȩ����Ϣ
				Permission permiss=admins.getPermission();
				Iterator<Percontent> con=permiss.getPercontents().iterator();
				while (con.hasNext()) {
					Percontent items=con.next();
					content.add(items);
				}
			}else{
				//���ݿ��ȡȫ������Ա��ȫ��Ȩ��items
				content=percontentBiz.FindByManager();
			}
			ac.getSession().put("PercontentManager",content);
		}
		//��ȡȫ��Ȩ��items����
		List<Percontent> lisCon=percontentBiz.FindByperid(permission.getPerid());
		ac.getSession().put("PercontentList",lisCon);
		return INPUT;
	}
	//modifyȨ��
	@SuppressWarnings("unchecked")
	public String ModifyPermission(){
		System.out.println("perid:"+permission.getPerid());
		//���µ���Ȩ����Ϣ
		Permission mission=permissionBiz.UpdatePermission(permission);
		//��ȡԭ��Ȩ�޼���
		List<Percontent> lisCon=new ArrayList<Percontent>();
		if(ac.getSession().get("PercontentList")!=null){
			lisCon=(List<Percontent>) ac.getSession().get("PercontentList");
		}else{
			lisCon=percontentBiz.FindByperid(permission.getPerid());
		}
		//��ȡ���º�Ȩ�ޱ�ԭ��Ȩ��items�û���ѡ�еļ���
		List<String> nowper=new ArrayList<String>();
		boolean tr=true;
		for (int i = 0; i < chk.length; i++) {
			System.out.println("chk::"+chk[i]);
			for (Percontent percontent : lisCon) {				
				if(percontent.getPcitems().equals(chk[i])){
					//֤���û�����Ȩ��itemsû�и��Ĺ�
					tr=false;
					break;
				}
			}
			if(tr){
				nowper.add(chk[i]);
			}
			tr=true;
		}
		//��ȡԭ��Ȩ��items�û�ɾ���ļ���
		tr=true;
		List<String> overper=new ArrayList<String>();
		for (Percontent percontent : lisCon) {
			for (int i = 0; i < chk.length; i++) {
				if(percontent.getPcitems().equals(chk[i])){
					//֤���û�ɾ����ԭ��Ȩ��items
					tr=false;
					break;
				}
			}
			if(tr){
				overper.add(percontent.getPcitems());
			}
			tr=true;
		}
		//����Ȩ�޾���items
		percontentBiz.UpdatePercontent(mission, nowper, overper);
		return execute();
	}
	//ɾ��Ȩ����Ϣ
	public String DeletePermission(){
		permissionBiz.DeletePermission(permission.getPerid());
		return execute();
	}
	//����ɾ��
	@SuppressWarnings("unchecked")
	public String BatchDeletePermission(){
		List<Permission> lisPermission=new ArrayList<Permission>();
		if(ac.getSession().get("ShowPermission")!=null){
			lisPermission=(List<Permission>)((PageBean)ac.getSession().get("ShowPermission")).getData();
		}else{
			lisPermission=permissionBiz.FindAll();
		}
		for (Permission permission : lisPermission) {
			for (int i = 0; i < chk_aid.length; i++) {
				if(Integer.parseInt(chk_aid[i])==permission.getPerid()){
					permissionBiz.DeletePermission(permission.getPerid());
					break;
				}
			}
		}
		return execute();
	}
	//���Ȩ��
	@SuppressWarnings("unchecked")
	public String AddPermission(){
		//��ȡȨ��items���ڵ���Ϣ
		Admins admins=new Admins();
		if(ac.getSession().get("admininfo")!=null){
			admins=	(Admins) ac.getSession().get("admininfo");
		}
		//����Ȩ�����ݼ���
		List<Percontent> content=new ArrayList<Percontent>();
		if(admins.getAcount().equalsIgnoreCase("root")){
			//��ȡ����Աȫ��Ȩ����Ϣ
			Permission permiss=admins.getPermission();
			Iterator<Percontent> con=permiss.getPercontents().iterator();
			while (con.hasNext()) {
				Percontent items=con.next();
				content.add(items);
			}
		}else{
			//���ݿ��ȡȫ������Ա��ȫ��Ȩ��items
			content=percontentBiz.FindByManager();
		}
		ac.getSession().put("PercontentManager",content);
		return NONE;
	}
	//����Ȩ��
	@SuppressWarnings("unchecked")
	public String SavePermission(){
		//����Ȩ����Ϣ�������±���Ȩ����Ϣ
		Permission per=new Permission();
		per=permissionBiz.SavePermission(permission);
		//Ȩ��items ����
		List<Percontent> content=new ArrayList<Percontent>();
		if(ac.getSession().get("PercontentManager")!=null){
			content=(List<Percontent>) ac.getSession().get("PercontentManager");			
		}else{
			content=percontentBiz.FindByManager();
		}
		Percontent con=null;
		for (int i = 0; i < chk.length; i++) {
			System.out.println("chk:"+chk[i]);
			for (Percontent percontent : content) {
				//�ж����Ȩ��items��ͬ�����û���ѡ�����items
				if(percontent.getPcitems().equals(chk[i])){
					//ʵ����percontent
					con=new Percontent();
					con.setPermission(per);
					con.setPcitems(percontent.getPcitems());
					con.setPcmenu(percontent.getPcmenu());
					con.setPcremarks(percontent.getPcremarks());
					con.setPcurl(percontent.getPcurl());
					//����Ȩ��items
					percontentBiz.SavePercontent(con);
					break;
				}
			}
		}
		return execute();
	}
}