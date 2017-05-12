package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Types;
import com.shoesback.vo.PageBean;

public interface ITypesBiz {
   //��ȡ����ȫ����Ϣ
	public List<Types> FindAll();
	//��ҳ��ȡ������Ϣ
	public PageBean FindByPage(int currentpage,int pageSize);
	//��\����������
	public void DeleteType(int state,int tid);
	//������������
	public void BatchDeleteTypes(String hql);
	//ģ����������
	public PageBean FuzzySearchType(String params,int currentpage,int pageSize);
	//��ȡ����������Ϣ
	public Types FindByTid(int tid);
	//��������������Ϣ
	public void ModifyTypes(Types type);
	//�������
	public void SaveTypes(Types type);
}
