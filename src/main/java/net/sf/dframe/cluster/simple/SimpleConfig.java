package net.sf.dframe.cluster.simple;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;

/**
 * 简化获取配置
 */
public class SimpleConfig {

    private Config config ;
    private String clusterName;

    /**
     * 默认配置
     * 默认的集群名称，默认的网络配置
     */
    SimpleConfig (){
        this("default",new NetworkCfg());
    }

    /**
     * 构建简单配置
     * @param clusterName 集群名
     */
    SimpleConfig(String clusterName){
        this(clusterName,new NetworkCfg());
    }

    /**
     * 集群配置
     * @param clusterName 集群名称
     * @param cfg 集群网络配置，包括多播与指定IP序列
     */
    SimpleConfig (String clusterName,NetworkCfg cfg){
        config = initCfg(clusterName,cfg);
        this.clusterName = clusterName;
    }

    /**
     * getClusterName
     * @return clusterName
     */
    public String getClusterName() {
        return clusterName;
    }

    /**
     * setClusterName
     * @param clusterName
     */
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    /**
     * getConfig
     * @return config
     */
    public Config getConfig(){
        return config;
    }

    /**
     * 初始化
     * @return Config
     */
    private  Config initCfg(String clusterName, NetworkCfg cfg){
        Config config = new Config();
        NetworkConfig network = config.getNetworkConfig();
        network.setPortAutoIncrement(true);
        JoinConfig join = network.getJoin();
        //如果是多播
        if (cfg.getNet() == NetworkCfg.ClusterNet.Multicast){
            join.getMulticastConfig().setEnabled(true);
            join.getMulticastConfig().setMulticastGroup(cfg.getGroup());
            join.getMulticastConfig().setMulticastPort(cfg.getPort());
        } else {
            join.getMulticastConfig().setEnabled(false);
            join.getTcpIpConfig().setMembers(cfg.getMembers()).setEnabled(true);
        }
        config.setInstanceName(clusterName);
        return config;
    }
}
