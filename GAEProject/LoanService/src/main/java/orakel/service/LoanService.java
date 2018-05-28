package orakel.service;

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

import orakel.database.ApprovalRepository;
import orakel.database.DatabaseConnection;
import orakel.entity.Approval;
import orakel.exception.ShirOSException;


@Path("api/approvals")
@Produces(MediaType.APPLICATION_JSON)
public class LoanService extends Service {
	@GET
    public Response list() {
    	try {
    		ApprovalRepository repository = new ApprovalRepository();   
    		List<Approval> approvals = repository.select();

    		DatabaseConnection.close();
        	return Response.ok(approvals).build();
    	} catch (ShirOSException shirosException) {
    		return this.errorHandling(shirosException);
		}
    }
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(@FormParam("accountId") int accountId, @FormParam("amount") Double amount) {
    	try {
    		ApprovalRepository repository = new ApprovalRepository();   		
    		Approval approval = repository.add(accountId, amount);
    		
    		DatabaseConnection.close();
    		return Response.status(Status.CREATED).entity(approval).build();
    	} catch (ShirOSException shirosException) {
    		return this.errorHandling(shirosException);
		}
    }
	
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
    	try {
    		ApprovalRepository repository = new ApprovalRepository();   	
    		repository.delete(id);

    		DatabaseConnection.close();
        	return Response.noContent().build();
    	} catch (ShirOSException shirosException) {
    		return this.errorHandling(shirosException);
		}
    }
}
