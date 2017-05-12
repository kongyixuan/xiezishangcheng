package com.shoesback.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IAdminsBiz;
import com.shoesback.biz.IPercontentBiz;
import com.shoesback.biz.IPermissionBiz;
import com.shoesback.po.Admins;
import com.shoesback.po.Percontent;
import com.shoesback.po.Permission;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class AdminsAction extends ActionSupport implements ModelDriven<Admins>,Preparable{
    IAdminsBiz adminsBiz;
    IPermissionBiz permissionBiz;
    IPercontentBiz percontentBiz;    
    String pagesize,currentPage;    
	Admins admin;
    ActionContext ac;    
    //验证码
    private String validate;
    //管理员选择权限id
    int pcid;
    //批量删除id
    String[] chk_aid;
    public String[] getChk_aid() {
		return chk_aid;
	}
	public void setChk_aid(String[] chk_aid) {
		this.chk_aid = chk_aid;
	}
	public int getPcid() {
		return pcid;
	}
	public void setPcid(int pcid) {
		this.pcid = pcid;
	}
	public IPermissionBiz getPermissionBiz() {
		return permissionBiz;
	}
	public void setPermissionBiz(IPermissionBiz permissionBiz) {
		this.permissionBiz = permissionBiz;
	}
	public IPercontentBiz getPercontentBiz() {
		return percontentBiz;
	}
	public void setPercontentBiz(IPercontentBiz percontentBiz) {
		this.percontentBiz = percontentBiz;
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
	public Admins getAdmin() {
		return admin;
	}
	public void setAdmin(Admins admin) {
		this.admin = admin;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	public IAdminsBiz getAdminsBiz() {
		return adminsBiz;
	}
	public void setAdminsBiz(IAdminsBiz adminsBiz) {
		this.adminsBiz = adminsBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		admin=new Admins();
		ac=ActionContext.getContext();
	}
	@Override
	public Admins getModel() {
		// TODO Auto-generated method stub
		return admin;
	}
	/**
	 *后台管理员登陆验证
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		System.out.println("Panam:"+admin.getAcount()+" - "+admin.getApwd()+" - "+this.validate);
		//验证是否为空
		if(admin.getAcount()==null||admin.getAcount()==""){
			return ERROR;
		}
		//step1.获取系统产生的验证码
		String rand=null;
		if(ac.getSession().get("rand")==null){
			return ERROR;			
		}else{
		    rand=(String)ac.getSession().get("rand");
		}		
		//step2.判断验证码
		if(rand.equalsIgnoreCase(this.validate)){
			//step3.验证单点登陆
			List<String> online;
			if(ac.getApplication().get("online")!=null){
				online=(List<String>) ac.getApplication().get("online");
			}else{
				online=new ArrayList<String>();
			}
			if(online.contains(admin.getAcount())){
				//证明此用户已经登陆过
				//验证码错误
				ac.put("msg",2);
				return ERROR;
			}else{
				//如果页面不断刷新，则如果回话存在，只访问数据库一次
				if(ac.getSession().get("admininfo")==null){					
					//step4.第一次登陆,数据库验证用户名密码
					admin=adminsBiz.AdminLogin(admin.getAcount(), admin.getApwd());
					if(admin==null){
						//证明用户名或密码错误
						//验证码错误
						ac.put("msg",3);
						return ERROR;
					}
					//step5.正确的第一次登陆,涉及用户权限等信息封装,依此显示登陆首页所对应权限内容，测试环节，最终删除
					System.out.println("PErmission:"+admin.getPermission().getPername());
					//创建权限对象，获取具体权限items
					Permission permiss=admin.getPermission();
					//迭代器遍历权限items  set集合				
					Iterator<Percontent> con=permiss.getPercontents().iterator();
					while (con.hasNext()) {
                     	Percontent items=con.next();
						System.out.println("Cont:"+items.getPcitems()+" - "+items.getPcmenu());
					}
				    //封装登陆用户对象回话				
					ac.getSession().put("admininfo", admin);
				}
				return SUCCESS;	
			}				
		}else{
			//验证码错误
			ac.put("msg",1);
			return ERROR;
		}		
	}
	//推出登陆
	public String Logout(){
		if(ac.getSession().get("admininfo")!=null){
			ac.getSession().remove("admininfo");
		}
		return ERROR;
	}
	//显示管理员列表
	public String SearchAdmins(){
		//获取页面属性
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//创建分页对象
		PageBean pb=adminsBiz.FindByPage(currentpage, pages);		
		ac.getSession().put("ShowAdmins",pb);
		return NONE;
	}
	//批量删除管理员
	public String BatchDeleteAdmins(){
		String hql="(";
		if(chk_aid.length!=0){
			for (int i = 0; i < chk_aid.length; i++) {
				hql+=chk_aid[i]+",";
			}
		}
		hql=hql.substring(0, hql.length()-1)+")";
		adminsBiz.BatchDeleteAdmins(hql);
		return SearchAdmins();
	}
	//获取单个管理员对象
	@SuppressWarnings("unchecked")
	public String UpdateAdmins(){
		System.out.println("aid:"+admin.getAid());
		admin=adminsBiz.FindByaid(admin.getAid());
		ac.put("admins",admin);
		//获取权限集合信息
		List<Permission> lisPer=new ArrayList<Permission>();
		if(ac.getSession().get("ShowPermission")!=null){
			lisPer=(List<Permission>)((PageBean)ac.getSession().get("ShowPermission")).getData();
		}else{
			lisPer=permissionBiz.FindAll();
		}
		ac.getSession().put("PermissionList",lisPer);
		return INPUT;
	}
	//更新管理员
	public String ModifyAdmins(){
		System.out.println("pcid:"+pcid);
		//获取权限对象
		Permission per=permissionBiz.FindByperid(pcid);
		//赋值admin权限对象
		admin.setPermission(per);
		//更新admin
		adminsBiz.UpdateAdmins(admin);
		return SearchAdmins();
	}
	//删除管理员
	public String DeleteAdmins(){
		System.out.println("aid::"+admin.getAid());
		adminsBiz.DeleteAdmins(admin.getAid());
		return SearchAdmins();
	}
	//添加管理员
	@SuppressWarnings("unchecked")
	public String AddAdmins(){
		//显示全部权限信息列表
		List<Permission> lisPer=new ArrayList<Permission>();
		if(ac.getSession().get("ShowPermission")!=null){
			lisPer=(List<Permission>)((PageBean)ac.getSession().get("ShowPermission")).getData();			
		}else{
			lisPer=permissionBiz.FindAll();
		}
		ac.getSession().put("PermissionList",lisPer);
		return "addadmin";
	}
	//保存管理员
	public String SaveAdmins(){
		System.out.println("pcid:"+pcid);
		//获取权限对象
		Permission per=permissionBiz.FindByperid(pcid);
		//赋值admin权限对象
		admin.setPermission(per);
		//保存admin
		adminsBiz.SaveAdmins(admin);
		return SearchAdmins();
	}
}