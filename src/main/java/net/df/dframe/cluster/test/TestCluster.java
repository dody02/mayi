package net.df.dframe.cluster.test;


import com.hazelcast.collection.IQueue;
import com.hazelcast.map.IMap;

import net.sf.dframe.cluster.hazelcast.HazelcastClusterCreater;
import net.sf.dframe.cluster.hazelcast.HazelcastMasterSlaveCluster;

public class TestCluster {
	public static void main(String[] args) throws Exception {
		HazelcastClusterCreater cc = new HazelcastClusterCreater();
		HazelcastMasterSlaveCluster c = (HazelcastMasterSlaveCluster) cc.getCluster("cluster.json");
		
		System.out.println(c.isMeActive());
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
