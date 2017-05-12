package com.shoesback.biz.impl;

import java.util.List;

import com.shoesback.biz.IPercontentBiz;
import com.shoesback.dao.IPercontentDao;
import com.shoesback.po.Percontent;
import com.shoesback.po.Permission;

public class PercontentBizImpl implements IPercontentBiz {
    IPercontentDao percontentDao;
	public IPercontentDao getPercontentDao() {
		return percontentDao;
	}
	public void setPercontentDao(IPercontentDao percontentDao) {
		this.percontentDao = percontentDao;
	}
	@Override
	public List<Percontent> FindByManager() {
		// TODO Auto-generated method stub
		return percontentDao.findByObject("from Percontent where permission.perid=1",new Object[]{});
	}
	@Override
	public void SavePercontent(Percontent percontent) {
		// TODO Auto-generated method stub
		percontentDao.create(percontent);
	}
	@Override
	public List<Percontent> FindByperid(int perid) {
		//根据权限id获取对应权限items
		return percontentDao.findByObject("from Percontent where permission.perid=?",new Object[]{perid});
	}
	@Override                   //权限对象                             新增权限items        删除原有权限items
	public void UpdatePercontent(Permission permission, List<String> nowper,List<String> overper) {
		// TODO Auto-generated method stub
		String hql="delete from Percontent where pcitems=? and permission.perid=?";
		for (String string : overper) {
			percontentDao.bulkUpdate(hql, new Object[]{string,permission.getPerid()});			
		}
		//新增权限items操作
		Percontent con=null;
		for (String now : nowper) {
			String hql1="from Percontent where pcitems=? and permission.perid=?";
			Percontent con1=percontentDao.findByObject(hql1, new Object[]{now,1}).get(0);
			if(con1!=null){
				con=new Percontent();
				con.setPcitems(now);
				con.setPcmenu(con1.getPcmenu());
				con.setPcremarks(con1.getPcremarks());
				con.setPcurl(con1.getPcurl());
				con.setPctnt(con1.getPctnt());
				con.setPermission(permission);
				percontentDao.create(con);
			}
		}
	}

}
