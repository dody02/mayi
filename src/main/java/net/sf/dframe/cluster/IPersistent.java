package net.sf.dframe.cluster;


import com.hazelcast.collection.QueueStore;
import com.hazelcast.map.MapStore;
/**
 * 持久化
 * @author Dody
 *
 */
public interface IPersistent {

	/**
	 * getMapStore
	 * @param name
	 * @return MapStore
	 */
	public MapStore<?, ?> getMapStore(String name);

	/**
	 * getQueueStore
	 * @param name
	 * @return QueueStore
	 */
	public QueueStore<?> getQueueStore(String name);
	
	
}
