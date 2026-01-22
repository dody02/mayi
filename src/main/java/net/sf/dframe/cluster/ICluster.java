package net.sf.dframe.cluster;

/**
 * Cluster interface 
 * 不同的集群类型将具体实现
 * @author Dody
 *
 */
public interface ICluster {
	/**
	 * shutdown
	 */
	public void shutdown();

	/**
	 * setClusterMemberListener
	 * @param listener
	 */
	public void setClusterMemberListener(IMListener listener);
}
