package marquise.daos.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;

public class DataSourceProvider {
	
	public static MysqlDataSource dataSource;

	public static MysqlDataSource getDataSource() {
		
		if (dataSource == null){
			dataSource = new MysqlDataSource();
			dataSource.setServerName("zwgaqwfn759tj79r.chr7pe7iynqr.eu-west-1.rds.amazonaws.com");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("jyz1vhfvffbmzqa3");
			dataSource.setUser("xyb3xc48u2c8jbo9");
			dataSource.setPassword("mkl926vwmgtc8rfq");
		}
		return dataSource;
	}

	
	}
	
	
	
	
	
	
	
	


