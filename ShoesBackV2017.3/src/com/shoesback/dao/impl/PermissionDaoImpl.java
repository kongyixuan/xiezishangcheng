package com.shoesback.dao.impl;


import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.shoesback.dao.IPermissionDao;
import com.shoesback.po.Permission;

public class PermissionDaoImpl extends
		AGenericHibernateDao<Permission, Integer> implements IPermissionDao {

	@Override
	public Integer SavePermission(final Permission permission) {
		// TODO Auto-generated method stub		
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				//利用Hibernate本身query接口添加权限
				Query query=session.createSQLQuery
("insert into Permission values(null,'"+permission.getPername()+"','"+permission.getPercont()+"','"+permission.getPeremarks()+"')").addEntity(Permission.class);
				query.executeUpdate();
				return 1;
			}
		});
	}

	@Override
	public Integer UpdatePermission(final Permission permission) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				//利用Hibernate本身query接口添加权限
				Query query=session.createSQLQuery
("update Permission set pername='"+permission.getPername()+"',percont='"+permission.getPercont()+"',peremarks='"+permission.getPeremarks()+"' where perid="+permission.getPerid())
                .addEntity(Permission.class);
				query.executeUpdate();
				return 1;
			}
		});
	}


}
