package net.sf.dframe.cluster;

import com.hazelcast.collection.IQueue;
import com.hazelcast.map.IMap;
/**
 * 持久化集群接口
 * @author dy02
 *
 */
public interface IPersistentCluster extends ICluster{
	/**
	 * getPersistentQueue
	 * @param name
	 * @return IQueue
	 */
	public IQueue<?> getPersistentQueue(String name);

	/**
	 * getPersistentMap
	 * @param name
	 * @return IMap
	 */
	public IMap<?,?> getPersistentMap(String name);

	/**
	 * getPersistent
	 * @return IPersistent
	 */
	public IPersistent getPersistent();
	
	
}
