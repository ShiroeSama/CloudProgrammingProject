package shiros.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shiros.entity.Response;
import shiros.exception.DBException;

public class ResponseRepository extends Repository {

	public ResponseRepository() throws DBException {
		super();
		this.table = "public.\"Response\"";
	}
	
	/**
	 * Find Response
	 * 
	 * @param int id
	 * 
	 * @return Response
	 * @throws DBException
	 */
	public Response find(int id) throws DBException {
		String query = String.format("SELECT * FROM %s WHERE id = ?", this.table);

		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {				
				int responseId = result.getInt("id");
				String name = result.getString("name");
				
				Response response = new Response();
				response.setId(responseId).setName(name);	

				return response;			
			} else {
				throw new DBException(String.format("The response for id %d doesn't exist", id));
			}
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the find response query for id %d", id), sqlException);
		}		
	}
}