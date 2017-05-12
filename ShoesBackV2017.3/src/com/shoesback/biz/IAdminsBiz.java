package com.shoesback.biz;

import com.shoesback.po.Admins;
import com.shoesback.vo.PageBean;

public interface IAdminsBiz {
   //��̨����Ա��½
	public Admins AdminLogin(String asn,String pwd); 
	//��ҳ��ȡ��̨����Ա��Ϣ
	public PageBean FindByPage(int currentpage,int pageSize);
	//������ӹ���Ա
	public void SaveAdmins(Admins admin);
	//��ȡ��������Ա����
	public Admins FindByaid(int aid);
	//���µ�������Ա����
	public void UpdateAdmins(Admins admin);
	//����ɾ������Ա
	public void BatchDeleteAdmins(String hql);
	//����ɾ������Ա
	public void DeleteAdmins(int aid);
}
