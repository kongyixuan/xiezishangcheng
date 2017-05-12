package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Permission;
import com.shoesback.vo.PageBean;

public interface IPermissionBiz {
    //��ҳ��ȡȨ����Ϣ
	public PageBean FindByPage(int currentpage,int pageSize);
	//�������Ȩ��
	public Permission SavePermission(Permission permission);
	//��ȡ����Ȩ�޶���
	public Permission FindByperid(int perid);
	//����Ȩ�޵�������
    public Permission UpdatePermission(Permission permission);
    //ɾ������Ȩ����Ϣ
    public void DeletePermission(int perid);
    //��ȡȫ��Ȩ����Ϣ
    public List<Permission> FindAll();
}
