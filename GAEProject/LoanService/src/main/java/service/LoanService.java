package service;

import java.util.List;

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


@Path("api/approvals")
@Produces(MediaType.APPLICATION_JSON)
public class LoanService extends Service {
	@GET
    public Response list() {
    	try {
    		Account_Approval_Repository repository = new Account_Approval_Repository();   
    		List<Account_Approval> list_account_approval = repository.select();

    		DatabaseConnection.close();
        	return Response.ok(list_account_approval).build();
    	} catch (ShirOSException shirosException) {
    		return this.errorHandling(shirosException);
		}
    }
    @DELETE
    @Path("/{idDelete}")
    public Response delete(@PathParam("accountId") int accountId) {
    	try {
    		Account_Approval_Repository repository = new Account_Approval_Repository();
    		repository.delete(accountId);

    		DatabaseConnection.close();
        	return Response.noContent().build();
    	} catch (ShirOSException shirosException) {
    		return this.errorHandling(shirosException);
		}
    }
	
}
				