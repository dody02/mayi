# mayi
可快速建立应用集群服务，支持快速集群，主从集群。
可以通过config.json文件配置，建集群；也支持代码快速建立集群。
集群名称相同的应用，则自动组成集群，可以在集群之间通讯和共享数据。
# 快速使用

public class Test implements IMListener {
	
	/**分别试了三种集群*/
    public static void main(String[] arg){
    	//快速建立一个主从模式的集群。
        SimpleMasterSlaveCluster smsc = new SimpleMasterSlaveCluster(new Test());
        System.out.println("当前节点是否为活动的："+smsc.isMeActive());
	//关闭应用时，可以通过shutdown退出集群。
        smsc.shutdown();
	//快速建立一个集群
        System.out.println("测试简单无持久化集群");
        SimpleCluster sc = new SimpleCluster();
        sc.shutdown();
	//快速建立一个持久化集群
        System.out.println("测试简单持久化集群");
        SimplePersistentCluster spc = new SimplePersistentCluster();
        spc.shutdown();
    }

    @Override
    public void onMemberAdd(MembershipEvent event) {

    }

    @Override
    public void onMemberRemove(MembershipEvent event) {

    }
    
    
}


# 每种集群都可以通过默认配置构建或者指定配置信息构建，如：
/**
     * 构建一个简单主从集群
     * @param clusterName 集群名
     * @param config 集群配置
     * @param listener 集群监听器
     */
    public SimpleMasterSlaveCluster(String clusterName, SimpleConfig config, IMListener listener)
    
# 集群配置信息如下,如果不指定参数，则为采用默认构建
     /**
     * 集群配置
     * @param clusterName 集群名称
     * @param cfg 集群网络配置，包括多播与指定IP序列
     */
    SimpleConfig (String clusterName,NetworkCfg cfg)
    
# 构建SimpleConfig传入的NetworkCfg参数主要用于网络配置，有两种形式，一种是多播，一种是指定IP列表，默认无参构建则为多播形式：
     /**
     * 构建网络配置，多播形式
     * @param group 多播组名，多播地址
     * @param port 多播端口
     */
    public NetworkCfg(String group, int port) 
    
# 或者：

    /**
     * 网络配置，指定IP列表形式
     * @param members 指定组成集群的IP地址 字符串数组
     */
    public NetworkCfg(String[] members)
    

    
    
    
