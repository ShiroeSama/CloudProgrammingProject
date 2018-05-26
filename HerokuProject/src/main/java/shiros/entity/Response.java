package shiros.entity;

public class Response {
	public static final int ACCEPTED_ID = 1;
	public static final int REFUSED_ID = 2;
	
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
