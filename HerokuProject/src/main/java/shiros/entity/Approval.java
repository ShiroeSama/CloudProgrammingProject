package shiros.entity;

public class Approval {
	protected int id;
	protected Long date;
	protected Account account;
	protected Response response;
	
	public int getId() {
		return id;
	}
	
	public Approval setId(int id) {
		this.id = id;
		return this;
	}
	
	public Long getDate() {
		return date;
	}
	
	public Approval setDate(Long date) {
		this.date = date;
		return this;
	}

	public Account getAccount() {
		return account;
	}
	
	public Approval setAccount(Account account) {
		this.account = account;
		return this;
	}

	public Response getResponse() {
		return response;
	}
	
	public Approval setResponse(Response response) {
		this.response = response;
		return this;
	}
}
