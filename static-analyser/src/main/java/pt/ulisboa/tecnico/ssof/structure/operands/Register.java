package pt.ulisboa.tecnico.ssof.structure.operands;

public class Register extends Operand {
	private String name;
	
	public Register(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}