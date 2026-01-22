package net.sf.dframe.cluster.hazelcast.redis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.map.MapStore;

/**
 * store for map
 * @author dy02
 *
 */
public class RedisMapStore implements MapStore<String, String>  {

	private static Logger log = LoggerFactory.getLogger(RedisMapStore.class);
	
	
	
	private RedissonClient redis;
	
	private String name = "REDIS_MAP";
	
	
	private RMap<String,String> map; 
	
	/**
	 * set the name
	 * @param db
	 * @param name
	 */
	public RedisMapStore (RedissonClient db,String name) {
		this.redis = db;
		if (name != null)
			this.name = name;
		map = redis.getMap(this.name);
	}
	
	
	/**
	 * set the name
	 * @param db
	 */
	public RedisMapStore (RedissonClient db) {
		this(db,null);
	}

	
	
	@Override
	public String load(String key) {
		
		try {
			return map.get(key);
		} catch (Exception e) {
			log.error("load data for "+key +" error ",e);
		}
		return null;
		
	}

	@Override
	public Map<String, String> loadAll(Collection<String> keys) {
		Set<String> setKey = new HashSet<String>(keys);
		return map.getAll(setKey);
	}

	@Override
	public Iterable<String> loadAllKeys() {
		try {
			final Set<String> keyset = map.keySet();
			
			return new Iterable<String>() {

				@Override
				public Iterator<String> iterator() {
					return keyset.iterator();
				}};
		} catch (Exception e) {
			log.error("load ALL keys exception ",e);
		}
		return null;
	}

	@Override
	public void store(String key, String value) {
		try {
			map.put(key, value);
		} catch (Exception e) {
			log.error("store exception",e);
		}
	}

	@Override
	public void storeAll(Map<String, String> map) {
		map.putAll(map);
	}

	@Override
	public void delete(String key) {
		try {
			map.remove(key);
		} catch (Exception e) {
			log.error("delete h2 exception",e);
		}
	}

	@Override
	public void deleteAll(Collection<String> keys) {
		map.clear();
	}

	

}
