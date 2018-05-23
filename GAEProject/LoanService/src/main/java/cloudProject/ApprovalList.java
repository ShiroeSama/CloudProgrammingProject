package cloudProject;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


@SuppressWarnings("serial")
public class ApprovalList extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    //  Database credentials
		final String databaseUrl = "postgres://wsccixbtuidyta:1f0e386b68be62083503cae79a231e7888ca2a8870932f7e58a9cf65fd10ad84@ec2-54-235-90-200.compute-1.amazonaws.com:5432/dclfn1iueip14h";
		//final String databaseUrl = "postgres://ixkjgacjrotshg:fa94c3bd5940d8bbf677c52749a5cc8c6cae1ff451b8946e9befb063d8e82b4b@ec2-54-243-28-109.compute-1.amazonaws.com:5432/d1nfump0vhgpj8";
		try {			
	  	 	// Set response content type
		    resp.setContentType("text/html");
		    PrintWriter out = resp.getWriter();
		    String title = "Database Result";
		      
		    String docType =
		         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		      
		    out.println(docType +
		         "<html>\n" +
		         "<head><title>" + title + "</title></head>\n" +
		         "<body bgcolor = \"#f0f0f0\">\n" +
		         "<h1 align = \"center\">" + title + "</h1>\n");
		    
		    try {
		    	Connection connection = configureConnection(databaseUrl);
				// Execute SQL query
			     Statement stmt = connection.createStatement();
			     String sql;
			     sql = "SELECT last_name, first_name, amount FROM public.\"BankAccount\"";
			     ResultSet rs = stmt.executeQuery(sql);
				
		         while(rs.next()){
		             //Retrieve by column name
		         	 String last_name = rs.getString("last_name");
		             String first_name = rs.getString("first_name");	             
		             double amount = rs.getDouble("amount");

		             //Display values
		             out.println("last_name" + last_name + "<br>");
		             out.println(", first_name " + first_name + "<br>");
		             out.println(", first_name: " + amount + "<br>");
		         }
		         out.println("</body></html>");
		         rs.close();
		         stmt.close();
		         connection.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("############# ERROR ############");
				e.printStackTrace();
			}
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	    
	}

	private Connection configureConnection(String databaseUrl) throws URISyntaxException, ClassNotFoundException {
		URI dbUri = new URI(databaseUrl);
		String username = dbUri.getUserInfo().split(":")[0];
  	 	String password = dbUri.getUserInfo().split(":")[1];
  	 	String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath()+"?sslmode=require";
  	 	Class.forName("org.postgresql.Driver");
  	 	Connection connection = null ;
		try {
			connection = DriverManager.getConnection(dbUrl, username, password);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;		
	}
}
				