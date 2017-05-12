package com.shoesback.biz;

import java.util.List;

import com.shoesback.po.SpcifyShoes;
import com.shoesback.vo.PageBean;

public interface ISpcifyShoesBiz {
    //��ҳ��ȡ����Ь��Ϣ
	public PageBean FindByPage(int currentpage,int pageSize);
	//��ȡȫ������Ь��Ϣ
	public List<SpcifyShoes> FindAll();
    //ɾ������Ь��Ϣ
	public void DeleteSpcifyShoes(int spsid);
	//��ȡ��������Ь��Ϣ
	public SpcifyShoes FindBySpsid(int spsid);
	//���¶���Ь��Ϣ
	public void UpdateSpcifyShoes(SpcifyShoes spcify);
	//��Ӷ���Ь
	public void SaveSpcifyShoes(SpcifyShoes spcify);
}


