package net.sf.dframe.cluster.hazelcast.h2;

import com.hazelcast.collection.QueueStore;
import com.hazelcast.map.MapStore;

import net.sf.dframe.cluster.IPersistent;
/**
 * H2 Persistent
 * @author Dody
 *
 */
public class H2Persistent implements IPersistent{
	
	
	private DataBase db ;
	
	public H2Persistent (DataBase db) {
		this.db = db;
	}
	
	@Override
	public QueueStore<?> getQueueStore(String name) {
		return new H2QueueStore(db,name);
	}

	@Override
	public MapStore<?, ?> getMapStore(String name) {
		return new H2MapStore(db,name);
	}

}
