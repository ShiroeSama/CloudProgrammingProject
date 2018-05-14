package data;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.ws.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import exception.DBException;

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