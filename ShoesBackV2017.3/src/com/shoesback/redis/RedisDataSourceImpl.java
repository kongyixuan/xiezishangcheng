package com.shoesback.redis;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisDataSourceImpl implements RedisDataSource {
@Autowired
private ShardedJedisPool shardedJedisPool;

public ShardedJedisPool getShardedJedisPool() {
	return shardedJedisPool;
}
public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
	this.shardedJedisPool = shardedJedisPool;
}
	/*获取客户端*/
	@Override
	public ShardedJedis getRedisClient() {
		try {
			ShardedJedis shardedJedis=shardedJedisPool.getResource();
			return shardedJedis;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
/*将资源返回给pool*/
	@SuppressWarnings("deprecation")
	@Override
	public void returnResource(ShardedJedis shardedJedis) {
	shardedJedisPool.returnResource(shardedJedis);

	}
/*出现异常后资源返回给pool*/
	@SuppressWarnings("deprecation")
	@Override
	public void returnResource(ShardedJedis resource, boolean broken) {
		if(broken){
			shardedJedisPool.returnBrokenResource(resource);
		}
		

	}

}
