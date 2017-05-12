package com.shoesback.dao;

import java.io.Serializable;
import java.util.List;

import com.shoesback.vo.PageBean;

/** * 
 * @author kzz
 *通用dao接口：
 *继承反射序列化
 *作用是不同对象的类进行操作，可以通过反射来实现不同的类用一个通用dao方法实现相应操作
 *                             反射实体类对象                      反射id类型，可以为Integer类型    
 */ 
public interface IGenericDao<T extends Serializable,ID extends Serializable> {
   //添加操作,由于mysql数据库主键都是自增长，因此添加实体类对象中主键id不能有值
   public void create(T entity);
   //删除操作
   public void delete(ID id);
   //更新操作，由于是根据主键对该对象信息进行更新操作，因此添加实体类对象中主键id必须有值
   public void update(T entity);
   //根据ID获取单个对象
   public T findById(ID id);
   //获取全部对象结合
   public List<T> findAll();
   //根据条件和参数获取部分结合
   public List<T> findByObject(String hql,Object[] param);
   //分页操作获取所需要当前页对象信息               hql语句                           hql语句参数                                当前页                                             每页大小
   public PageBean findByPageBean(final String hql,final Object[] param,final int currentpage,final int pageSize);
 
   //批量更新操作
   public void bulkUpdate(final String bulk,final Object[] param);
   //获取该实体类数量
   public Integer countByObject(final String hql,final Object[] param);
}
