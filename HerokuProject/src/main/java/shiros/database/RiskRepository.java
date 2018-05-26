package shiros.database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import shiros.entity.Risk;
import shiros.exception.DBException;
import shiros.exception.ShirOSException;

public class RiskRepository extends Repository {

	public RiskRepository() throws DBException {
		super();
		this.table = "public.\"Risk\"";
	}
	
	/**
	 * Find Risk
	 * 
	 * @param int id
	 * 
	 * @return Risk
	 * @throws DBException
	 */
	public Risk find(int id) throws DBException {
		String query = "SELECT * FROM public.\"Risk\" WHERE id = ?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {				
				int riskId = result.getInt("id");
				String name = result.getString("name_risk");
				
				Risk risk = new Risk();
				risk.setId(riskId)
					.setName(name);	

				return risk;			
			} else {
				throw new DBException(String.format("The risk for id %d doesn't exist", id), null);
			}
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the find risk query for id %d", id), sqlException);
		}		
	}
	
	/**
	 * Find Risk
	 * 
	 * @param int accountId
	 * 
	 * @return Risk
	 * @throws DBException
	 */
	public Risk findByAccount(int accountId) throws DBException {
		AccountRepository accountRepository = new AccountRepository();
		String query = String.format("SELECT * "
				+ "FROM %s AS risk "
				+ "LEFT JOIN %s AS acc ON acc.\"idRisk\" = risk.id "
				+ "WHERE acc.id = ?", 
				this.table, accountRepository.getTable());
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, accountId);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {				
				int riskId = result.getInt("id");
				String name = result.getString("name_risk");
				
				Risk risk = new Risk();
				risk.setId(riskId)
					.setName(name);	

				return risk;			
			} else {
				throw new DBException(String.format("The risk for account id %d doesn't exist", accountId), null);
			}
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the find risk by account query for account id %d", accountId), sqlException);
		}		
	}
	
	/**
	 * Add Risk
	 * 
	 * @param String name
	 * 
	 * @return Risk
	 * @throws DBException
	 */
	public Risk add(String name) throws DBException {		
		Risk risk = new Risk();
		risk.setName(name);
		
		String query = String.format("INSERT INTO %s (name_risk) VALUES (?)", this.table);
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, risk.getName());

			int update = stmt.executeUpdate();
			if (update == 0) {
				throw new DBException("Creating risk failed. (No row affected)");
			}
			
			try (ResultSet object = stmt.getGeneratedKeys()) {				
	            if (object.next()) {
	            	risk.setId(object.getInt(1));
	            } else {
	                throw new DBException("Creating risk failed. (No ID obtained)");
	            }
	        }
		} catch(SQLException|ShirOSException sqlException) {
			throw new DBException("Error during the account insert query", sqlException);
		}	
		
		return risk;
	}
	
	/**
	 * Delete Risk
	 * 
	 * @param int accountId
	 * 
	 * @return void
	 * @throws DBException
	 */	
	public void delete(int riskId) throws DBException {
		String query = String.format("DELETE FROM %s WHERE id = ?", this.table);
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, riskId);
			stmt.executeUpdate();
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the delete query for risk id %d", riskId), sqlException);
		}		
	}
}
