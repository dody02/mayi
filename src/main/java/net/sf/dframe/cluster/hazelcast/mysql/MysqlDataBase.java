package net.sf.dframe.cluster.hazelcast.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.sf.dframe.cluster.DataBasePool;
import net.sf.dframe.cluster.pojo.Persistent;
/**
 * mysql 数据库
 * @author Dody
 *
 */
public final class MysqlDataBase {

	private static Logger log = LoggerFactory.getLogger(MysqlDataBase.class);
	
		
	private static ConcurrentMap<String , MysqlDataBase> instances = new ConcurrentHashMap<String ,MysqlDataBase>();
	
	private DataBasePool dbo = null;
	
	public static synchronized MysqlDataBase getInstance(String clusterData,Persistent persistent) {
			if ( !instances.containsKey(clusterData)) {
				
				instances.put(clusterData, new MysqlDataBase(persistent));
			} 
		return  instances.get(clusterData);
	}
	
	/**
	 * 建立数据库
	 * @param dir
	 * @param port
	 * @param user
	 * @param password
	 */
	public MysqlDataBase(Persistent persistent) {
		
		dbo = new DataBasePool(persistent);
	}
	
	

	/**
	 * excuteSql
	 * @throws Exception 
	 * 
	 */
	public void executeSql (String sql) throws Exception {
		Connection conn = null;
		Statement createStatement = null;
		try {
			conn = dbo.getConnection();
			createStatement = conn.createStatement();
			createStatement.execute(sql);
		}catch (Exception ex) {
			log.error("exception",ex);
			throw ex;
		}finally {
			try {
				if (createStatement != null)
					createStatement.close();
				createStatement = null;
			}catch (Exception e) {
				createStatement = null;
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			}catch (Exception e) {
				conn = null;
			}
		}
	}

	/**
	 *   get query result in json array
	 * @param sql
	 * @param getColumns
	 * @return
	 * @throws SQLException
	 */
	public JSONArray query(String sql,List<String> getColumns) throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		JSONArray result = null;
		try {
			conn = dbo.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			result = getResult(rs,getColumns);
		}catch (Exception e ) {
			log.error("Database query error , SQL: "+ sql ,e);
			throw e;
		}finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				}catch (Exception e) {
					rs = null;
				}
			}
			if (st != null ) {
				try {
					st.close();
					st = null;
				} catch (SQLException e) {
					st = null;
				}
			}
			if (conn !=null) {
				try {
					conn.close();
					conn = null;
				}catch (Exception e) {
					conn = null;
				}
			}	
		}
		return result;
	}
	
	private JSONArray getResult(ResultSet rs,List<String> columns) throws SQLException {
		JSONArray result = new JSONArray();
		if (rs != null) {
			while ( rs.next()) {
				JSONObject item = new JSONObject();
				for (String column:columns) {
					item.put(column, rs.getObject(column));
				}
				result.add(item);
			}
		}
		
		return result;
	}
	
	
	public void close() {
		dbo.dispose();
	}
	
	
	/**
	 * excuteSql
	 * @throws Exception 
	 * 
	 */
	public void dropTable (String tableName) throws Exception {
		Connection conn = null;
		Statement createStatement = null;
		try {
			conn = dbo.getConnection();
			createStatement = conn.createStatement();
			createStatement.execute("DROP TABLE "+tableName);
		}catch (Exception ex) {
			log.error("create Table exception",ex);
			throw ex;
		}finally {
			try {
				if (createStatement != null)
					createStatement.close();
				createStatement = null;
			}catch (Exception e) {
				createStatement = null;
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			}catch (Exception e) {
				conn = null;
			}
		}
	}

	
}
