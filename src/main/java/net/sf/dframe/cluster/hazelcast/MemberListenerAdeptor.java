package net.sf.dframe.cluster.hazelcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;

import net.sf.dframe.cluster.IMListener;

/**
 * Adeptor
 * @author Dody
 *
 */
public class MemberListenerAdeptor implements MembershipListener{

	private static Logger log = LoggerFactory.getLogger(MemberListenerAdeptor.class);
	
	private IMListener listener;
	
	@Override
	public void memberAdded(MembershipEvent membershipEvent) {
		log.debug("member add : " + membershipEvent.getMember());
		if (listener != null)
			listener.onMemberAdd(membershipEvent);
	}

	@Override
	public void memberRemoved(MembershipEvent membershipEvent) {
		log.debug("member removed : " + membershipEvent.getMember());
		if (listener != null)
			listener.onMemberRemove(membershipEvent);
	}
	
	public void setListener(IMListener listener) {
		this.listener = listener;
	}
	
	public IMListener getListener() {
		return listener;
	}

}
