package com.shoesback.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.shoesback.biz.IUserBiz;
import com.shoesback.po.Users;
import com.shoesback.vo.PageBean;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ModelDriven<Users>,Preparable{
    IUserBiz userBiz;
    Users use;
    ActionContext ac;    
    //     ҳ��С��  ��ǰ�ڼ�ҳ        ģ����������
    String pagesize,currentPage,fuzzy;   
	//������ѡ���û�id
    int[] chk_uid;
    public String getFuzzy() {
		return fuzzy;
	}
	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
	}
	public int[] getChk_uid() {
		return chk_uid;
	}
	public void setChk_uid(int[] chk_uid) {
		this.chk_uid = chk_uid;
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
	public Users getUse() {
		return use;
	}
	public void setUse(Users use) {
		this.use = use;
	}
	public IUserBiz getUserBiz() {
		return userBiz;
	}
	public void setUserBiz(IUserBiz userBiz) {
		this.userBiz = userBiz;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		use=new Users();
		ac=ActionContext.getContext();		
	}
	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return use;
	}
	/**
	 * ��ҳ��ʾǰ̨�û���Ϣ:
	 * 1.���ǵ�һ�ν����ҳ��Ĭ��ѡ��ȫ����Ϣ��һҳ
	 * 2.��ҳ����currentPage������Ϣ�������ģ��������������������ģ������������ѯ
	 * 3.ģ������������ѯ��
	 */
	public String execute() {
		//��ȡҳ������
		int pages=Integer.parseInt(pagesize);
		int currentpage=Integer.parseInt(this.getCurrentPage()!=null?this.getCurrentPage():"1");
		//������ҳ����
		PageBean pb=new PageBean();
		//�ж��Ƿ���ģ��������������
		if(fuzzy!=null&&fuzzy.trim()!=""){
			System.out.println("Fuzzy:"+fuzzy);
			//��ҳ��ȡģ��������ѯ���
			pb=userBiz.FuzzySearchUser(fuzzy, currentpage, pages);
			//Ϊ����ʾҳ��ģ����������װrequest�����ǵ���ҳ��ѯ�����ģ�������������ҳ��ʾģ�����������Ϣ����
			ac.put("fuzzy", fuzzy);
		}else{
            //û����������
			pb=userBiz.FindByPageUser(currentpage, pages);	
		}		
		ac.getSession().put("ShowUser",pb);
		return SUCCESS;
	}
	//��ȡ���������û�����
	@SuppressWarnings("unchecked")
	public String UserUpdate(){
		/*
		 * �����жϷ�ҳ��ʾ�ػ��б�����ȡ��Ӧ�û���Ϣ��
		 * ����ڶ�ȡ���ݿ��ȡ����������Ϣ
		 */
		System.out.println("Uid:"+use.getUid());
		//������ȡ��ʾ�û��б���
		List<Users> lisUser=new ArrayList<Users>();
		//�жϻػ��Ƿ���ڣ�����������
		if(ac.getSession().get("ShowUser")!=null){
			lisUser=(List<Users>)((PageBean)ac.getSession().get("ShowUser")).getData();
			//����������ϲ��ҳ����Ӧ�û�������Ϣ
			for (Users users : lisUser) {
				if(users.getUid().equals(this.use.getUid())){
					this.use=users;
					break;
				}
			}
		}else{
			this.use=userBiz.FindByUid(this.use.getUid());
		}
		//��װ�û�����ػ���Ϣ
		ac.put("user", this.use);
 		return "UpdateUser";
	}
	//�����û���Ϣ
	public String ModifyUser(){
		System.out.println("modifyUid:"+use.getUid());
		//�����ֶ�
		use.setUdelete(0);
		userBiz.UpdateUser(use);
		return execute();
	}
	//�����û�
	public String DeleteUser(){
		System.out.println("Deleteuser:"+use.getUid());
		//����ǰ̨�û�udeleteΪ1
		userBiz.DeleteUser(1, use.getUid());
		return execute();
	}
	//�������û�
	public String UnDeleteUser(){
		System.out.println("unDeleteuser:"+use.getUid());
		//������ǰ̨�û�udeleteΪ0
		userBiz.DeleteUser(0, use.getUid());
		return execute();
	}
	//������/�������û�
	@SuppressWarnings("unchecked")
	public String BatchDeleteUser(){
		/*
		 * �����õ���������¼�޸��û�����Ϣ��
		 * ��ȡǰ̨�û��Ự��Ϣ�б������ң������ڼ������ݿ⽻��������
		 * ���ݲ�ͬ�û�״̬����װƴ�����ݿ�hql���
		 * ����Ự���������ȡȫ�����ݿ���Ϣ�������� 
		 */
		//����ѡ���û�id
        for (int i = 0; i < this.chk_uid.length; i++) {
			System.out.println("che_id:"+chk_uid[i]);
		}
        //�������úͷ������û�����hql���
        String hqldel="(",hqlundel="(";
        //���������û�����
        List<Users> lisUser=new ArrayList<Users>();
        //�жϻỰ���Ƿ����
        if(ac.getSession().get("ShowUser")!=null){
        	lisUser=(List<Users>)((PageBean)ac.getSession().get("ShowUser")).getData();
        }else{
        	lisUser=userBiz.FindAll();
        }
        //�������ϻ�ȡ��ӦҪ���������û����󼯺�
        for (Users users : lisUser) {
			for (int i = 0; i < this.chk_uid.length; i++) {
				if(chk_uid[i]==users.getUid()){
					//�ж��û�״̬�Ƿ��Ѿ�����
					if(users.getUdelete().equals(0)){//�����û������ò���
						//ƴ��hql���
						hqldel+=chk_uid[i]+",";
					}else{                          //�����û��������ò���
						hqlundel+=chk_uid[i]+",";
					}
					break;
				}
			}
		}
        //�жϽ��ò������û��Ƿ����
        if(!hqldel.equals("(")){
        	//ƴ�����������û�����hql��䣬ɾ��ĩβ",",���"��"
        	hqldel="udelete=1 where uid in "+hqldel.substring(0, hqldel.length()-1)+")";
        	System.out.println("hqldel:"+hqldel);
        	//���������û�����
        	userBiz.BatchDeleteUser(hqldel);
        }
        //�жϷ����ò������û��Ƿ����
        if(!hqlundel.equals("(")){
        	hqlundel="udelete=0 where uid in "+hqlundel.substring(0, hqlundel.length()-1)+")";
        	System.out.println("hqlundel:"+hqlundel);
        	//�����������û�����
        	userBiz.BatchDeleteUser(hqlundel);
        }
		return execute();
	}
}