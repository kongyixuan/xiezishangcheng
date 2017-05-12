package com.shoesback.redis;

import redis.clients.jedis.ShardedJedis;

/*�ýӿ����ڻ�ȡredis�ͻ���ʵ����ͨ����ʵ������redis����Դ*/
public interface RedisDataSource {
	public abstract ShardedJedis getRedisClient();

	public void returnResource(ShardedJedis shardedJedis);

	public void returnResource(ShardedJedis shardedJedis, boolean broken);
}
