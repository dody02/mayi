package net.sf.dframe.cluster.hazelcast.redis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.collection.QueueStore;
/**
 * 持久化
 * @author dy02
 *
 */
public class RedisQueueStore implements QueueStore<String>{

	
	private static Logger log = LoggerFactory.getLogger(RedisQueueStore.class);
	
	private RedissonClient redisson;
	
	private String name = "REDIS_QUEUE";
	
	private RMap<Long,String> map ;
	
	
	/**
	 * set the name
	 * @param db
	 * @param name
	 */
	public RedisQueueStore (RedissonClient db,String name) {
		this.redisson = db;
		if (name != null )
			this.name = name;
		map = redisson.getMap(this.name);
	}

	
	public RedisQueueStore (RedissonClient db) {
		this(db, null);
	}
	
	
	@Override
	public void store(Long key, String value) {
		try {
			map.put(key, value);
		} catch (Exception e) {
			log.error("save rediss exception",e);
		}
	}

	@Override
	public void storeAll(Map<Long, String> map) {
			try {
				this.map.putAll(map);
			} catch (Exception e) {
				log.error("batch save rediss exception",e);
			}
	}

	@Override
	public void delete(Long key) {
		try {
			map.remove(key);
		} catch (Exception e) {
			log.error("delete rediss exception",e);
		}
	}

	@Override
	public void deleteAll(Collection<Long> keys) {
//		redisson.getMap(name).fastRemove(keys);
		
		map.fastRemove(keys.toArray(new Long[0]));
	}

	@Override
	public String load(Long key) {
		String value = null;
		try {
			return map.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("load data for "+key +" error ",e);
		}
		return value;
	}

	@Override
	public Map<Long, String> loadAll(Collection<Long> keys) {
		Set<Long> setKey = new HashSet<Long>(keys);
		return map.getAll(setKey);
	}

	@Override
	public Set<Long> loadAllKeys() {
		return 	map.keySet();
	}

}
