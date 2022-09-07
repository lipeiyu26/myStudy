package com.lpy.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication

public class RedisApplication {
	@Value("${spring.redis.cluster.nodes}")
	private String nodes;
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}
	@Bean
	public JedisCluster getJedisCluster(){
		Set<HostAndPort> redisSet = new HashSet<>();
		String[] nodeStrs = nodes.split(",");
		for (String nodeStr : nodeStrs) {
			String[] split = nodeStr.split(":");
			HostAndPort hostAndPort = new HostAndPort(split[0], Integer.valueOf(split[1]));
			redisSet.add(hostAndPort);
		}
		JedisCluster jedisCluster = new JedisCluster(redisSet);
		return jedisCluster;
	}

}

