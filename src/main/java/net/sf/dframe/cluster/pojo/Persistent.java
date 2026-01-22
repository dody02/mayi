package net.sf.dframe.cluster.pojo;
/**
 * 持久化配置POJO
 * @author dy02
 *
 */
public class Persistent {
	
	private String url;
	
	private String user;
	
	private String passwd;
	
	private int poolsize = 50;

	private int idle = 20;
	
	private long timeout = 60000;
	
	private boolean autoCommit = true;
	
	private String classdriver;

	/**
	 * getUrl
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * setUrl
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * getUser
	 * @return user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * setUser
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * getPsswd
	 * @return passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * setPassword
	 * @param passwd
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * getPoolssize
	 * @return poolsize
	 */
	public int getPoolsize() {
		return poolsize;
	}

	/**
	 * setPoolsize
	 * @param poolsize
	 */
	public void setPoolsize(int poolsize) {
		this.poolsize = poolsize;
	}

	/**
	 * getIdel
	 * @return idle
	 */
	public int getIdle() {
		return idle;
	}

	/**
	 * setIdel
	 * @param idle
	 */
	public void setIdle(int idle) {
		this.idle = idle;
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
	 * isAutoCommit
	 * @return autoCommit
	 */
	public boolean isAutoCommit() {
		return autoCommit;
	}

	/**
	 * setAutoCommit
	 * @param autoCommit
	 */
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

	/**
	 * getClassdriver
	 * @return classdriver
	 */
	public String getClassdriver() {
		return classdriver;
	}

	/**
	 * setClassdriver
	 * @param classdriver
	 */
	public void setClassdriver(String classdriver) {
		this.classdriver = classdriver;
	}

	
	
}
