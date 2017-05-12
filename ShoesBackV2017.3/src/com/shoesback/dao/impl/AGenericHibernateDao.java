package com.shoesback.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shoesback.dao.IGenericDao;
import com.shoesback.vo.PageBean;

/**
 * @author 
 * 通用dao实现类：
 *继承反射序列化和Spring托管的HibernateDaoSupport，实现通过Dao接口
 *作用是实现通用dao接口的所有通过方法，针对不同对象的类进行操作，
 *可以通过反射来实现不同的类用一个通用dao方法实现相应操作
 */
public abstract class AGenericHibernateDao<T extends Serializable,ID extends Serializable> 
    extends HibernateDaoSupport  implements IGenericDao<T, ID> {   
	/**
	 * 反射类对象,通过空构造方法来实现反射实体类对象
	 */
	Class<T> persistentClass;		
	@SuppressWarnings("unchecked")
	public AGenericHibernateDao() {
		//                强转为当前类对象，转换为可序列化类型，                        获取java最底层类对象                     反射出第一个参数对象信息                                                
		this.persistentClass=(Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		//System.out.println("Ageneric:"+this.persistentClass);
	}
	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	@Override
	public void create(T entity) {
		//添加通用方法
		this.getHibernateTemplate().save(entity);
	}
	@Override
	public void delete(ID id) {
		//删除通用方法                             参数为要删除的实体类对象
		System.out.println("IDD "+id);
		this.getHibernateTemplate().delete(this.findById(id));	
	}
	@Override
	public void update(T entity) {
		//更新通用方法
		this.getHibernateTemplate().update(entity);
	}
	@Override
	public T findById(ID id) {
		//根据ID ，获取单个实体类对象
		return this.getHibernateTemplate().get(persistentClass, id);
	}
	@Override
	public List<T> findAll() {
		//获取全部表数据
		return this.getHibernateTemplate().loadAll(persistentClass);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByObject(String hql, Object[] param) {
		//根据hql语句与参数数组来获取相应集合数据
		return this.getHibernateTemplate().find(hql, param);
	}
	@Override
	public PageBean findByPageBean(final String hql,final Object[] param,final int currentpage,final int pageSize){
		// 获取分页显示，当前页数据
		return this.getHibernateTemplate().execute(new HibernateCallback<PageBean>() {
			//获取hibernateSessionFactory接口方法   /
			@Override
			public PageBean doInHibernate(Session session)throws HibernateException, SQLException {
				//创建pagebean对象
				PageBean pb=new PageBean();
				//通过Query对象来获取所需要页的数据
				Query qu=session.createQuery(hql);
				//赋值参数
				if(param.length>0){
					for (int i = 0; i < param.length; i++) {
						qu.setParameter(i, param[i]);
					}
				}
				//为Query对象，赋值从第几行到第几行参数，也就是最大最小页数值 2-1 10
				qu.setFirstResult((currentpage-1)*pageSize);
				qu.setMaxResults(pageSize);
				//给PageBean对象，赋值list参数
				pb.setData(qu.list());
				//获取总行数                                  
				qu=session.createQuery("select count(*) "+hql.substring(hql.toLowerCase().indexOf("from")));
				//赋值获取总行数参数
				if(param.length>0){
				   for (int j = 0; j < param.length; j++) {
					   qu.setParameter(j, param[j]);
				   }					
				}
				//Pagebean赋值总行数参数
				pb.setTotalRows(Integer.parseInt(qu.uniqueResult().toString()));
				//Pagebean赋值当前页参数
				pb.setCurrentPage(currentpage);
				//Pagebean赋值每页大小参数
				pb.setPageSize(pageSize);
				//返回pagebean对象
				return pb;
			}
		});
	}
	@Override
	public void bulkUpdate(String bulk, Object[] param) {
		//批量更新方法
		this.getHibernateTemplate().bulkUpdate(bulk,param);
		
	}
	@Override
	public Integer countByObject(final String hql,final Object[] param) {
		//统计数据量方法
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            //获取hibernateSessionFactory接口方法   
			@Override
			public Integer doInHibernate(Session session)throws HibernateException, SQLException {
				//通过HIbernateQuery方法来获取count值
				Query qu=session.createQuery(hql);
				for (int i = 0; i < param.length; i++) {
					qu.setParameter(i, param[i]);
				}
				return Integer.parseInt(qu.uniqueResult().toString());				
			}
		});
	}
}
