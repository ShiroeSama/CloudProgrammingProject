package shiros.entity;

public class Account {
	protected int id;
	protected String name;
	protected String lastname;
	protected String firstname;
	protected Double amount;
	protected Risk risk;
	
	public int getId() {
		return id;
	}
	
	public Account setId(int id) {
		this.id = id;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public Account setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public Account setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public Account setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public Account setAmount(Double amount) {
		this.amount = amount;
		return this;
	}
	
	public Risk getRisk() {
		return risk;
	}
	
	public Account setRisk(Risk risk) {
		this.risk = risk;
		return this;
	}
}
