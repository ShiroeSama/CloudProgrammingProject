package shiros.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import shiros.entity.ExceptionJSON;
import shiros.exception.ShirOSException;

public abstract class Service {
	
	protected Response errorHandling(ShirOSException shirosException) {
		System.err.println("--------------------------------------------");
		System.err.println(shirosException);
		System.err.println("--------------------------------------------");
				
		ExceptionJSON exceptionJson = new ExceptionJSON();
		exceptionJson.configureEntity(shirosException);
		
		return Response.status(shirosException.getStatus()).entity(exceptionJson).build();
	}
}
