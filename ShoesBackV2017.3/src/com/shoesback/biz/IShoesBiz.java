package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.Shoes;
import com.shoesback.po.Shoesizes;
import com.shoesback.vo.PageBean;

public interface IShoesBiz {
    //��ҳ��ʾЬ����Ϣ
	public PageBean FindShoesByPage(int currentpage,int pagesize);
	//��/����Ь�ӷ���
	public void DeleteShoe(int isdelete,int sid);
	//��ȡȫ����Ϣ
	public List<Shoes> FindAllShoes();
	//��������Ь����Ϣ
	public void BatchDeleteShoes(String hql);
	//ģ����ҳ����
	public PageBean FuzzySearchShoe(String params,int currentpage,int pagesize);
	//��ȡ����Ь�Ӷ���
	public Shoes Findbysid(int sid);
	//��������Ь����Ϣ
	public void UpdateShoes(Shoes shoe);
	//ɾ��ЬͼƬ
	public Shoes DeleteImage(String simage,int sid);
	//�����Ь��Ϣ
	public Shoes SaveShoes(Shoes shoe);
	//excel������ȡģ����ѯ����
	public List<Shoes> FuzzySearchShoe(String fuzzy);
	//Excel������������
	public boolean ImportShoes(List<Shoes> lstShoes,List<Shoesizes> lstShoeszies);
	//��ȡЬ��������Ϣ����
	public List<Shoes> FindByOrders();
	//��ȡ��ͨ�ҷ���ɱ���ǽ�����Ʒ��Ϣ
	public PageBean FindNoSecondKillsByPage(int currentpage,int pagesize);
	//������ɱ��Ʒ״̬
	public void UpdateSecondKills(int sid);
 }
