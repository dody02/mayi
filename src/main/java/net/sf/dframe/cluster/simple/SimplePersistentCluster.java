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

    /**
     * 构建一个默认配置的集群，并指定监听器
     * @param listener 监听器
     */
    public SimplePersistentCluster(IMListener listener){
        this("defalutCluster",new SimpleConfig(),listener);
    }

    /**
     * 构建一个默认配置的集群
     */
    public SimplePersistentCluster(){
        this("defalutCluster",new SimpleConfig());
    }

    /**
     * 通过指定集群名，配置信息，构建一个集群
     * @param clusterName 集群名
     * @param config 配置信息
     */
    public SimplePersistentCluster(String clusterName, SimpleConfig config){
        DataBase db = DataBase.getInstance(clusterName,"./", "root", "root");
        hpc = new HazelcastPersistentCluster(config.getConfig(), new H2Persistent(db));
    }

    /**
     * 通过指定集群名，配置信息，集群监听器，构建一个集群
     * @param clusterName 集群名
     * @param config 集群配置信息
     * @param listener 集群监听器
     */
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
