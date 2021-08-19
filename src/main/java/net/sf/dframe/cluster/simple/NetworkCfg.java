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
        Multicast ,
        IPList
    }

    private ClusterNet net;
    private String group = "224.2.2.3";
    private int port = 54327;
    private ArrayList<String> members = new ArrayList<String>();

//    {"127.0.0.1"};
    // 构造方法
    public NetworkCfg(String group, int port) {
        this.group = group;
        this.port = port;
        this.net = ClusterNet.Multicast;
    }

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
