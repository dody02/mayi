package net.sf.dframe.cluster.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import net.sf.dframe.cluster.ICluster;
import net.sf.dframe.cluster.IMListener;

/**
 * 
 * @author dy02
 *
 */
public class HazelcastShardingCluster implements  ICluster {

	protected HazelcastInstance hz;
	
	protected MemberListenerAdeptor listenerAdeptor;
	
	public HazelcastShardingCluster(Config cfg) {
		hz = Hazelcast.getOrCreateHazelcastInstance(cfg);
		hz.getCluster().addMembershipListener(listenerAdeptor );
	}


	public HazelcastInstance getHz() {
		return hz;
	}


	@Override
	public void shutdown() {
		hz.shutdown();
	}


	@Override
	public void setClusterMemberListener(IMListener listener) {
		getListener().setListener(listener);
	}

	public MemberListenerAdeptor getListener() {
		return listenerAdeptor;
	}
	
}
