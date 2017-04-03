package marquise.daos.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceProvider {
	
	protected static MysqlDataSource dataSource;

	public static MysqlDataSource getDataSource() {
		
		if (dataSource == null){
			dataSource = new MysqlDataSource();
			dataSource.setServerName("localhost");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("marquiseBase");
			dataSource.setUser("root");
			dataSource.setPassword("mylove");
		}
		return dataSource;
	}

	
	}
	
	
	
	
	
	
	
	


