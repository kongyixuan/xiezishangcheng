package com.shoesback.biz;

import com.shoesback.po.Ads;
import com.shoesback.po.EnterpriseCertification;
import com.shoesback.vo.PageBean;

public interface IEnterpriseCertificationBiz {
     //��ҳ��ȡ�����Ϣ
	public PageBean FindByPage(int currentpage,int pageSize);
	//��������Ϣ
	public void SaveAds(EnterpriseCertification ads);
	//ɾ�����������Ϣ
	public void DeleteAds(int adid);
	//����ɾ�������Ϣ
	public void BatchDeleteAds(String hql);
	public EnterpriseCertification findById(int ads);
}
