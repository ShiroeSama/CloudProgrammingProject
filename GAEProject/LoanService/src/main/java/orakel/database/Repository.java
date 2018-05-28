package orakel.database;

import java.sql.*;

import orakel.exception.DBException;

public abstract class Repository
{
	protected DatabaseConnection dbConnection;
	protected Connection connection;
	protected String table;
	
	/**
	 * Repository Constructor
	 * @throws DBException 
	 */
	public Repository() throws DBException {
		super();
		
		try {
			this.dbConnection = DatabaseConnection.getInstance();
			this.connection = this.dbConnection.getConnection();
		} catch (DBException dbException) {
			throw new DBException(String.format("%s Init Failed", this.getClass().getName()), dbException);
		}
	}

	public String getTable() {
		return table;
	}
}
