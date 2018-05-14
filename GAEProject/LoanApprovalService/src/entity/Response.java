package entity;

public class Response {
	public static final String LOW = "ACCEPTED";
	public static final String HIGH = "REFUSED";
	
	protected int idResponse;
	protected String nameResponse;
	
	public int getId() {
		return idResponse;
	}
	public Response setId(int id) {
		this.idResponse = id;
		return this;
	}
	
	public String getName() {
		return nameResponse;
	}
	
	public Response setName(String name) {
		this.nameResponse = name;
		return this;
	}
}
