package net.sf.dframe.cluster;


import com.hazelcast.cluster.MembershipEvent;

/**
 * 消息监听接口
 * @author Dody
 *
 */
public interface  IMListener {
	/**
	 * onMemberAdd
	 * @param event
	 */
	public void onMemberAdd(MembershipEvent event) ;

	/**
	 * onMemberRemove
	 * @param event
	 */
	public void onMemberRemove(MembershipEvent event);
	
}
