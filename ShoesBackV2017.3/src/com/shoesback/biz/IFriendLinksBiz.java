package com.shoesback.biz;

import com.shoesback.po.FriendLinks;
import com.shoesback.vo.PageBean;

public interface IFriendLinksBiz {
    //分页获取友情链接
	public PageBean FindByPage(int currentpage,int pageSize);
	//保存友情链接
	public void SaveFriendLinks(FriendLinks friend);
	//删除友情链接
	public void DeleteFriendLinks(int flid);
	//批量删除友情链接
	public void BatchDeleteFriendLinks(String hql);
}
