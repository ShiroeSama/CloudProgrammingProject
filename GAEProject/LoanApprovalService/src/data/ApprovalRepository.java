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

import entity.Account;
import entity.Account_Approval;
import entity.Approval;
import entity.Risk;
import exception.DBException;
import exception.ShirOSException;

public class ApprovalRepository extends Repository
{
	public ApprovalRepository() throws DBException {
		super();
		this.table = "public.\"Approval\"";
	}
	
	
	/**
	 * Get one approval
	 * 
	 * @return Approval
	 * @throws DBException
	 */
	protected Approval getApproval(ResultSet result) throws DBException, SQLException {
		int id = result.getInt("id");
		String date_approval = result.getString("date_approval");
		int idResponse = result.getInt("idResponse");
						
		entity.Response response;
		try {
			ResponseRepository responseRepository = new ResponseRepository();
			response = responseRepository.find(idResponse);	
		} catch (ShirOSException shirosException) {
			throw new DBException(String.format("Failed to find the response id %s for approval %d", idResponse, id), shirosException);
		}
		
		Approval approval = new Approval();
		approval.setId(id)
			.setDate(date_approval)
			.setResponse(response);
		
		return approval;
	}
	
	/**
	 * Get all approval
	 * 
	 * @return List<Approval>
	 * @throws DBException
	 */
	public List<Approval> select() throws DBException {
		String query = String.format("SELECT * FROM %s", this.table);

		try {
			Statement stmt = this.connection.createStatement();
			ResultSet results = stmt.executeQuery(query);
			
			List<Approval> approvals = new ArrayList<Approval>();
			
			while(results.next()) {
				Approval approval = this.getApproval(results);
				approvals.add(approval);
			}
			
			return approvals;
		} catch(SQLException sqlException) {
			throw new DBException("Error during the select account query", sqlException);
		}
	}
	
	/**
	 * Find Approval
	 * 
	 * @param int id
	 * 
	 * @return Approval
	 * @throws DBException
	 */
	public Approval find(int id) throws DBException {
		String query = "SELECT * FROM public.\"Approval\" WHERE id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {				
				int approvalId = result.getInt("id");
				String date_approval = result.getString("date_approval");
				int idResponse = result.getInt("idResponse");
				
				entity.Response response;
				try {
					ResponseRepository responseRepository = new ResponseRepository();
					response = responseRepository.find(idResponse);	
				} catch (ShirOSException shirosException) {
					throw new DBException(String.format("Failed to find the response id %s for approval %d", idResponse, id), shirosException);
				}
				
				Approval approval = new Approval();
				approval.setId(approvalId).setDate(date_approval).setResponse(response);	

				return approval;			
			} else {
				throw new DBException(String.format("The risk for id %d doesn't exist", id), null);
			}
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the find risk query for id %d", id), sqlException);
		}		
	}
	
	/**
	 * Delete Approval
	 * 
	 * @param int approvalId
	 * 
	 * @return void
	 * @throws DBException
	 */	
	public void delete(int approvalId) throws DBException {
		//Approval approval = this.find(approvalId);		
		String query = String.format("DELETE FROM %s WHERE id = ?", this.table);		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, approvalId);
			stmt.executeUpdate();
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the delete query for Approval id %d", approvalId), sqlException);
		}
	
	}
}		