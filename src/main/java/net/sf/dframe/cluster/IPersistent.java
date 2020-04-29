package net.sf.dframe.cluster;


import com.hazelcast.collection.QueueStore;
import com.hazelcast.map.MapStore;
/**
 * 持久化
 * @author Dody
 *
 */
public interface IPersistent {
	

	public MapStore<?, ?> getMapStore(String name);

	public QueueStore<?> getQueueStore(String name);
	
	
}
