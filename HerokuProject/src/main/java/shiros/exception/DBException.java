package shiros.exception;

import javax.ws.rs.core.Response.Status;

public class DBException extends ShirOSException {
	private static final long serialVersionUID = 2L;
	protected static final String TYPE = "DatabaseException";
	
	public DBException(String message) {
	    super(message);
	}
	
	public DBException(String message, Status status) {
	    super(message, status);
	}	
		
	public DBException(String message, Throwable previous) {
	    super(message, previous);
	}	
	
	public DBException(String message, Status status, Throwable previous) {
	    super(message, status, previous);
	}
}
