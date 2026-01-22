package net.sf.dframe.cluster.pojo;

import java.util.List;

/**
 * 简化配置信息
 * @author dy02
 *
 */
public class ConfigMessage {
	
	private String name;
	
	private int port;
	
	private boolean isMasterSalve;
	
	private Multicast multicast;
	
	private List<String> members;
	
	private Advance advance;
	
	private Persistent persistent;

	/**
	 * getName
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * setPort
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * getPort
	 * @return port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * getMulticast
	 * @return multicast
	 */
	public Multicast getMulticast() {
		return multicast;
	}

	/**
	 * setMulticast
	 * @param multicast
	 */
	public void setMulticast(Multicast multicast) {
		this.multicast = multicast;
	}

	/**
	 * getMembers
	 * @return members
	 */
	public List<String> getMembers() {
		return members;
	}

	/**
	 * setMembers
	 * @param members
	 */
	public void setMembers(List<String> members) {
		this.members = members;
	}

	/**
	 * setMasterSalve
	 * @param isMasterSalve
	 */
	public void setMasterSalve(boolean isMasterSalve) {
		this.isMasterSalve = isMasterSalve;
	}

	/**
	 * isMasterSalve
	 * @return isMasterSalve
	 */
	public boolean isMasterSalve() {
		return isMasterSalve;
	}

	/**
	 * getAdvance
	 * @return advance
	 */
	public Advance getAdvance() {
		return advance;
	}

	/**
	 * setAdvance
	 * @param advance
	 */
	public void setAdvance(Advance advance) {
		this.advance = advance;
	}

	/**
	 * getPersistent
	 * @return persistent
	 */
	public Persistent getPersistent() {
		return persistent;
	}

	/**
	 * setPersistent
	 * @param persistent
	 */
	public void setPersistent(Persistent persistent) {
		this.persistent = persistent;
	}
	
}
