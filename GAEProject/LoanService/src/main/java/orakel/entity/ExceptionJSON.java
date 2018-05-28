package orakel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import orakel.exception.ShirOSException;

@XmlRootElement(name="exception")
public class ExceptionJSON {
	
	protected String type;
	protected String message;
	protected List<ExceptionJSON> previous;
	protected StackTraceElement[] trace;
	
	public void configureEntity(ShirOSException shirosException) {
		this.setType(shirosException.getType());
		this.setMessage(shirosException.getMessage());
				
		this.previous = new ArrayList<ExceptionJSON>();
		
		if (shirosException.getPrevious() != null) {
			this.configurePrevious(shirosException.getPrevious());
		}
		
		this.trace = shirosException.getStackTrace();
	}
	
	protected void configurePrevious(Exception exception) {
		ExceptionJSON exceptionTrace = new ExceptionJSON();
		
		if (exception instanceof ShirOSException) {
			ShirOSException shirosException = (ShirOSException)exception;
						
			exceptionTrace.setType(shirosException.getType());
			exceptionTrace.setMessage(shirosException.getMessage());
			
			if (shirosException.getPrevious() != null) {
				this.configurePrevious(shirosException.getPrevious());
			}
		} else {
			exceptionTrace.setType(exception.getClass().getName());
			exceptionTrace.setMessage(exception.getMessage());
		}

		this.previous.add(exceptionTrace);
	}
	
	
	public String getType() {
		return type;
	}
	
	public ExceptionJSON setType(String type) {
		this.type = type;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	
	public ExceptionJSON setMessage(String message) {
		this.message = message;
		return this;
	}

	public List<ExceptionJSON> getPrevious() {
		return previous;
	}

	public ExceptionJSON setPrevious(List<ExceptionJSON> previous) {
		this.previous = previous;
		return this;
	}

	public StackTraceElement[] getTrace() {
		return trace;
	}

	public void setTrace(StackTraceElement[] trace) {
		this.trace = trace;
	}
}
