package net.sf.dframe.cluster;


import com.hazelcast.cluster.MembershipEvent;

/**
 * 
 * @author Dody
 *
 */
public interface  IMListener {
	
	public void onMemberAdd(MembershipEvent event) ;
	
	public void onMemberRemove(MembershipEvent event);
	
}
