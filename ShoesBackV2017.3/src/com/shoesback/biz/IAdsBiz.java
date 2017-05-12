package com.shoesback.biz;

import com.shoesback.po.Ads;
import com.shoesback.vo.PageBean;

public interface IAdsBiz {
     //分页获取广告信息
	public PageBean FindByPage(int currentpage,int pageSize);
	//保存广告信息
	public void SaveAds(Ads ads);
	//删除单个广告信息
	public void DeleteAds(int adid);
	//批量删除广告信息
	public void BatchDeleteAds(String hql);
}
