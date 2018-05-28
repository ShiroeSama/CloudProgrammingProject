package orakel.database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import orakel.entity.Account;
import orakel.entity.Risk;
import orakel.exception.DBException;
import orakel.exception.ShirOSException;

public class AccountRepository extends Repository {
	
	public AccountRepository() throws DBException {
		super();
		this.table = "public.\"BankAccount\"";
	}
	
	protected Account getAccount(ResultSet result) throws DBException, SQLException {
		int id = result.getInt("id");
		String name = result.getString("account_name");
		String lastname = result.getString("last_name");
		String firstname = result.getString("first_name");
		Double amount = result.getDouble("amount");
		int riskId = result.getInt("idRisk");
						
		Risk risk;
		try {
			RiskRepository riskRepository = new RiskRepository();
			risk = riskRepository.find(riskId);	
		} catch (ShirOSException shirosException) {
			throw new DBException(String.format("Failed to find the risk id %s for account %d", riskId, id), shirosException);
		}
		
		Account account = new Account();
		account.setId(id)
			.setName(name)
			.setLastname(lastname)
			.setFirstname(firstname)
			.setAmount(amount)
			.setRisk(risk);
		
		return account;
	}
	
	/**
	 * Get all account
	 * 
	 * @return List<Account>
	 * @throws DBException
	 */
	public List<Account> select() throws DBException {
		String query = String.format("SELECT * FROM %s", this.table);

		try {
			Statement stmt = this.connection.createStatement();
			ResultSet results = stmt.executeQuery(query);
			
			List<Account> accounts = new ArrayList<Account>();
			
			while(results.next()) {
				Account account = this.getAccount(results);
				accounts.add(account);
			}
			
			return accounts;
		} catch(SQLException sqlException) {
			throw new DBException("Error during the select account query", sqlException);
		}
	}
	
	/**
	 * Get all account
	 * 
	 * @return Account
	 * @throws DBException
	 */
	public Account find(int accountId) throws DBException {
		String query = String.format("SELECT * FROM %s WHERE id = ?", this.table);

		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, accountId);
			ResultSet results = stmt.executeQuery();
						
			if(results.next()) {
				return this.getAccount(results);
			} else {
				throw new DBException(String.format("The account id %d doesn't exist", accountId));
			}
		} catch(SQLException sqlException) {
			throw new DBException("Error during the select account query", sqlException);
		}
	}
	
	/**
	 * Add Account
	 * 
	 * @param String name
	 * @param String lastname
	 * @param String firstname
	 * @param Double amount
	 * 
	 * @return Account
	 * @throws DBException
	 */	
	public Account add(String name, String lastname, String firstname, Double amount) throws DBException {
		String riskName;
		if (amount >= Risk.CEILING) {
			riskName = Risk.HIGH;
		} else {
			riskName = Risk.LOW;
		}
		
		Risk risk;
		try {
			RiskRepository riskRepository = new RiskRepository();
			risk = riskRepository.add(riskName);
		} catch (ShirOSException shirosException) {
			throw new DBException("Failed to insert the risk during the account insert query", shirosException);
		}
		
		Account account = new Account();
		account.setName(name)
			.setLastname(lastname)
			.setFirstname(firstname)
			.setAmount(amount)
			.setRisk(risk);
		
		String query = String.format("INSERT INTO %s (account_name, last_name, first_name, amount, \"idRisk\") VALUES (?,?,?,?,?)", this.table);
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, account.getName());
			stmt.setString(2, account.getLastname());
			stmt.setString(3, account.getFirstname());
			stmt.setDouble(4, account.getAmount());
			stmt.setInt(5, account.getRisk().getId());
			
			int update = stmt.executeUpdate();
			if (update == 0) {
				throw new DBException("Creating account failed. (No row affected)");
			}
			
			try (ResultSet object = stmt.getGeneratedKeys()) {
	            if (object.next()) {
	            	account.setId(object.getInt(1));
	            } else {
	                throw new DBException("Creating account failed. (No ID obtained)");
	            }
	        }
		} catch(SQLException|ShirOSException sqlException) {
			throw new DBException("Error during the account insert query", sqlException);
		}	
		
		return account;
	}
	
	/**
	 * Add Account
	 * 
	 * @param Account account
	 * @param Double amount
	 * 
	 * @return Account
	 * @throws DBException
	 */	
	public Account edit(Account account, Double amount) throws DBException {		
		String query = String.format("UPDATE %s SET amount = ? WHERE id = ?", this.table);
		
		account.setAmount(account.getAmount() + amount);
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, account.getAmount());
			stmt.setInt(2, account.getId());
			
			int update = stmt.executeUpdate();
			if (update == 0) {
				throw new DBException("Editing account failed. (No row affected)");
			}
			
			try (ResultSet object = stmt.getGeneratedKeys()) {
	            if (object.next()) {
	            	account.setId(object.getInt(1));
	            } else {
	                throw new DBException("Editing account failed. (No ID obtained)");
	            }
	        }
		} catch(SQLException|ShirOSException sqlException) {
			throw new DBException("Error during the account update query", sqlException);
		}	
		
		return account;
	}
	
	/**
	 * Delete Account
	 * 
	 * @param int accountId
	 * 
	 * @return void
	 * @throws DBException
	 */	
	public void delete(int accountId) throws DBException {
		Account account = this.find(accountId);
		
		String query = String.format("DELETE FROM %s WHERE id = ?", this.table);
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, accountId);
			stmt.executeUpdate();
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the delete query for account id %d", accountId), sqlException);
		}
		
		try {
			RiskRepository riskRepository = new RiskRepository();
			riskRepository.delete(account.getRisk().getId());
		} catch (ShirOSException shirosException) {
			throw new DBException("Failed to delete the risk during the account delete query", shirosException);
		}		
	}
}
