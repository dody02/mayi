package net.sf.dframe.cluster.simple;

import com.hazelcast.collection.IQueue;
import com.hazelcast.map.IMap;
import net.sf.dframe.cluster.IMListener;
import net.sf.dframe.cluster.IPersistent;
import net.sf.dframe.cluster.hazelcast.HazelcastPersistentCluster;
import net.sf.dframe.cluster.hazelcast.h2.DataBase;
import net.sf.dframe.cluster.hazelcast.h2.H2Persistent;

/**
 * 快速构建简单持久化集群
 */
public class SimplePersistentCluster {

    HazelcastPersistentCluster hpc = null;

    public SimplePersistentCluster(IMListener listener){
        this("defalutCluster",new SimpleConfig(),listener);
    }

    public SimplePersistentCluster(){
        this("defalutCluster",new SimpleConfig());
    }
    public SimplePersistentCluster(String clusterName, SimpleConfig config){
        DataBase db = DataBase.getInstance(clusterName,"./", "root", "root");
        hpc = new HazelcastPersistentCluster(config.getConfig(), new H2Persistent(db));
    }

    public SimplePersistentCluster(String clusterName, SimpleConfig config, IMListener listener){
        DataBase db = DataBase.getInstance(clusterName,"./", "root", "root");
        hpc = new HazelcastPersistentCluster(config.getConfig(), new H2Persistent(db));
        hpc.setClusterMemberListener(listener);
    }


    public IQueue<?> getPersistentQueue(String name){
        return hpc.getPersistentQueue(name);
    }

    public IMap<?, ?> getPersistentMap(String name){
        return hpc.getPersistentMap(name);
    }

    public IPersistent getPersistent(){
        return hpc.getPersistent();
    }

    public void setPersistent(IPersistent persistent){
        hpc.setPersistent(persistent);
    }

    public void shutdown(){
        hpc.shutdown();
    }
}
