package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Brands;
import com.shoesback.vo.PageBean;

public interface IBrandsBiz {
    //��ȡȫ��Ʒ����Ϣ
	public List<Brands> FindAll();
	//��ҳ��ȡƷ����Ϣ
	public PageBean FindByPage(int currentpage,int pagesize);
	//����Ʒ��
	public void DeleteBrand(int state,int bid);
	//�������÷�����Ʒ��
	public void BatchDeleteBrands(String hql);
	//ģ����ѯƷ����Ϣ
	public PageBean FuzzySearchBrands(String params,int currentpage,int pagesize);
	//��ȡ��������
	public Brands FindBybid(int bid);
    //����Ʒ��
	public void ModifyBrands(Brands brand);
	//���Ʒ��
	public void AddBrands(Brands brand);
}
