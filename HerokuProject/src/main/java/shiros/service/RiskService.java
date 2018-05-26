package shiros.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import shiros.database.DatabaseConnection;
import shiros.database.RiskRepository;
import shiros.entity.Risk;
import shiros.exception.ShirOSException;


@Path("api/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class RiskService extends Service {

    @GET
    @Path("/{accountId}/risks")
    public Response list(@PathParam("accountId") int accountId) {
    	try {
    		RiskRepository repository = new RiskRepository();
    		Risk risk = repository.findByAccount(accountId);
    		
    		DatabaseConnection.close();
        	return Response.ok(risk).build();
    	} catch (ShirOSException shirosException) {
    		try {
				DatabaseConnection.close();
			} catch (ShirOSException ex) {
	    		return this.errorHandling(ex);
			}
    		return this.errorHandling(shirosException);
		}
    }
}
