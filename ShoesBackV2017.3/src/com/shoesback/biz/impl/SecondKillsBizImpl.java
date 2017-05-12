package com.shoesback.biz.impl;

import com.shoesback.biz.ISecondKillsBiz;
import com.shoesback.dao.ISecondKillsDao;
import com.shoesback.po.SecondKills;
import com.shoesback.vo.PageBean;

public class SecondKillsBizImpl implements ISecondKillsBiz {
    ISecondKillsDao secondKillsDao;
    public ISecondKillsDao getSecondKillsDao() {
		return secondKillsDao;
	}

	public void setSecondKillsDao(ISecondKillsDao secondKillsDao) {
		this.secondKillsDao = secondKillsDao;
	}

	@Override
	public PageBean FindByPage(int currentpage, int pageSize) {
		// TODO Auto-generated method stub
		return secondKillsDao.findByPageBean("from SecondKills order by skid",new Object[]{}, currentpage, pageSize);
	}

	@Override
	public void CreateSecondKill(SecondKills kill) {
		// TODO Auto-generated method stub
        secondKillsDao.create(kill);
	}

	@Override
	public SecondKills FindBySkid(int skid) {
		// TODO Auto-generated method stub
		return secondKillsDao.findById(skid);
	}

	@Override
	public void UpdateSecondKill(SecondKills kill) {
		// TODO Auto-generated method stub
		secondKillsDao.update(kill);
	}

}
