package com.shoesback.biz.impl.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.ShardedJedisPool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesback.biz.impl.ShoesBizImpl;
import com.shoesback.dao.IAdminsDao;
import com.shoesback.dao.impl.AdminsDaoImpl;
import com.shoesback.dao.impl.ShoesDaoImpl;
import com.shoesback.po.Brands;
import com.shoesback.po.OrderShoes;
import com.shoesback.po.Shoes;
import com.shoesback.redis.RedisClientTemplate;
import com.shoesback.redis.RedisDataSourceImpl;

public class BaseTest {
	private static final ObjectMapper mapper=new ObjectMapper(); 
	private ApplicationContext ac;
	/*这样需要在配置文件进行扫描*/
	@Autowired
	ShardedJedisPool shardedJedisPool;
	ShoesDaoImpl shoesDao;
	ShoesBizImpl shoesBiz;
	public BaseTest(){
		ac = new  ClassPathXmlApplicationContext("applicationContext.xml");
		//ac = new  ClassPathXmlApplicationContext("spring-redis.xml");
		//ac = new  ClassPathXmlApplicationContext("spring-redis-config.xml");
		System.out.println("读取对象"+ac);

	}
	public Object getBean(String name){
		return ac.getBean(name);
	}
	public static void main(String[] args) {
		IAdminsDao adminsDao= (AdminsDaoImpl)new BaseTest().getBean("adminsDao");
		//System.out.println(new BaseTest().getBean("jedisPoolConfig"));
		System.out.println(adminsDao);
		
	}
	@Test
	public void testShoesDao(){
		shoesDao=(ShoesDaoImpl)new BaseTest().getBean("shoesDao");
		shoesBiz=(ShoesBizImpl)new BaseTest().getBean("shoesBiz");
		List<Shoes>list=shoesBiz.FindAllShoes();
		System.out.print(list.size());
	}
	@Test
	public void testConfig(){
		ShardedJedisPool shardedJedisPool=(ShardedJedisPool)new BaseTest().getBean("shardedJedisPool");
	
		System.out.print(shardedJedisPool);
		RedisDataSourceImpl redisDataSource=(RedisDataSourceImpl)new BaseTest().getBean("redisDataSource");
		System.out.print(redisDataSource);
		RedisClientTemplate redisClientTemplate=(RedisClientTemplate)new BaseTest().getBean("redisClientTemplate");
		System.out.print(redisClientTemplate);
		redisClientTemplate.setKeyValueTime("kongTest", "test1", 60);
		List<Shoes> list=new ArrayList<Shoes>();
		Shoes shoes=new Shoes();
		shoes.setScolor("brake");
		shoes.setSdetail("测试");
		shoes.setSgender("男");
		Brands brands=new Brands();
		brands.setBname("361");
		brands.setBid(1000);
		shoes.setBrands(brands);
	/*	Set<Object> orderShoeses=new HashSet<Object>();
		OrderShoes oreOrderShoe=new OrderShoes();
	     oreOrderShoe.setShoes(shoes);
		orderShoeses.add(oreOrderShoe);
		shoes.setOrderShoeses(orderShoeses);*/
		list.add(shoes);
		try {
			/*向redis中设置值*/
		String valueList=mapper.writeValueAsString(list);
	 redisClientTemplate.setKeyValueTime("valueList20170511", valueList,60*60*60);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(redisClientTemplate.getOne("kongTest"));
		boolean flag=false;
		String key="valueList20170511";
		List<Shoes> listShoes=null;
		/*判断是否存在key*/
		flag = redisClientTemplate.exsistKey(key);
		if (flag) {
			String allShoes = redisClientTemplate.getOne(key);
			try {
				/*将获取的value值转变成list集合*/
			 listShoes = mapper.readValue(allShoes,new TypeReference<List<Shoes>>(){});
			 System.out.print(listShoes.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		}
		
	}
	}

