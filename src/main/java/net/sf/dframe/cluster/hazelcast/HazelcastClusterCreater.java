package net.sf.dframe.cluster.hazelcast;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hazelcast.config.Config;

import net.sf.dframe.cluster.DataBasePool;
import net.sf.dframe.cluster.ICluster;
import net.sf.dframe.cluster.IPersistent;
import net.sf.dframe.cluster.hazelcast.h2.DataBase;
import net.sf.dframe.cluster.hazelcast.h2.H2Persistent;
import net.sf.dframe.cluster.hazelcast.mysql.MysqlDataBase;
import net.sf.dframe.cluster.hazelcast.mysql.MysqlPersistent;
import net.sf.dframe.cluster.hazelcast.redis.RedisPersistent;
import net.sf.dframe.cluster.pojo.Persistent;

/**
 * 
 * @author dy02
 *
 */
public class HazelcastClusterCreater {
	
    private static Logger log = LoggerFactory.getLogger(HazelcastClusterCreater.class);
	
	private HazelcastConfig config = new HazelcastConfig();
	
	private Config cfg;

	private IPersistent persistent = null;
	
	private final String UTF8 = "utf-8";
	/**
	 * 
	 * @param is
	 * @return
	 * @throws Exception 
	 */
	public ICluster getCluster (InputStream is) throws Exception {
		ICluster result = null;
		String configString = this.readInputStream(is, UTF8);	
		log.info("JSONConfig:"+configString);
		cfg = config.readJSONConfig(configString);
		try{
			is.close();		
		} catch (Exception e) {
			log.warn("close InputStream Exception",e);;
		}
		result = createrClusterInstance();
		return result;
	}
	
	/**
	 *  构建Cluster
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public ICluster getCluster(String url) throws Exception {
		
		ICluster result = null;
		
		if (url == null) {
			cfg = config.readConfig();
			log.info("config url is null, use default config info to create Cluster");
		} else {
			if ( url.endsWith("xml")) {
				cfg = config.readXmlConfig(url);
				log.info("create cluster by xml file "+url);
			}else {
				cfg = config.readConfig(url);
				log.info("create cluster by json file "+ url);
			}
		}
		result = createrClusterInstance();
		
		return result;
	}

	/**
	 * 构建实例
	 * @return
	 */
	private ICluster createrClusterInstance() {
		ICluster result;
		//persistent setting
		if (config.getCm().getPersistent() != null) {
			Persistent persistentConfig = config.getCm().getPersistent();
			//mysql 
			if (persistentConfig.getUrl().contains(DataBasePool.DBTYPE_MYSQL)){
				this.persistent = new MysqlPersistent(MysqlDataBase.getInstance(config.getCm().getName(),persistentConfig));
				//H2
			} else if (persistentConfig.getUrl().contains(DataBasePool.DBTYPE_H2)) {
				this.persistent = new H2Persistent(DataBase.getInstance(config.getCm().getName(), persistentConfig));
				// redis
			} else if (persistentConfig.getUrl().contains(DataBasePool.DBTYPE_REDIS)) {
				String redisurl = persistentConfig.getUrl();
				String host = redisurl;
			    String[] temp = redisurl.split("/");
			    String db = "1";
			    if (  temp.length > 3) {
			    	host = redisurl.substring(0,redisurl.lastIndexOf("/"));
			    	db = temp[3];
			    }
			    this.persistent = new RedisPersistent(host,db,persistentConfig.getUser(),persistentConfig.getPasswd());
			} 
			
		}
		
		//主从
		if (config.getCm().isMasterSalve()) {
			String dir = null;
			String user = null;
			String password = null;
			if (config.getCm().getAdvance() != null) {
				dir = config.getCm().getAdvance().getPersistencedir();
				user = config.getCm().getAdvance().getH2user();
				password = config.getCm().getAdvance().getH2password();
			}
			DataBase db = DataBase.getInstance(config.getCm().getName(),dir, user, password);
			result = new HazelcastMasterSlaveCluster(cfg,db);
			if ( persistent != null ) {
				((HazelcastMasterSlaveCluster)result).setPersistent(persistent);
			}
			log.info("create HazelcastMasterSlaveCluster by :"+JSONObject.toJSONString(cfg));
			
		} else { 
			
			if ( persistent != null ) {
				result = new HazelcastPersistentCluster(cfg, persistent);
				
			} else {
				result = new HazelcastShardingCluster(cfg);
				log.info("create HazelcastShardingCluster by :"+JSONObject.toJSONString(cfg));
			}
			
		}
		return result;
	}
	
	
	/**
	 *  读取配置
	 * @param in
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	 private String readInputStream(InputStream in, String charset) throws Exception {
		  if (charset == null) {
			charset = UTF8;
		}
	      byte[] buf = new byte[1024];
	      StringBuffer sb = new StringBuffer();
	      int len = 0;
          while ((len=in.read(buf)) != -1) {
              sb.append(new String(buf,0,len, charset));
              
          }
	      return sb.toString();
	}
	
}
