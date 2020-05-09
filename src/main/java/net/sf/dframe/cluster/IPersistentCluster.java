package net.sf.dframe.cluster;

import com.hazelcast.collection.IQueue;
import com.hazelcast.map.IMap;
/**
 * 
 * @author dy02
 *
 */
public interface IPersistentCluster extends ICluster{
	
	public IQueue<?> getPersistentQueue(String name);
	
	public IMap<?,?> getPersistentMap(String name);
	
	public IPersistent getPersistent();
	
	
}
