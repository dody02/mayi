package net.sf.dframe.cluster.hazelcast.mysql;

import com.hazelcast.collection.QueueStore;
import com.hazelcast.map.MapStore;

import net.sf.dframe.cluster.IPersistent;
/**
 * H2 Persistent
 * @author Dody
 *
 */
public class MysqlPersistent implements IPersistent{
	
	
	private MysqlDataBase db ;
	
	public MysqlPersistent (MysqlDataBase db) {
		this.db = db;
	}
	
	@Override
	public QueueStore<?> getQueueStore(String name) {
		return new MysqlQueueStore(db,name);
	}

	@Override
	public MapStore<?, ?> getMapStore(String name) {
		return new MysqlMapStore(db,name);
	}

}
