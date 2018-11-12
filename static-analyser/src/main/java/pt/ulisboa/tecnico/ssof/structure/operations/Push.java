package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.structure.Instruction;

public class Push extends Instruction {
	private String value;
	
	public Push(int position, String address, String value) {
		super(position, address);
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
