package entity;

public class Approval {
	protected int id;
	protected String dateApproval;
	protected Response response;
	
	public int getId() {
		return id;
	}
	
	public Approval setId(int id) {
		this.id = id;
		return this;
	}

	public Response getResponse() {
		return response;
	}
	
	public Approval setResponse(Response response) {
		this.response = response;
		return this;
	}
	
	public String getDate() {
		return dateApproval;
	}
	
	public Approval setDate(String dateApproval) {
		this.dateApproval = dateApproval;
		return this;
	}
}
