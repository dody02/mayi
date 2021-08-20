package net.sf.dframe.cluster.simple;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 集群组网方式
 */
public class NetworkCfg  {
    /**
     * 网络类型：多播与IP列表
     */
    enum ClusterNet{
        /**
         * 多播方式
         */
        Multicast ,
        /**
         * 给定IP列表
         */
        IPList
    }

    private ClusterNet net;
    private String group = "224.2.2.3";
    private int port = 54327;
    private ArrayList<String> members = new ArrayList<String>();


    /**
     * 构建网络配置，多播形式
     * @param group 多播组名，多播地址
     * @param port 多播端口
     */
    public NetworkCfg(String group, int port) {
        this.group = group;
        this.port = port;
        this.net = ClusterNet.Multicast;
    }

    /**
     * 网络配置，指定IP列表形式
     * @param members 指定组成集群的IP地址 字符串数组
     */
    public NetworkCfg(String[] members) {
        if (members== null || members.length ==0) {
            try {
                String m = InetAddress.getLocalHost().getHostAddress();
                this.members.add(m);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        this.members= (ArrayList<String>) Arrays.asList(members);
        this.net = ClusterNet.IPList;
    }

    /**
     * 默认配置
     * 默认多播形式
     * 默认多播组：224.2.2.3
     * 默认多播端口：54327
     */
    public NetworkCfg (){
        this("224.2.2.3",54327);
    }

    public String getGroup(){
        return this.group;
    }
    public int getPort(){
        return this.port;
    }
    public ArrayList<String> getMembers(){
        return this.members;
    }

    public ClusterNet getNet() {
        return net;
    }
}
