package com.shoesback.biz;

import com.shoesback.po.FriendLinks;
import com.shoesback.vo.PageBean;

public interface IFriendLinksBiz {
    //��ҳ��ȡ��������
	public PageBean FindByPage(int currentpage,int pageSize);
	//������������
	public void SaveFriendLinks(FriendLinks friend);
	//ɾ����������
	public void DeleteFriendLinks(int flid);
	//����ɾ����������
	public void BatchDeleteFriendLinks(String hql);
}
