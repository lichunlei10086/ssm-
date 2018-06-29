package test;

import redis.clients.jedis.Jedis;

public class Producer  extends Thread{
	
    public static final String MESSAGE_KEY = "message:queue";
    private Jedis jedis;
    private String producerName;
    private volatile int count;

	
	public Producer(String name){
		this.producerName=name;
		init();
	}
    private void init() {
        jedis = MyJedisFactory.getLocalJedis();
    }

	
	
}


