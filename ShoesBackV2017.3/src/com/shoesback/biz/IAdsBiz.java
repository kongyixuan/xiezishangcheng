package com.shoesback.biz;

import com.shoesback.po.Ads;
import com.shoesback.vo.PageBean;

public interface IAdsBiz {
     //��ҳ��ȡ�����Ϣ
	public PageBean FindByPage(int currentpage,int pageSize);
	//��������Ϣ
	public void SaveAds(Ads ads);
	//ɾ�����������Ϣ
	public void DeleteAds(int adid);
	//����ɾ�������Ϣ
	public void BatchDeleteAds(String hql);
}
