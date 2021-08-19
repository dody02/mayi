package net.sf.dframe.cluster.simple;

import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import net.sf.dframe.cluster.IMListener;
import net.sf.dframe.cluster.hazelcast.HazelcastMasterSlaveCluster;
import net.sf.dframe.cluster.hazelcast.h2.DataBase;

/**
 * 简化使用
 */
public class SimpleMasterSlaveCluster {


    private HazelcastMasterSlaveCluster hazelcastMasterSlaveCluster;
    private String basedir = "./";

    private String clusterData = "defaultMasterSlaveCluster";
    private String dbUser = "root";
    private String dbPasswd = "root";

    private SimpleConfig config;

    public SimpleMasterSlaveCluster(IMListener listener){
        this("defaultMasterSlaveCluster",new SimpleConfig(),listener);
    }

    public SimpleMasterSlaveCluster(){
        this("defaultMasterSlaveCluster",new SimpleConfig());
    }

    public SimpleMasterSlaveCluster(String clusterName, SimpleConfig config){
        DataBase db = DataBase.getInstance(clusterName,basedir, dbUser, dbPasswd);
        hazelcastMasterSlaveCluster = new HazelcastMasterSlaveCluster(config.getConfig(),db);
    }

    public SimpleMasterSlaveCluster(String clusterName, SimpleConfig config, IMListener listener){
        DataBase db = DataBase.getInstance(clusterName,basedir, dbUser, dbPasswd);
        hazelcastMasterSlaveCluster = new HazelcastMasterSlaveCluster(config.getConfig(),db);
        hazelcastMasterSlaveCluster.setClusterMemberListener(listener);
    }

    /**
     * 获取持久化的属性
     * @return
     */
    public IMap<String,String> getArributesMap(){
        return  hazelcastMasterSlaveCluster.getArributesMap();
    }
    /**
     * 清除持久化属性
     */
    public void clearArributesMap(){
        hazelcastMasterSlaveCluster.clearArributesMap();
    }

    /**
     * 本机是否是活动的成员
     * @return
     */
    public boolean isMeActive() {
        return hazelcastMasterSlaveCluster.isMeActive();
    }

    /**
     * 获取集群中活动的节点
     * @return
     */
    public String getActive() {
        return hazelcastMasterSlaveCluster.getActive();
    }

    /**
     * 获取持久化的队列存储
     * @param name
     * @return
     */
    public IQueue<?> getPersistentQueue(String name){
        return hazelcastMasterSlaveCluster.getPersistentQueue(name);
    }
    /**
     * 获取持久化的map存储
     * @param name
     * @return
     */
    public IMap<?, ?> getPersistentMap(String name){
        return hazelcastMasterSlaveCluster.getPersistentMap(name);
    }
    /**
     * 获取Map存储（持久化于H2中的）
     * @param name
     * @return
     */
    public IMap<?,?> getH2Map(String name){
        return hazelcastMasterSlaveCluster.getH2Map(name);
    }

    /**
     * 获取集群队列存储
     * @param name
     * @return
     */
    public IQueue<?> getQueue(String name){
        return hazelcastMasterSlaveCluster.getQueue(name);
    }


    public void shutdown(){
        hazelcastMasterSlaveCluster.shutdown();
    }
    public HazelcastInstance getHz(){
        return hazelcastMasterSlaveCluster.getHz();
    }

    public void setClusterMemberListener (IMListener listener){
        hazelcastMasterSlaveCluster.setClusterMemberListener(listener);
    }
}
