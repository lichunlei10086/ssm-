package ConfigUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisCache implements Cache{
	 private static Logger logger = LoggerFactory.getLogger(RedisCache.class);

	    private String cacheId;
	    /**
	     * ��д������Ϊ������д����������������⣬������д�����⣬������jvm�Լ����Ƶģ���ֻҪ�Ϻ���Ӧ�������ɡ������Ĵ���ֻ�����ݣ����Ժܶ���
	     * ͬʱ����������ͬʱд���Ǿ��϶����������Ĵ����޸����ݣ�ֻ����һ������д���Ҳ���ͬʱ��ȡ���Ǿ���д������֮������ʱ���϶�����д��ʱ����
	     * д����
	     */
	    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
	    private final Lock read = readWriteLock.readLock();
	    private final Lock write = readWriteLock.writeLock();

	    public RedisCache(String cacheId) {
	        if (cacheId == null) {
	            throw new IllegalArgumentException("Cache instances require an ID");
	        }
	        this.cacheId = ConfigUtils.key + "." + cacheId;
	        logger.info("��ѯ������뻺���Ӧ�Ļ���ռ����ɵ�����cacheId: " + this.cacheId);

	        if (ConfigUtils.redisSwitch) {
	            JedisUtils.getInstence();
	        }
	    }

	    public String getId() {
	        return cacheId;
	    }

	    public void putObject(Object key, Object value) {
	        // TODO �ӻ�����д���ݣ���д���������������
	        logger.info("NTSRedisCache putObject=" + cacheId);
	        if (ConfigUtils.redisSwitch) {
	            write.lock();
	            try {
	                JedisUtils.put(cacheId, key, value);
	            } finally {
	                write.unlock();
	            }

	        }
	    }

	    public Object getObject(Object key) {
	        // TODO �ӻ����ж����ݣ��ö���������������д
	        logger.info("�ӻ���cacheId="+cacheId+"��������key="+key+"��Ӧ��value");
	        if (ConfigUtils.redisSwitch) {
	            read.lock();
	            try {
	                return JedisUtils.get(cacheId, key);
	            } finally {
	                read.unlock();
	            }
	        }
	        return null;
	    }

	    public Object removeObject(Object key) {
	        // TODO �ӻ����иĶ����ݣ���д������������������Ķ��������ͷ�д����
	        logger.info("NTSRedisCache clear =" + cacheId);
	        if (ConfigUtils.redisSwitch) {
	            write.lock();
	            try {
	                return JedisUtils.remove(cacheId, key);
	            } finally {
	                write.unlock();
	            }
	        }
	        return null;
	    }

	    public void clear() {
	        // TODO  �ӻ����иĶ����ݣ���д������������������Ķ��������ͷ�д����
	        logger.info("NTSRedisCache clear =" + cacheId);
	        if (ConfigUtils.redisSwitch) {
	            write.lock();
	            try {
	                JedisUtils.removeAll(cacheId);
	            } finally {
	                write.unlock();
	            }
	        }
	    }

	    public int getSize() {
	        // TODO Auto-generated method stub
	        logger.info("NTSRedisCache clear =" + cacheId);
	        if (ConfigUtils.redisSwitch) {
	            read.lock();
	            try {
	                return JedisUtils.getSize(cacheId);
	            } finally {
	                read.unlock();
	            }
	        }
	        return -1;
	    }

	    public ReadWriteLock getReadWriteLock() {
	        return readWriteLock;
	    }
}
