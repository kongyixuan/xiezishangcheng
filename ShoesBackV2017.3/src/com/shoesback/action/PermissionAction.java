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
    //权限items
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
	 * 分页获取权限信息
	 */
	public String execute() {
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=new PageBean();
		pb=permissionBiz.FindByPage(currentpage, pages);				
		ac.getSession().put("ShowPermission",pb);
		return SUCCESS;
	}
	//获取单个权限信息
	@SuppressWarnings("unchecked")
	public String UpdatePermission(){
		System.out.println("perid:"+permission.getPerid());
		//获取权限单个信息对象
		permission=permissionBiz.FindByperid(permission.getPerid());
		ac.put("permission",permission);
		//权限items操作
		if(ac.getSession().get("PercontentManager")==null){
			Admins admins=new Admins();
			if(ac.getSession().get("admininfo")!=null){
				admins=	(Admins) ac.getSession().get("admininfo");
			}
			//创建权限内容集合
			List<Percontent> content=new ArrayList<Percontent>();
			if("root".equals(admins.getAcount())){
				//获取管理员全部权限信息
				Permission permiss=admins.getPermission();
				Iterator<Percontent> con=permiss.getPercontents().iterator();
				while (con.hasNext()) {
					Percontent items=con.next();
					content.add(items);
				}
			}else{
				//数据库获取全部管理员的全部权限items
				content=percontentBiz.FindByManager();
			}
			ac.getSession().put("PercontentManager",content);
		}
		//获取全部权限items集合
		List<Percontent> lisCon=percontentBiz.FindByperid(permission.getPerid());
		ac.getSession().put("PercontentList",lisCon);
		return INPUT;
	}
	//modify权限
	@SuppressWarnings("unchecked")
	public String ModifyPermission(){
		System.out.println("perid:"+permission.getPerid());
		//更新单个权限信息
		Permission mission=permissionBiz.UpdatePermission(permission);
		//获取原先权限集合
		List<Percontent> lisCon=new ArrayList<Percontent>();
		if(ac.getSession().get("PercontentList")!=null){
			lisCon=(List<Percontent>) ac.getSession().get("PercontentList");
		}else{
			lisCon=percontentBiz.FindByperid(permission.getPerid());
		}
		//获取更新后权限比原先权限items用户多选中的集合
		List<String> nowper=new ArrayList<String>();
		boolean tr=true;
		for (int i = 0; i < chk.length; i++) {
			System.out.println("chk::"+chk[i]);
			for (Percontent percontent : lisCon) {				
				if(percontent.getPcitems().equals(chk[i])){
					//证明用户已有权限items没有更改过
					tr=false;
					break;
				}
			}
			if(tr){
				nowper.add(chk[i]);
			}
			tr=true;
		}
		//获取原先权限items用户删除的集合
		tr=true;
		List<String> overper=new ArrayList<String>();
		for (Percontent percontent : lisCon) {
			for (int i = 0; i < chk.length; i++) {
				if(percontent.getPcitems().equals(chk[i])){
					//证明用户删除过原先权限items
					tr=false;
					break;
				}
			}
			if(tr){
				overper.add(percontent.getPcitems());
			}
			tr=true;
		}
		//更新权限具体items
		percontentBiz.UpdatePercontent(mission, nowper, overper);
		return execute();
	}
	//删除权限信息
	public String DeletePermission(){
		permissionBiz.DeletePermission(permission.getPerid());
		return execute();
	}
	//批量删除
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
	//添加权限
	@SuppressWarnings("unchecked")
	public String AddPermission(){
		//获取权限items根节点信息
		Admins admins=new Admins();
		if(ac.getSession().get("admininfo")!=null){
			admins=	(Admins) ac.getSession().get("admininfo");
		}
		//创建权限内容集合
		List<Percontent> content=new ArrayList<Percontent>();
		if(admins.getAcount().equalsIgnoreCase("root")){
			//获取管理员全部权限信息
			Permission permiss=admins.getPermission();
			Iterator<Percontent> con=permiss.getPercontents().iterator();
			while (con.hasNext()) {
				Percontent items=con.next();
				content.add(items);
			}
		}else{
			//数据库获取全部管理员的全部权限items
			content=percontentBiz.FindByManager();
		}
		ac.getSession().put("PercontentManager",content);
		return NONE;
	}
	//保存权限
	@SuppressWarnings("unchecked")
	public String SavePermission(){
		//保存权限信息，返回新保存权限信息
		Permission per=new Permission();
		per=permissionBiz.SavePermission(permission);
		//权限items 操作
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
				//判断如果权限items相同则是用户所选择的想items
				if(percontent.getPcitems().equals(chk[i])){
					//实例化percontent
					con=new Percontent();
					con.setPermission(per);
					con.setPcitems(percontent.getPcitems());
					con.setPcmenu(percontent.getPcmenu());
					con.setPcremarks(percontent.getPcremarks());
					con.setPcurl(percontent.getPcurl());
					//保存权限items
					percontentBiz.SavePercontent(con);
					break;
				}
			}
		}
		return execute();
	}
}