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
    //��֤��
    private String validate;
    //����Աѡ��Ȩ��id
    int pcid;
    //����ɾ��id
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
	 *��̨����Ա��½��֤
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		System.out.println("Panam:"+admin.getAcount()+" - "+admin.getApwd()+" - "+this.validate);
		//��֤�Ƿ�Ϊ��
		if(admin.getAcount()==null||admin.getAcount()==""){
			return ERROR;
		}
		//step1.��ȡϵͳ��������֤��
		String rand=null;
		if(ac.getSession().get("rand")==null){
			return ERROR;			
		}else{
		    rand=(String)ac.getSession().get("rand");
		}		
		//step2.�ж���֤��
		if(rand.equalsIgnoreCase(this.validate)){
			//step3.��֤�����½
			List<String> online;
			if(ac.getApplication().get("online")!=null){
				online=(List<String>) ac.getApplication().get("online");
			}else{
				online=new ArrayList<String>();
			}
			if(online.contains(admin.getAcount())){
				//֤�����û��Ѿ���½��
				//��֤�����
				ac.put("msg",2);
				return ERROR;
			}else{
				//���ҳ�治��ˢ�£�������ػ����ڣ�ֻ�������ݿ�һ��
				if(ac.getSession().get("admininfo")==null){					
					//step4.��һ�ε�½,���ݿ���֤�û�������
					admin=adminsBiz.AdminLogin(admin.getAcount(), admin.getApwd());
					if(admin==null){
						//֤���û������������
						//��֤�����
						ac.put("msg",3);
						return ERROR;
					}
					//step5.��ȷ�ĵ�һ�ε�½,�漰�û�Ȩ�޵���Ϣ��װ,������ʾ��½��ҳ����ӦȨ�����ݣ����Ի��ڣ�����ɾ��
					System.out.println("PErmission:"+admin.getPermission().getPername());
					//����Ȩ�޶��󣬻�ȡ����Ȩ��items
					Permission permiss=admin.getPermission();
					//����������Ȩ��items  set����				
					Iterator<Percontent> con=permiss.getPercontents().iterator();
					while (con.hasNext()) {
                     	Percontent items=con.next();
						System.out.println("Cont:"+items.getPcitems()+" - "+items.getPcmenu());
					}
				    //��װ��½�û�����ػ�				
					ac.getSession().put("admininfo", admin);
				}
				return SUCCESS;	
			}				
		}else{
			//��֤�����
			ac.put("msg",1);
			return ERROR;
		}		
	}
	//�Ƴ���½
	public String Logout(){
		if(ac.getSession().get("admininfo")!=null){
			ac.getSession().remove("admininfo");
		}
		return ERROR;
	}
	//��ʾ����Ա�б�
	public String SearchAdmins(){
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=adminsBiz.FindByPage(currentpage, pages);		
		ac.getSession().put("ShowAdmins",pb);
		return NONE;
	}
	//����ɾ������Ա
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
	//��ȡ��������Ա����
	@SuppressWarnings("unchecked")
	public String UpdateAdmins(){
		System.out.println("aid:"+admin.getAid());
		admin=adminsBiz.FindByaid(admin.getAid());
		ac.put("admins",admin);
		//��ȡȨ�޼�����Ϣ
		List<Permission> lisPer=new ArrayList<Permission>();
		if(ac.getSession().get("ShowPermission")!=null){
			lisPer=(List<Permission>)((PageBean)ac.getSession().get("ShowPermission")).getData();
		}else{
			lisPer=permissionBiz.FindAll();
		}
		ac.getSession().put("PermissionList",lisPer);
		return INPUT;
	}
	//���¹���Ա
	public String ModifyAdmins(){
		System.out.println("pcid:"+pcid);
		//��ȡȨ�޶���
		Permission per=permissionBiz.FindByperid(pcid);
		//��ֵadminȨ�޶���
		admin.setPermission(per);
		//����admin
		adminsBiz.UpdateAdmins(admin);
		return SearchAdmins();
	}
	//ɾ������Ա
	public String DeleteAdmins(){
		System.out.println("aid::"+admin.getAid());
		adminsBiz.DeleteAdmins(admin.getAid());
		return SearchAdmins();
	}
	//��ӹ���Ա
	@SuppressWarnings("unchecked")
	public String AddAdmins(){
		//��ʾȫ��Ȩ����Ϣ�б�
		List<Permission> lisPer=new ArrayList<Permission>();
		if(ac.getSession().get("ShowPermission")!=null){
			lisPer=(List<Permission>)((PageBean)ac.getSession().get("ShowPermission")).getData();			
		}else{
			lisPer=permissionBiz.FindAll();
		}
		ac.getSession().put("PermissionList",lisPer);
		return "addadmin";
	}
	//�������Ա
	public String SaveAdmins(){
		System.out.println("pcid:"+pcid);
		//��ȡȨ�޶���
		Permission per=permissionBiz.FindByperid(pcid);
		//��ֵadminȨ�޶���
		admin.setPermission(per);
		//����admin
		adminsBiz.SaveAdmins(admin);
		return SearchAdmins();
	}
}