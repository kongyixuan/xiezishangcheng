package com.shoesback.biz.impl;

import java.io.StringWriter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesback.biz.IShoesBiz;
import com.shoesback.dao.IShoesDao;
import com.shoesback.dao.IShoesizesDao;
import com.shoesback.po.Shoes;
import com.shoesback.po.Shoesizes;
import com.shoesback.redis.RedisClientTemplate;
import com.shoesback.vo.PageBean;

public class ShoesBizImpl implements IShoesBiz {
    IShoesDao shoesDao;
    IShoesizesDao shoesizesDao;
    @Autowired
    private RedisClientTemplate redisClientTemplate;
    
    public RedisClientTemplate getRedisClientTemplate() {
		return redisClientTemplate;
	}
	public void setRedisClientTemplate(RedisClientTemplate redisClientTemplate) {
		this.redisClientTemplate = redisClientTemplate;
	}
	public static ObjectMapper getMapper() {
		return mapper;
	}
	private static final ObjectMapper mapper=new ObjectMapper(); 
	public IShoesizesDao getShoesizesDao() {
		return shoesizesDao;
	}
	public void setShoesizesDao(IShoesizesDao shoesizesDao) {
		this.shoesizesDao = shoesizesDao;
	}
	public IShoesDao getShoesDao() {
		return shoesDao;
	}
	public void setShoesDao(IShoesDao shoesDao) {
		this.shoesDao = shoesDao;
	}	   
	@Override
	public PageBean FindShoesByPage(int currentpage, int pagesize) {
		// TODO Auto-generated method stub
		return shoesDao.findByPageBean("from Shoes", new Object[]{}, currentpage, pagesize);
	}
	@Override
	public void DeleteShoe(int isdelete, int sid) {
		// TODO Auto-generated method stub
		String hql="update Shoes set sdelete=? where sid=?";
		shoesDao.bulkUpdate(hql, new Object[]{isdelete,sid});
	}
	@Override
	public List<Shoes> FindAllShoes() {
		// TODO Auto-generated method stub
		String key = "allshoes";
		List<Shoes> listShoes = null;
		String shoesAllString =null;
		boolean flag = false;
		try {
			//redisClientTemplate=new RedisClientTemplate();
			flag = redisClientTemplate.exsistKey(key);
			if (flag) {
				String allShoes = redisClientTemplate.getOne(key);
				listShoes = mapper.readValue(allShoes,
						new TypeReference<List<Shoes>>() {
						});
				return listShoes;
			} else {
				listShoes = shoesDao.findAll();
				/* 将value存入到redis */
				 //StringWriter w = new StringWriter(); 
				//mapper.writeValue(w, listShoes);
				//shoesAllString =w.toString();
				
				shoesAllString = mapper.writeValueAsString(listShoes);
			
				redisClientTemplate.setKeyValueTime(key, shoesAllString, 60*60*60);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listShoes;
	}
	@Override
	public void BatchDeleteShoes(String hql) {
		// TODO Auto-generated method stub
		hql="update Shoes set "+hql;
		shoesDao.bulkUpdate(hql, new Object[]{});
	}
	@Override
	public PageBean FuzzySearchShoe(String params, int currentpage, int pagesize) {
		// TODO Auto-generated method stub
		String hql="from Shoes where concat (sname,snum,sproducer) like ? ";
		return shoesDao.findByPageBean(hql, new Object[]{"%"+params+"%"}, currentpage, pagesize);
	}
	@Override
	public Shoes Findbysid(int sid) {
		// TODO Auto-generated method stub
		return shoesDao.findById(sid);
	}
	@Override
	public void UpdateShoes(Shoes shoe) {
		// TODO Auto-generated method stub
		shoesDao.update(shoe);
	}
	@Override
	public Shoes DeleteImage(String simage, int sid) {
		//根据sid获取鞋单个对象
		Shoes shoe=Findbysid(sid);
		//删除该鞋图片相应图片名
		shoe.setSimage(shoe.getSimage().replaceAll(simage, ""));
		//更新鞋对象
		shoesDao.update(shoe);
		return shoe;
	}
	@Override
	public Shoes SaveShoes(Shoes shoe) {
		// TODO Auto-generated method stub
		String hql="from Shoes where sname=?";
		List<Shoes> lstShoes=shoesDao.findByObject(hql, new Object[]{shoe.getSname()});
		//判断该鞋是否已经注册添加过
		if(lstShoes.size()<1){
			//证明集合为空，没有注册添加过该鞋信息,添加保存该鞋信息
			shoesDao.create(shoe);
			//返回该鞋对象
			return shoesDao.findByObject(hql, new Object[]{shoe.getSname()}).get(0);
		}else{
			//证明改鞋已经添加过
		    return null;
		}
	}
	@Override
	public List<Shoes> FuzzySearchShoe(String fuzzy) {
		// TODO Auto-generated method stub
		String hql="from Shoes where concat (sname,snum,sproducer) like ? ";
		return shoesDao.findByObject(hql, new Object[]{"%"+fuzzy+"%"});		
	}
	@Override
	public boolean ImportShoes(List<Shoes> lstShoes,List<Shoesizes> lstShoeszies) {
		for (Shoes shoes : lstShoes) {
			shoesDao.create(shoes);
		}
		for (Shoesizes shoesizes : lstShoeszies) {
			shoesizesDao.create(shoesizes);
		}
		return true;
	}
	@Override
	public List<Shoes> FindByOrders() {
		String hql="from Shoes where stimessold <> 0";
		
		return shoesDao.findByObject(hql, new Object[]{});		
	}
	@Override
	public PageBean FindNoSecondKillsByPage(int currentpage, int pagesize) {
		// TODO Auto-generated method stub
		return shoesDao.findByPageBean("from Shoes where sdelete <> 7 and sdelete <> 1 order by sid",new Object[]{}, currentpage, pagesize);
	}
	@Override
	public void UpdateSecondKills(int sid) {
		// TODO Auto-generated method stub
		shoesDao.bulkUpdate("update Shoes set sdelete=7 where sid=?",new Object[]{sid});
	}

}
