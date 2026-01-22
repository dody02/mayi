package net.sf.dframe.cluster;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import net.sf.dframe.cluster.pojo.Persistent;
/**
 * 基础连接池
 * @author dy02
 *
 */
public class DataBasePool implements IConnectionPool {

	
	public static final String  DBTYPE_MYSQL = "jdbc:mysql";
	public static final String  DBTYPE_H2 = "jdbc:h2";
	public static final String  DBTYPE_ORACL = "jdbc:oracle";
	public static final String  DBTYPE_SQLSERVER = "jdbc:microsoft";
	public static final String  DBTYPE_POSTGRE = "jdbc:postgresql";
	public static final String DBTYPE_REDIS = "redis://";
//	public static final String  DBTYPE_DB2 = "db2";
//	public static final String  DBTYPE_SQLITE = "sqlite";
	
	
	private Persistent persistent;
	
	private HikariDataSource ds ;

	/**
	 * DataBasePool
	 * @param persistent
	 */
	public DataBasePool (Persistent persistent) {
		this.persistent = persistent;
		init();
	}

	/**
	 * init
	 */
	private void init() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(persistent.getUrl());
		config.setUsername(persistent.getUser());
		config.setPassword(persistent.getPasswd());
		config.setAutoCommit(persistent.isAutoCommit());
		config.setConnectionTimeout(persistent.getTimeout());
		config.setMaximumPoolSize(persistent.getPoolsize());
		config.setMinimumIdle(persistent.getIdle());
		config.setMaxLifetime(0);
		config.setIdleTimeout(persistent.getTimeout());
		if (persistent.getClassdriver() != null && !persistent.getClassdriver().isEmpty()) {
			config.setDriverClassName(persistent.getClassdriver());
		}
		if (persistent.getUrl().contains(DBTYPE_MYSQL)) {
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");	
		}
		
		if (persistent.getUrl().contains(DBTYPE_ORACL)) {
			config.addDataSourceProperty("implicitCachingEnabled", "true");
			config.addDataSourceProperty("maxStatements", "250");
		}
		
		ds = new HikariDataSource(config);
	}

	
	/**
	 * getConnection
	 * @return Connection
	 * @throws SQLException 
	 */
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
		
	}

	/**
	 * dispose
	 */
	public void dispose() {
		ds.close();
	}

	
}
