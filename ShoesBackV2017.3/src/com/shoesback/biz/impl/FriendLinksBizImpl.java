package com.shoesback.biz.impl;

import com.shoesback.biz.IFriendLinksBiz;
import com.shoesback.dao.IFriendLinksDao;
import com.shoesback.po.FriendLinks;
import com.shoesback.vo.PageBean;

public class FriendLinksBizImpl implements IFriendLinksBiz {
    IFriendLinksDao friendLinksDao ;
	public IFriendLinksDao getFriendLinksDao() {
		return friendLinksDao;
	}

	public void setFriendLinksDao(IFriendLinksDao friendLinksDao) {
		this.friendLinksDao = friendLinksDao;
	}

	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return friendLinksDao.findByPageBean("from FriendLinks order by flid",new Object[]{}, currentpage, pageSize);
	}

	@Override
	public void SaveFriendLinks(FriendLinks friend) {
		// TODO Auto-generated method stub
        friendLinksDao.create(friend);
	}

	@Override
	public void DeleteFriendLinks(int flid) {
		// TODO Auto-generated method stub
		friendLinksDao.delete(flid);
	}

	@Override
	public void BatchDeleteFriendLinks(String hql) {
		// TODO Auto-generated method stub
		friendLinksDao.bulkUpdate("delete FriendLinks where flid in "+hql,new Object[]{});
	}

}
