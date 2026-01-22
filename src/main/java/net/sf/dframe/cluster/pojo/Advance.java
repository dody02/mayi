package net.sf.dframe.cluster.pojo;

/**
 * 配置POJO
 * @author dy02
 *
 */
public class Advance {
	
	private String persistencedir;
	
	private long timeout;
	
	private int size;

	private String h2user;
	private String h2password;

	/**
	 * get persistence dir
	 * @return persistencedir
	 */
	public String getPersistencedir() {
		return persistencedir;
	}

	/**
	 * set persistence dir
	 * @param persistencedir
	 */
	public void setPersistencedir(String persistencedir) {
		this.persistencedir = persistencedir;
	}

	/**
	 * get timeout
	 * @return timeout
	 */
	public long getTimeout() {
		return timeout;
	}

	/**
	 * setTimeout
	 * @param timeout
	 */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	/**
	 * getSize
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * setSize
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * getH2user
	 * @return h2user
	 */
	public String getH2user() {
		return h2user;
	}

	/**
	 * setH2user
	 * @param h2user
	 */
	public void setH2user(String h2user) {
		this.h2user = h2user;
	}

	/**
	 * getH2password
	 * @return h2password
	 */
	public String getH2password() {
		return h2password;
	}

	/**
	 * setH2password
	 * @param h2password
	 */
	public void setH2password(String h2password) {
		this.h2password = h2password;
	}
	
	
	
}
