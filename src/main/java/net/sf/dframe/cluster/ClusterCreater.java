package net.sf.dframe.cluster;

/**
 * 构建集群实例 
 * @author dy02
 *
 */
public interface ClusterCreater {
	/**
	 * get cluster
	 * @param url
	 * @return ICluster
	 * @throws Exception
	 */
	public ICluster getCluster(String url) throws Exception;
}
