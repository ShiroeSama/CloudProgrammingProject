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

import orakel.database.AccountRepository;
import orakel.database.DatabaseConnection;
import orakel.entity.Account;
import orakel.exception.ShirOSException;


@Path("api/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountService extends Service {

    @GET
    public Response list() {
    	try {
    		AccountRepository repository = new AccountRepository();    		
    		List<Account> accounts = repository.select();
    		
    		DatabaseConnection.close();  		
        	return Response.ok(accounts).build();
    	} catch (ShirOSException shirosException) {
    		return this.errorHandling(shirosException);
		}
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(@FormParam("name") String name, @FormParam("lastname") String lastname, @FormParam("firstname") String firstname, @FormParam("amount") Double amount) {
    	try {
    		AccountRepository repository = new AccountRepository();    		
    		Account account = repository.add(name, lastname, firstname, amount);
    		
    		DatabaseConnection.close();
    		return Response.status(Status.CREATED).entity(account).build();
    	} catch (ShirOSException shirosException) {
    		return this.errorHandling(shirosException);
		}
    }

    @DELETE
    @Path("/{accountId}")
    public Response delete(@PathParam("accountId") int accountId) {
    	try {
    		AccountRepository repository = new AccountRepository();    		
    		repository.delete(accountId);
    		
    		DatabaseConnection.close();  		
        	return Response.noContent().build();
    	} catch (ShirOSException shirosException) {
    		return this.errorHandling(shirosException);
		}
    }
}
