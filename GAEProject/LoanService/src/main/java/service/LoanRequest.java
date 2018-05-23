package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import data.Account_Approval_Repository;
import data.DatabaseConnection;
import entity.Account_Approval;
import exception.ShirOSException;



@Path("api/loan")
@Produces(MediaType.APPLICATION_JSON)
public class LoanRequest extends Service {
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(@FormParam("accountId") int accountId, @FormParam("amount") Double amount) {
    	try {
    		Account_Approval_Repository repository = new Account_Approval_Repository();    		
    		Account_Approval account_Approval = repository.add(accountId, amount);
    		
    		DatabaseConnection.close();
    		return Response.status(Status.CREATED).entity(account_Approval).build();
    	} catch (ShirOSException shirosException) {
    		return this.errorHandling(shirosException);
		}
    }
	  	
}
				