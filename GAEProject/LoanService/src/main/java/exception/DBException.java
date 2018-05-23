package exception;

public class DBException extends ShirOSException {
	private static final long serialVersionUID = 2L;

	public DBException(String message) {
		this(message, null);
	}

	public DBException(String message, Exception previous) {
        super(message, previous);
        this.type = "DatabaseException";
	}
}
