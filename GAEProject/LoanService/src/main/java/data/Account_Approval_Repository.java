package data;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

public class Account_Approval_Repository extends Repository
{
	public Account_Approval_Repository() throws DBException {
		super();
		this.table = "public.\"Association_BankAccount_Approval\"";
	}
	
	/**
	 * Get one approval
	 * 
	 * @return Approval
	 * @throws DBException
	 */
	protected Account_Approval getAccount_Approval(ResultSet result) throws DBException, SQLException {
		int id = result.getInt("id");
		int idBankAccount = result.getInt("idBankAccount");
		int idApproval = result.getInt("idApproval");
						
		Account account;
		Approval approval;
		try {
			AccountRepository accountRepository = new AccountRepository();
			ApprovalRepository approvalRepository = new ApprovalRepository();
			account = accountRepository.find(idBankAccount);	
			approval = approvalRepository.find(idApproval);
		} catch (ShirOSException shirosException) {
			throw new DBException(String.format("Failed getAccount_Approval for id ", id), shirosException);
		}
		
		Account_Approval account_Approval = new Account_Approval();
		account_Approval.setId(id).setAccount(account).setApproval(approval);
		
		return account_Approval;
	}
	
	/**
	 * Get all approval
	 * 
	 * @return List<Approval>
	 * @throws DBException
	 */
	public List<Account_Approval> select() throws DBException {
		String query = String.format("SELECT * FROM %s", this.table);

		try {
			Statement stmt = this.connection.createStatement();
			ResultSet results = stmt.executeQuery(query);
			
			List<Account_Approval> list_account_approvals = new ArrayList<Account_Approval>();
			
			while(results.next()) {
				Account_Approval account_Approval = this.getAccount_Approval(results);
				list_account_approvals.add(account_Approval);
			}
			
			return list_account_approvals;
		} catch(SQLException sqlException) {
			throw new DBException("Error during the select account query", sqlException);
		}
	}
	/**
	 * Find Account_Approval
	 * 
	 * @param int id
	 * 
	 * @return Approval
	 * @throws DBException
	 */
	public Account_Approval find(int id) throws DBException {
		String query = "SELECT * FROM public.\"Association_BankAccount_Approval\" WHERE id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {				
				int account_approval_Id = result.getInt("id");
				int idBankAccount = result.getInt("idBankAccount");
				int idApproval = result.getInt("idApproval");	
				
				Account account;
				Approval approval;
				try {
					AccountRepository accountRepository = new AccountRepository();
					ApprovalRepository approvalRepository = new ApprovalRepository();
					account = accountRepository.find(idBankAccount);	
					approval = approvalRepository.find(idApproval);
				} catch (ShirOSException shirosException) {
					throw new DBException(String.format("Failed to find the account or approval id for Account_Approval %d", id), shirosException);
				}
				
				Account_Approval account_Approval = new Account_Approval();
				account_Approval.setId(id).setAccount(account).setApproval(approval);	

				return account_Approval;			
			} else {
				throw new DBException(String.format("The risk for id %d doesn't exist", id), null);
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
	 * @return Account
	 * @throws DBException
	 */	
	public Account_Approval add(int accountId, Double amount) throws DBException {
		Account account;
		Account_Approval account_Approval;
		Approval approval;
		try {
			AccountRepository accountRepository = new AccountRepository();
			account = accountRepository.find(accountId);
		} catch (ShirOSException shirosException) {
			throw new DBException("Failed to insert the risk during the account insert query", shirosException);
		}
		
		Risk riskAccount = account.getRisk();
		
		//AJOUT APPROVAL
	
		int id_add_Approval = 0;
		String query = String.format("INSERT INTO %s (date_approval, \"idResponse\") VALUES (?,?)", this.table);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date_approval = dateFormat.format(new Date());
		
		approval = new Approval();
		approval.setDate(date_approval);
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, date_approval);
			entity.Response response = new entity.Response();
			if (riskAccount.getName().equals("HIGH")) {				
				//AJOUT REPONSE REFUSED
				stmt.setInt(2, 2);
				approval.setResponse(response.setId(2).setName("REFUSED"));
			} else {
				//AJOUT REPONSE ACCEPTED
				stmt.setInt(2, 1);
				approval.setResponse(response.setId(1).setName("ACCEPTED"));
			}			
			int update = stmt.executeUpdate();
			if (update == 0) {
				throw new DBException("Creating approval failed. (No row affected)");
			}			
			try (ResultSet object = stmt.getGeneratedKeys()) {
	            if (object.next()) {
	            	id_add_Approval = object.getInt(1);
	            	approval.setId(object.getInt(1));
	            } else {
	                throw new DBException("Creating approval failed. (No ID obtained)");
	            }
	        }
		} catch(SQLException|ShirOSException sqlException) {
			throw new DBException("Error during the approval insert query", sqlException);
		}	
		
		account_Approval = new Account_Approval();
		account_Approval.setAccount(account).setApproval(approval);
		//AJOUT LIEN APPROVAL & ACCOUNT
		
		String query_add = String.format("INSERT INTO %s (\"idBankAccount\", \"idApproval\") VALUES (?,?) ", this.table);
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query_add, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, account.getId());
			stmt.setInt(2, id_add_Approval);
			
			int update = stmt.executeUpdate();
			
			if (update == 0) {
				throw new DBException("Creating approval failed. (No row affected)");
			}			
			try (ResultSet object = stmt.getGeneratedKeys()) {
	            if (object.next()) {
	            	account_Approval.setId(object.getInt(1));
	            } else {
	                throw new DBException("Creating risk failed. (No ID obtained)");
	            }
	        }
		} catch(SQLException|ShirOSException sqlException) {
			throw new DBException("Error during the account insert query", sqlException);
		}	
		
		return account_Approval;
	}
	
	
	/**
	 * Delete Account_Approval
	 * 
	 * @param int account_approval_Id
	 * 
	 * @return void
	 * @throws DBException
	 */	
	public void delete(int account_approval_Id) throws DBException {
		Account_Approval account_Approval = this.find(account_approval_Id);
		
		String query = String.format("DELETE FROM %s WHERE id = ?", this.table);
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, account_approval_Id);
			stmt.executeUpdate();
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the delete query for Account_Approval id %d", account_approval_Id), sqlException);
		}
		
		try {
			ApprovalRepository approvalRepository = new ApprovalRepository();
			approvalRepository.delete(account_Approval.getApproval().getId());
		} catch (ShirOSException shirosException) {
			throw new DBException("Failed to delete the risk during the account delete query", shirosException);
		}		
	}
}		