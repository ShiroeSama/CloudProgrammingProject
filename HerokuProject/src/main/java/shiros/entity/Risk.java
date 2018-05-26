package shiros.entity;

public class Risk {
	public static final String LOW = "Low";
	public static final String HIGH = "High";
	public static final int CEILING = 10000;
	
	protected int id;
	protected String name;
	
	public int getId() {
		return id;
	}
	public Risk setId(int id) {
		this.id = id;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public Risk setName(String name) {
		this.name = name;
		return this;
	}
}
