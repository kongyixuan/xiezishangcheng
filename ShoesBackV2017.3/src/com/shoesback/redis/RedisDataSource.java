package com.shoesback.redis;

import redis.clients.jedis.ShardedJedis;

/*该接口用于获取redis客户端实例，通过该实例操作redis数据源*/
public interface RedisDataSource {
	public abstract ShardedJedis getRedisClient();

	public void returnResource(ShardedJedis shardedJedis);

	public void returnResource(ShardedJedis shardedJedis, boolean broken);
}
