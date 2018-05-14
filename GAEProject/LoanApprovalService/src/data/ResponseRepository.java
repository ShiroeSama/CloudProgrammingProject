package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Response;
import entity.Risk;
import exception.DBException;
import exception.ShirOSException;

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
		String query = "SELECT * FROM public.\"Response\" WHERE id = ?";

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
				throw new DBException(String.format("The risk for id %d doesn't exist", id), null);
			}
		} catch(SQLException sqlException) {
			throw new DBException(String.format("Error during the find risk query for id %d", id), sqlException);
		}		
	}
	
}
