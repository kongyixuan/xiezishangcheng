package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Users;
import com.shoesback.vo.PageBean;

public interface IUserBiz {
    //��ҳ��ȡǰ̨�û���Ϣ
	public PageBean FindByPageUser(int currentpage,int pageSize);
	
	//��ȡ�����û�������Ϣ
	public Users FindByUid(int uid);
	//�����û���Ϣ
	public void UpdateUser(Users use);
	//��/�������û�����            ���ñ�־        �û�id
	public void DeleteUser(int state,int uid);
	//��ȡ�û�ȫ����Ϣ
	public List<Users> FindAll();
	//��������ǰ̨�û���Ϣ
	public void BatchDeleteUser(String hql);
	//��ҳģ������
	public PageBean FuzzySearchUser(String params,int currentPage,int pagesize);
}
