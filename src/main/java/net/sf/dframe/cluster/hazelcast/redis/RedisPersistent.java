package net.sf.dframe.cluster.hazelcast.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
//import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import com.hazelcast.collection.QueueStore;
import com.hazelcast.map.MapStore;

import net.sf.dframe.cluster.IPersistent;
/**
 * Redis Persistent
 * @author Dody
 *
 */
public class RedisPersistent implements IPersistent{
	
	private Config config;
	
	private RedissonClient redisson = null;
	
	public RedisPersistent (String url,String db,String username,String password) {
		config = new Config();
//		config.setCodec(new StringCodec());
		SingleServerConfig singleConfig = config.useSingleServer();
		singleConfig.setAddress(url);
		if (username != null && !username.isEmpty())
			singleConfig.setUsername(username);
		if (password != null && !password.isEmpty())
			singleConfig.setPassword(password);
		if ( db != null && !db.isEmpty())
			singleConfig.setDatabase(Integer.valueOf(db));
		
		redisson = Redisson.create(config);
	}
	
	@Override
	public QueueStore<?> getQueueStore(String name) {
		return new RedisQueueStore(redisson,name);
	}

	@Override
	public MapStore<?, ?> getMapStore(String name) {
		return new RedisMapStore(redisson,name);
	}

}
