package shiros.database;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import shiros.exception.DBException;

public class DatabaseConnection {
	protected static DatabaseConnection _instance;
	protected String databaseUrl = "postgres://wsccixbtuidyta:1f0e386b68be62083503cae79a231e7888ca2a8870932f7e58a9cf65fd10ad84@ec2-54-235-90-200.compute-1.amazonaws.com:5432/dclfn1iueip14h";
	protected Connection connection;
	
	protected DatabaseConnection() {}
	
	
	public static DatabaseConnection getInstance() throws DBException {
		if (_instance == null) {
			_instance = new DatabaseConnection();
			_instance.initConnection();
		}
		
		return _instance;
	}
	
	protected void initConnection() throws DBException {
		try {
			URI dbUri = new URI(this.databaseUrl);
			String username = dbUri.getUserInfo().split(":")[0];
		    String password = dbUri.getUserInfo().split(":")[1];
		    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		
		    try {
			    this.connection = DriverManager.getConnection(dbUrl, username, password);			
			} catch (Exception exception) {
				throw new DBException("Failed to init the connection on the PostgreSQL DB.", exception);
			}
		} catch (Exception exception) {
			throw new DBException("The Database URL is not correct.", exception);
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public static void close() throws DBException {
		if (_instance != null) {
			try {
				if (!_instance.getConnection().isClosed()) {
					_instance.getConnection().close();
				}
				_instance= null;
			} catch (SQLException sqlException) {
				throw new DBException("Connection close error", sqlException);
			}			
		}
	}
}
