package shiros.exception;

public class ShirOSException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Exception Type
	 */
	protected String type;
	
	/**
	 * Previous Exception
	 */
	protected Exception previous;
	
	
	public ShirOSException(String message) {
		this(message, null);
	}	
		
	public ShirOSException(String message, Exception previous) {
        super(message);
        this.type = "ShirosException";
        this.previous = previous;
	}	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Exception getPrevious() {
		return previous;
	}

	public void setPrevious(Exception previous) {
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