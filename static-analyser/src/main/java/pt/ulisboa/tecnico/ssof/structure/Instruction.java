package pt.ulisboa.tecnico.ssof.structure;

import java.util.List;

public class Instruction {
	private String operation;
	private int position;
	private List<String> args;
	private String address;
	
	public Instruction(String operation, int position, List<String> args, String address) {
		this.operation = operation;
		this.position = position;
		this.args = args;
		this.address = address;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public List<String> getArgs() {
		return args;
	}
	
	public void setArgs(List<String> args) {
		this.args = args;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}