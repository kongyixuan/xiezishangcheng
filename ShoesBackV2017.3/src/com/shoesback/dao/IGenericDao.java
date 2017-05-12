package com.shoesback.dao;

import java.io.Serializable;
import java.util.List;

import com.shoesback.vo.PageBean;

/** * 
 * @author kzz
 *ͨ��dao�ӿڣ�
 *�̳з������л�
 *�����ǲ�ͬ���������в���������ͨ��������ʵ�ֲ�ͬ������һ��ͨ��dao����ʵ����Ӧ����
 *                             ����ʵ�������                      ����id���ͣ�����ΪInteger����    
 */ 
public interface IGenericDao<T extends Serializable,ID extends Serializable> {
   //��Ӳ���,����mysql���ݿ�����������������������ʵ�������������id������ֵ
   public void create(T entity);
   //ɾ������
   public void delete(ID id);
   //���²����������Ǹ��������Ըö�����Ϣ���и��²�����������ʵ�������������id������ֵ
   public void update(T entity);
   //����ID��ȡ��������
   public T findById(ID id);
   //��ȡȫ��������
   public List<T> findAll();
   //���������Ͳ�����ȡ���ֽ��
   public List<T> findByObject(String hql,Object[] param);
   //��ҳ������ȡ����Ҫ��ǰҳ������Ϣ               hql���                           hql������                                ��ǰҳ                                             ÿҳ��С
   public PageBean findByPageBean(final String hql,final Object[] param,final int currentpage,final int pageSize);
 
   //�������²���
   public void bulkUpdate(final String bulk,final Object[] param);
   //��ȡ��ʵ��������
   public Integer countByObject(final String hql,final Object[] param);
}
