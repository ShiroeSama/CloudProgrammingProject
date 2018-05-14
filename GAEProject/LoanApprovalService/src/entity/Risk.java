package entity;

public class Risk {
	public static final String LOW = "Low";
	public static final String HIGH = "High";
	public static final int CEILING = 10000;
	
	protected int idRisk;
	protected String nameRisk;
	
	public int getId() {
		return idRisk;
	}
	public Risk setId(int id) {
		this.idRisk = id;
		return this;
	}
	
	public String getName() {
		return nameRisk;
	}
	
	public Risk setName(String name) {
		this.nameRisk = name;
		return this;
	}
}
