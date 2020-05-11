package net.sf.dframe.cluster.pojo;
/**
 * 
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
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getPoolsize() {
		return poolsize;
	}

	public void setPoolsize(int poolsize) {
		this.poolsize = poolsize;
	}

	public int getIdle() {
		return idle;
	}

	public void setIdle(int idle) {
		this.idle = idle;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public boolean isAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

	public String getClassdriver() {
		return classdriver;
	}

	public void setClassdriver(String classdriver) {
		this.classdriver = classdriver;
	}

	
	
}
