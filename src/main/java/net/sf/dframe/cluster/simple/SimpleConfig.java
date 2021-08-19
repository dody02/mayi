package net.sf.dframe.cluster.simple;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;

/**
 * 简化获取配置
 */
public class SimpleConfig {

    private Config config ;

    SimpleConfig (){
        this("default",new NetworkCfg());
    }
    SimpleConfig (String clusterName,NetworkCfg cfg){
        config = initCfg(clusterName,cfg);
    }

    public Config getConfig(){
        return config;
    }

    /**
     * 初始化
     * @return
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
