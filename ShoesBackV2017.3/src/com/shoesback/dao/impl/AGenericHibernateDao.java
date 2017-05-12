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
 * ͨ��daoʵ���ࣺ
 *�̳з������л���Spring�йܵ�HibernateDaoSupport��ʵ��ͨ��Dao�ӿ�
 *������ʵ��ͨ��dao�ӿڵ�����ͨ����������Բ�ͬ���������в�����
 *����ͨ��������ʵ�ֲ�ͬ������һ��ͨ��dao����ʵ����Ӧ����
 */
public abstract class AGenericHibernateDao<T extends Serializable,ID extends Serializable> 
    extends HibernateDaoSupport  implements IGenericDao<T, ID> {   
	/**
	 * ���������,ͨ���չ��췽����ʵ�ַ���ʵ�������
	 */
	Class<T> persistentClass;		
	@SuppressWarnings("unchecked")
	public AGenericHibernateDao() {
		//                ǿתΪ��ǰ�����ת��Ϊ�����л����ͣ�                        ��ȡjava��ײ������                     �������һ������������Ϣ                                                
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
		//���ͨ�÷���
		this.getHibernateTemplate().save(entity);
	}
	@Override
	public void delete(ID id) {
		//ɾ��ͨ�÷���                             ����ΪҪɾ����ʵ�������
		System.out.println("IDD "+id);
		this.getHibernateTemplate().delete(this.findById(id));	
	}
	@Override
	public void update(T entity) {
		//����ͨ�÷���
		this.getHibernateTemplate().update(entity);
	}
	@Override
	public T findById(ID id) {
		//����ID ����ȡ����ʵ�������
		return this.getHibernateTemplate().get(persistentClass, id);
	}
	@Override
	public List<T> findAll() {
		//��ȡȫ��������
		return this.getHibernateTemplate().loadAll(persistentClass);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByObject(String hql, Object[] param) {
		//����hql����������������ȡ��Ӧ��������
		return this.getHibernateTemplate().find(hql, param);
	}
	@Override
	public PageBean findByPageBean(final String hql,final Object[] param,final int currentpage,final int pageSize){
		// ��ȡ��ҳ��ʾ����ǰҳ����
		return this.getHibernateTemplate().execute(new HibernateCallback<PageBean>() {
			//��ȡhibernateSessionFactory�ӿڷ���   /
			@Override
			public PageBean doInHibernate(Session session)throws HibernateException, SQLException {
				//����pagebean����
				PageBean pb=new PageBean();
				//ͨ��Query��������ȡ����Ҫҳ������
				Query qu=session.createQuery(hql);
				//��ֵ����
				if(param.length>0){
					for (int i = 0; i < param.length; i++) {
						qu.setParameter(i, param[i]);
					}
				}
				//ΪQuery���󣬸�ֵ�ӵڼ��е��ڼ��в�����Ҳ���������Сҳ��ֵ 2-1 10
				qu.setFirstResult((currentpage-1)*pageSize);
				qu.setMaxResults(pageSize);
				//��PageBean���󣬸�ֵlist����
				pb.setData(qu.list());
				//��ȡ������                                  
				qu=session.createQuery("select count(*) "+hql.substring(hql.toLowerCase().indexOf("from")));
				//��ֵ��ȡ����������
				if(param.length>0){
				   for (int j = 0; j < param.length; j++) {
					   qu.setParameter(j, param[j]);
				   }					
				}
				//Pagebean��ֵ����������
				pb.setTotalRows(Integer.parseInt(qu.uniqueResult().toString()));
				//Pagebean��ֵ��ǰҳ����
				pb.setCurrentPage(currentpage);
				//Pagebean��ֵÿҳ��С����
				pb.setPageSize(pageSize);
				//����pagebean����
				return pb;
			}
		});
	}
	@Override
	public void bulkUpdate(String bulk, Object[] param) {
		//�������·���
		this.getHibernateTemplate().bulkUpdate(bulk,param);
		
	}
	@Override
	public Integer countByObject(final String hql,final Object[] param) {
		//ͳ������������
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            //��ȡhibernateSessionFactory�ӿڷ���   
			@Override
			public Integer doInHibernate(Session session)throws HibernateException, SQLException {
				//ͨ��HIbernateQuery��������ȡcountֵ
				Query qu=session.createQuery(hql);
				for (int i = 0; i < param.length; i++) {
					qu.setParameter(i, param[i]);
				}
				return Integer.parseInt(qu.uniqueResult().toString());				
			}
		});
	}
}
