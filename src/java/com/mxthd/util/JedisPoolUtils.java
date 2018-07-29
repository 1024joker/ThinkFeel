package com.mxthd.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 执行redis的工具类
 */
public class JedisPoolUtils {

	
	static JedisPool pool = null;

	final static String HOST= "123.56.20.58";
	
	final static int PROT = 6339;
	
	
	public static void main(String[] args) {
		
//	System.out.println(getjedis().ping());
//		
		System.out.println(getjedis().get("k2"));
		
		System.out.println(getjedis().set("k2", "v2"));

		
		getjedis().expire("k2", 20);//这是给这个可以设置20 的时间
		System.out.println("时间："+getjedis().ttl("k2"));
//		exists 是否存在
	}
	
	 public static Jedis getjedis(){
		
	    if (pool==null) {
			
	    	 JedisPoolConfig config = new JedisPoolConfig();
	    	 
	    	 config.setMaxActive(500); 
	         // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
	         // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
	    	    config.setMaxActive(500); 
	         // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
	            config.setMaxIdle(5);  
	         // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
				config.setMaxWait(10000l);
	         // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
	           config.setTestOnBorrow(true);  
	         // pool = new JedisPool(config, "192.168.0.121", 6379, 100000);  
	            pool = new JedisPool(config, HOST, PROT, 10000);
		}
	    return pool.getResource();
	 }	    
}