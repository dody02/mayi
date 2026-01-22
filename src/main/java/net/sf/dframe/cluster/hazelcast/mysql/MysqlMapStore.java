package net.sf.dframe.cluster.hazelcast.mysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.hazelcast.map.MapStore;

/**
 * store for map
 * @author dy02
 *
 */
public class MysqlMapStore implements MapStore<String, String>  {

	private static Logger log = LoggerFactory.getLogger(MysqlMapStore.class);
	
	private MysqlDataBase db;
	
	private String TableName = "MYSQL_MAP";
	
	private List<String> columns ;
	
	private final String VALUE_LABEL = "MESSAGE";
	private final String KEY_LABEL = "STRING_KEY";
	
	
	public MysqlMapStore (MysqlDataBase db) {
		this(db,null);
	}
	
	/**
	 * set the name
	 * @param db
	 * @param name
	 */
	public MysqlMapStore (MysqlDataBase db,String name) {
		this.db = db;
		initTable(db, name);
		
	}

	private void initTable(MysqlDataBase db, String name) {
		if (name!=null && !name.isEmpty()) {
			this.TableName = this.TableName+"_"+name;
		}
		columns = new ArrayList<String>();
		columns.add(KEY_LABEL);
		columns.add(VALUE_LABEL);
		try {
			db.executeSql("CREATE TABLE IF NOT EXISTS "+TableName+" ("+KEY_LABEL+"  VARCHAR(500) PRIMARY KEY ,"+VALUE_LABEL+" VARCHAR(1000))");
		} catch (Exception e) {
			log.error("create Queue Store Table Exception");
		}
	}
	
	
	
	@Override
	public String load(String key) {
		
		String value = null;
		JSONArray result;
		try {
			result = db.query("SELECT * FROM "+this.TableName+" WHERE "+KEY_LABEL+" = '"+key+"';",columns);
			if (!result.isEmpty()) {
				value = result.getJSONObject(0).getString(VALUE_LABEL);
			}
		} catch (Exception e) {
			log.error("load data for "+key +" error ",e);
		}
		return value;
		
	}

	@Override
	public Map<String, String> loadAll(Collection<String> keys) {
		Map<String, String> map = new HashMap<String,String>();
		for (String key : keys) {
			String value = load(key);
			map.put(key, value);
		}
			
		return map;
	}

	@Override
	public Iterable<String> loadAllKeys() {
		List<String> set = new ArrayList<String>();
		try {
			JSONArray result = db.query("SELECT * FROM "+this.TableName,columns);
			for (int i = 0 ; i < result.size(); i ++) {
				set.add(result.getJSONObject(i).getString(this.KEY_LABEL));
			}
		} catch (Exception e) {
			log.error("load ALL keys exception ",e);
		}
		return set;
	}

	@Override
	public void store(String key, String value) {
		try {
			String sql = "replace INTO "+this.TableName+" ("+KEY_LABEL+","+VALUE_LABEL+") VALUES ('"+key+"','"+value+"');";
			log.debug(sql);
			db.executeSql(sql);
//			db.executeSql("MERGE INTO "+this.TableName+" KEY ("+KEY_LABEL+") VALUES ('"+key+"','"+value+"');");
		} catch (Exception e) {
			log.error("save mysql exception",e);
		}
	}

	@Override
	public void storeAll(Map<String, String> map) {
		for ( String key : map.keySet()) {
			try {
				db.executeSql("replace INTO "+this.TableName+" ("+KEY_LABEL+","+VALUE_LABEL+") VALUES ('"+key+"','"+map.get(key)+"');");
//				db.executeSql("MERGE INTO "+this.TableName+" KEY ("+KEY_LABEL+") VALUES ('"+key+"','"+map.get(key)+"');");
			} catch (Exception e) {
				log.error("batch save mysql exception",e);
			}
		}
	}

	@Override
	public void delete(String key) {
		try {
			db.executeSql("DELETE FROM "+this.TableName+" WHERE "+KEY_LABEL+" = '"+key+"';");
		} catch (Exception e) {
			log.error("delete mysql exception",e);
		}
	}

	@Override
	public void deleteAll(Collection<String> keys) {
		try {
			for (String key: keys)
				delete(key);
		} catch (Exception e) {
			log.error("delete all mysql exception",e);
		}
	}

	

}
