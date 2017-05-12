package com.shoesback.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;

public class RedisClientTemplate {
@Autowired
private RedisDataSource redisDataSource;

public RedisDataSource getRedisDataSource() {
	return redisDataSource;
}
public void setRedisDataSource(RedisDataSource redisDataSource) {
	this.redisDataSource = redisDataSource;
}
/*������ȡ */
public String setOne(String key,String value) {
	String result=null;
	ShardedJedis shardedJedis=redisDataSource.getRedisClient();
	if(null==shardedJedis){
		return result;
	}
	boolean broken=false;
	try {
result=shardedJedis.set(key, value);
	} catch (Exception e) {
	broken=true;
	}
	finally{
		redisDataSource.returnResource(shardedJedis, broken);
	}
	return result;
}
/*ͨ������key��ȡֵ*/
public String getOne(String key){
	String result=null;
	ShardedJedis shardedJedis=redisDataSource.getRedisClient();
	if(null==shardedJedis){
		return result;
	}
	/*�쳣�����ʶ��*/
	boolean broken=false;
	try {
		result=shardedJedis.get(key);
	} catch (Exception e) {
		broken=true;
		// TODO: handle exception
	}
	finally{
		redisDataSource.returnResource(shardedJedis, broken);
	}
	return result;
}
/*�ж�һ��key�Ƿ����*/
public boolean exsistKey(String key){
	Boolean result=false;
	ShardedJedis shardedJedis=redisDataSource.getRedisClient();
	if(null==shardedJedis){
		return result;
	}
	boolean broken=false;
	try {
		result=shardedJedis.exists(key);
	} catch (Exception e) {
		broken=true;
		// TODO: handle exception
	}
	finally{
		redisDataSource.returnResource(shardedJedis, broken);
	}
	return result;
}
/*����key��Ӧ�� ֵ��type��*/
public String valueType(String key){
	String result=null;
	ShardedJedis shardedJedis=redisDataSource.getRedisClient();
	if(null==shardedJedis){
		return result;
	}
	boolean broken=false;
	try {
		result=shardedJedis.type(key);
	} catch (Exception e) {
		broken=true;
		// TODO: handle exception
	}
	finally{
		redisDataSource.returnResource(shardedJedis, broken);
	}
	return result;
}
/*��ĳ��ʱ���ʧЧ*/
public long unAble(String key,long unixTime){
	Long result=null;
	ShardedJedis shardedJedis=redisDataSource.getRedisClient();
	if(null==shardedJedis){
		return result;
	}
	boolean broken=false;
	try {
		result=shardedJedis.expireAt(key, unixTime);
	} catch (Exception e) {
		broken=true;
	}finally{
		redisDataSource.returnResource(shardedJedis, broken);
	}
	return result;
}
/*Key�����ھͽ�value��key����*/
public Long setValueToKey(String key,String value){
	Long result=null;
	ShardedJedis shardedJedis=redisDataSource.getRedisClient();
	if(null==shardedJedis){
		return result;
	}
	boolean broken=false;
	try {
		if("".equals(key)||key.length()<0){
		result=shardedJedis.setnx(key, value);
		}
	} catch (Exception e) {
		broken=true;
		// TODO: handle exception
	}finally{
		redisDataSource.returnResource(shardedJedis, broken);
	}
	return result;
}
/*ɾ��key*/
public Long delKey(String key){
	Long result=null;
	ShardedJedis shardedJedis=redisDataSource.getRedisClient();
	if(null==shardedJedis){
		return result;
	}
	boolean broken=false;
	try {
		result=shardedJedis.del(key);
	} catch (Exception e) {
		broken=true;
		// TODO: handle exception
	}
	finally{
		redisDataSource.returnResource(shardedJedis, broken);
	}
	return result;
}
public String setMore(byte[] key,byte[] value){
	String result=null;
	ShardedJedis shardedJedis =redisDataSource.getRedisClient();
	if(null==shardedJedis){
		return result;
	}
	boolean broken=false;
	try {
		result=shardedJedis.set(key, value);		
	} catch (Exception e) {
		broken=true;
		// TODO: handle exception
	}
	finally{
		redisDataSource.returnResource(shardedJedis, broken);
	}
	return result;
}
public byte[]  getMore(byte[] key) {
	byte[] value=null;
	ShardedJedis shardedJedis=redisDataSource.getRedisClient();
	if(null==shardedJedis){
		return value;
	}
	boolean broken=false;
	try {
		value=shardedJedis.get(key);
	} catch (Exception e) {
		broken=true;
		// TODO: handle exception
	}finally{
		redisDataSource.returnResource(shardedJedis, broken);
	}
	return value;
}
/*��ֵ value ������ key ������ key ������ʱ����Ϊ seconds (����Ϊ��λ) */
public String setKeyValueTime(String key,String value,int seconds) {
	String reslut=null;
	
	ShardedJedis shardedJedis=redisDataSource.getRedisClient();
	if(null==shardedJedis){
		return reslut;
	}
	boolean broken=false;
	try {
		shardedJedis.setex(key, seconds, value);
	} catch (Exception e) {
		broken=true;
		// TODO: handle exception
	}finally{
		redisDataSource.returnResource(shardedJedis, broken);
	}
	return reslut;
}
}
