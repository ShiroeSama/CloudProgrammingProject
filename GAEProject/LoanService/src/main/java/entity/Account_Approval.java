package entity;

public class Account_Approval {
	protected int id;
	protected Account account;
	protected Approval approval;
	
	public int getId() {
		return id;
	}
	
	public Account_Approval setId(int id) {
		this.id = id;
		return this;
	}

	public Account getAccount() {
		return account;
	}
	
	public Account_Approval setAccount(Account account) {
		this.account = account;
		return this;
	}
	
	public Approval getApproval() {
		return approval;
	}
	
	public Account_Approval setApproval(Approval approval) {
		this.approval = approval;
		return this;
	}
}
