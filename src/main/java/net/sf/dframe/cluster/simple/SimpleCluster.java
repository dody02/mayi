package net.sf.dframe.cluster.simple;

import com.hazelcast.core.HazelcastInstance;
import net.sf.dframe.cluster.IMListener;
import net.sf.dframe.cluster.hazelcast.HazelcastShardingCluster;

/**
 * 非持久化集群
 */
public class SimpleCluster {

    HazelcastShardingCluster hsc = null;

    public SimpleCluster (){
        this(new SimpleConfig());
    }

    public SimpleCluster (IMListener listener){
        this(new SimpleConfig());
        hsc.setClusterMemberListener(listener);
    }

    public SimpleCluster(SimpleConfig sc){
        hsc= new HazelcastShardingCluster(sc.getConfig());
    }

    public SimpleCluster(SimpleConfig sc,IMListener listener){
        hsc= new HazelcastShardingCluster(sc.getConfig());
        hsc.setClusterMemberListener(listener);
    }

    public HazelcastInstance getHz(){
        return hsc.getHz();
    }

    public void shutdown(){
        hsc.shutdown();
    }

}
