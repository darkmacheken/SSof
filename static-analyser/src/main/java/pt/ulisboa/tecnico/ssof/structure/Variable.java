package pt.ulisboa.tecnico.ssof.structure;

public class Variable {
	private int bytes;
	private String type;
	private String name;
	private String address;
	
	public Variable(int bytes, String type, String name, String address) {
		this.bytes = bytes;
		this.type = type;
		this.name = name;
		this.address = address;
	}
	
	public int getBytes() {
		return bytes;
	}
	
	public void setBytes(int bytes) {
		this.bytes = bytes;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}