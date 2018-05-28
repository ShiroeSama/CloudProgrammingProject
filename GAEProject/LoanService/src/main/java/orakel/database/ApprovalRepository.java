package orakel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import orakel.entity.Account;
import orakel.entity.Approval;
import orakel.entity.Response;
import orakel.entity.Risk;
import orakel.exception.DBException;
import orakel.exception.ShirOSException;

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
		Long date = result.getLong("date_approval");
		int accountId = result.getInt("idAccount");
		int responseId = result.getInt("idResponse");
		
		// ----------------
		// Get Account
		
		Account account;		
		try {
			AccountRepository accountRepository = new AccountRepository();
			account = accountRepository.find(accountId);
		} catch (ShirOSException shirosException) {
			throw new DBException(String.format("Failed to find the account id %s", accountId), shirosException);
		}

		
		// ----------------
		// Get Response
		
		Response response;
		try {
			ResponseRepository responseRepository = new ResponseRepository();
			response = responseRepository.find(responseId);	
		} catch (ShirOSException shirosException) {
			throw new DBException(String.format("Failed to find the response id %s for approval %d", responseId, id), shirosException);
		}

		
		// ----------------
		// Create Approval
		
		Approval approval = new Approval();
		approval
			.setId(id)
			.setDate(date)
			.setAccount(account)
			.setResponse(response)
		;
		
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
		String query = String.format("SELECT * FROM %s WHERE id = ?", this.table);
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				return this.getApproval(result);		
			} else {
				throw new DBException(String.format("The approval for id %d doesn't exist", id), null);
			}
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the find risk query for id %d", id), sqlException);
		}		
	}
	
	/**
	 * Add Approval
	 * 
	 * @param int accountId
	 * @param Double amount
	 * 
	 * @return Approval
	 * @throws DBException
	 */	
	public Approval add(int accountId, Double amount) throws DBException {
		
		// ----------------
		// Get Account
		
		Account account;		
		try {
			AccountRepository accountRepository = new AccountRepository();
			account = accountRepository.find(accountId);
		} catch (ShirOSException shirosException) {
			throw new DBException(String.format("Failed to find the account id %s", accountId), shirosException);
		}
	
		
		// ----------------
		// Get Risk
		
		Risk risk = account.getRisk();
		
		// ----------------
		// Get Response
		
		Response response;		
		try {
			ResponseRepository responseRepository = new ResponseRepository();
			
			int responseId;			
			if (risk.getName().equals(Risk.HIGH)
				|| account.getAmount() + amount > Risk.CEILING
			) {
				responseId = Response.REFUSED_ID;
			} else {
				responseId = Response.ACCEPTED_ID;
			}
			
			response = responseRepository.find(responseId);
		} catch (ShirOSException shirosException) {
			throw new DBException(String.format("Failed to find the response id %s", accountId), shirosException);
		}
		
		// ----------------
		// Update Account
		
		if (response.getId() == Response.ACCEPTED_ID) {
			try {
				AccountRepository accountRepository = new AccountRepository();
				account = accountRepository.edit(account, amount);
			} catch (ShirOSException shirosException) {
				throw new DBException(String.format("Failed to find the account id %s", accountId), shirosException);
			}
		}
		
		
		// ----------------
		//Prepare Approval	
		
		String query = String.format("INSERT INTO %s (date_approval, \"idAccount\", \"idResponse\") VALUES (?,?,?)", this.table);
		
		// Date
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		timestamp.getTime();
				
		Approval approval = new Approval();
		approval
			.setDate(timestamp.getTime())
			.setAccount(account)
			.setResponse(response)
		;
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setLong(1, approval.getDate());
			stmt.setInt(2, approval.getAccount().getId());
			stmt.setInt(3, approval.getResponse().getId());
			
			int update = stmt.executeUpdate();
			if (update == 0) {
				throw new DBException("Creating approval failed. (No row affected)");
			}
			
			try (ResultSet object = stmt.getGeneratedKeys()) {
	            if (object.next()) {
	            	approval.setId(object.getInt(1));
	            } else {
	                throw new DBException("Creating approval failed. (No ID obtained)");
	            }
	        }
		} catch(SQLException|ShirOSException sqlException) {
			throw new DBException("Error during the approval insert query", sqlException);
		}	
		
		return approval;
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
		String query = String.format("DELETE FROM %s WHERE id = ?", this.table);
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, approvalId);
			stmt.executeUpdate();
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the delete query for approval id %d", approvalId), sqlException);
		}
	}
}		