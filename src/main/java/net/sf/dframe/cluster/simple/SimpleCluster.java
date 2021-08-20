package net.sf.dframe.cluster.simple;

import com.hazelcast.core.HazelcastInstance;
import net.sf.dframe.cluster.IMListener;
import net.sf.dframe.cluster.hazelcast.HazelcastShardingCluster;

/**
 * 非持久化集群
 */
public class SimpleCluster {

    HazelcastShardingCluster hsc = null;

    /**
     * 构建集群
     */
    public SimpleCluster (){
        this(new SimpleConfig());
    }
    /**
     * 构建集群
     * @param clusterName 集群名称
     */
    public SimpleCluster (String clusterName){
        this(new SimpleConfig(clusterName));
    }

    /**
     * 构建集群
     * @param clusterName 集群名称
     * @param listener 监听器
     */
    public SimpleCluster (String clusterName,IMListener listener){
        this(new SimpleConfig(clusterName),listener);
    }

    /**
     * 构建集群
     * @param listener 集群监听器
     */
    public SimpleCluster (IMListener listener){
        this(new SimpleConfig());
        hsc.setClusterMemberListener(listener);
    }

    /**
     * 构建集群
     * @param sc 指定配置
     */
    public SimpleCluster(SimpleConfig sc){
        hsc= new HazelcastShardingCluster(sc.getConfig());
    }

    /**
     * 构建集群
     * @param sc 指定配置信息
     * @param listener 指定集群监听
     */
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
