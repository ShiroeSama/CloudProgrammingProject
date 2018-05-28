package shiros.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import shiros.exception.ShirOSException;


@Path("api/exceptions")
@Produces(MediaType.APPLICATION_JSON)
public class ExceptionService extends Service {

    @GET
    @Path("/show")
    public Response show() {
    	try {
    		throw new ShirOSException("This page is a test to display JSON Exception with Status", Status.BAD_REQUEST);
    	} catch (ShirOSException shirosException) {
    		return this.errorHandling(shirosException);
		}
    }
}
