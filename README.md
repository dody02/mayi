# mayi
easy way to create an cluster use Hazelcast

#config file: config.json
{
	"?name": "set cluster name 设置集群名称",
	"name": "cluster",
	
	"?port":" set local port, if null will use a random port设置本机使用的端口号，可以为空，不设置时将自动使用一个空闲端口",
	"port":5701,
	
	"?isMasterSalve":"是否为主从模式",
	"isMasterSalve":true,
	
	"?multicast":"设置为多播形式，可以为空，如果设置了多播，则下面的members不需要设置，即使设置也不起作用",
	"multicast": {
		"?group":"多播组名",
		"group": "224.2.2.3",
		"?port":"多播端口",
		"port": 54327
	},
	
	"?members":"如果未设置multicast，则使用指定成员方式，当存在multicast时，可以为空。成员为数组类型，里面列出每一个允许加入的成员ip和端口；",
	"members": [
		"localhost:5701",
		"10.0.2.130:2434",
		"10.0.2.132:2434"
	],
	
	"advance":{
		"persistencedir":"./db/",
		"h2user":"root",
		"h2password":"root",
		"timeout":1000,
		"size":10000
	}

}

#JAVA
public class TestCluster {
	public static void main(String[] args) throws Exception {
    //create a cluster by json config file
		HazelcastClusterCreater cc = new HazelcastClusterCreater();
		HazelcastMasterSlaveCluster c = (HazelcastMasterSlaveCluster) cc.getCluster("cluster.json");
		//if set master salve mode，check local node is master
		System.out.println(c.isMeActive());
    
    //set the cluster properites 
		c.clearArributesMap();
		IMap<String, String>  aaa = c.getArributesMap();
		aaa.put("1", "3");

		IQueue<String> a =(IQueue<String>) c.getH2Queue("test");
		
		a.put("asdf");
		String value = a.poll();
		System.out.println(a.size());
		System.out.println(value);
		
		System.out.println("OVer");
	}
}
