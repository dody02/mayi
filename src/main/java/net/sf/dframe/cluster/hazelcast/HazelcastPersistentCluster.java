package net.sf.dframe.cluster.hazelcast;

import com.hazelcast.collection.IQueue;
import com.hazelcast.collection.QueueStore;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.QueueStoreConfig;
import com.hazelcast.map.IMap;
import com.hazelcast.map.MapStore;
import com.hazelcast.map.listener.MapListener;

import net.sf.dframe.cluster.IPersistent;
import net.sf.dframe.cluster.IPersistentCluster;

/**
 * 持久化集群
 * @author dy02
 *
 */
public class HazelcastPersistentCluster extends HazelcastShardingCluster implements IPersistentCluster{

	private IPersistent persistent ;
	
	private MapListener mapListener = null;
	
	
	public HazelcastPersistentCluster(Config cfg,IPersistent persistent) {
		super(cfg);
		this.persistent = persistent;
	}

	
	@Override
	public IQueue<?> getPersistentQueue(String name) {
		
		QueueConfig queueConfig = hz.getConfig().getQueueConfig(name);
		QueueStore<?> storeImplementation = persistent.getQueueStore(name);
		QueueStoreConfig queueStoreConfig = new QueueStoreConfig();
		queueStoreConfig.setEnabled(true);
		queueStoreConfig.setProperty("binary", "false");
		queueStoreConfig.setProperty("memory-limit", "0");
		queueStoreConfig.setProperty("bulk-load", "4");
		queueStoreConfig.setStoreImplementation(storeImplementation);
		queueConfig.setQueueStoreConfig(queueStoreConfig);
		return hz.getQueue(name);
	}

	@Override
	public IMap<?, ?> getPersistentMap(String name) {
		
		MapStoreConfig mapStoreConfig = new MapStoreConfig();
		mapStoreConfig.setEnabled(true);
		mapStoreConfig.setWriteDelaySeconds(0);
		mapStoreConfig.setInitialLoadMode(MapStoreConfig.InitialLoadMode.EAGER);
		MapStore<?, ?>  store = persistent.getMapStore(name);
		mapStoreConfig.setImplementation(store);
		hz.getConfig().getMapConfig(name).setMapStoreConfig(mapStoreConfig);
		
		IMap<?, ?>  map =  hz.getMap(name);
		if (mapListener != null) {
			map.addEntryListener(mapListener, true);
		}
		
		return map;
	}


	public IPersistent getPersistent() {
		return persistent;
	}


	public void setPersistent(IPersistent persistent) {
		this.persistent = persistent;
	}


	public MapListener getMapListener() {
		return mapListener;
	}

	/**
	 * Must set MapListener before invoke getPersistentMap
	 * @param listener
	 */
	public void setMapListener(MapListener listener) {
		this.mapListener = listener;
	}

}
