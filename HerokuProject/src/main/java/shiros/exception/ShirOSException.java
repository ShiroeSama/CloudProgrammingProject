package shiros.exception;

import javax.ws.rs.core.Response.Status;

public class ShirOSException extends Exception {
	private static final long serialVersionUID = 1L;
	protected static final String TYPE = "ShirosException";
	
	/**
	 * Exception Type
	 */
	protected String type;
	
	/**
	 * Code
	 */
	protected Status status;
	
	/**
	 * Previous Exception
	 */
	protected Throwable previous;
	
	
	public ShirOSException(String message) {
	    super(message);
	    this.type = TYPE;
	    this.status = Status.INTERNAL_SERVER_ERROR;
	    this.previous = null;
	}
	
	public ShirOSException(String message, Status status) {
	    this(message);
	    this.status = status;
	}	
		
	public ShirOSException(String message, Throwable previous) {
	    this(message);
        this.previous = previous;
	}	
	
	public ShirOSException(String message, Status status, Throwable previous) {
		this(message);
	    this.status = status;
	    this.previous = previous;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Throwable getPrevious() {
		return previous;
	}

	public void setPrevious(Throwable previous) {
		this.previous = previous;
	}

	@Override
	public String toString() {
		String toString = String.format("[%s] : \n", type);
		toString += String.format("\tMessage : %s\n", this.getMessage());
		toString += "\tPrevious : \n";
		toString += previous;
		
		return toString;
	}
}