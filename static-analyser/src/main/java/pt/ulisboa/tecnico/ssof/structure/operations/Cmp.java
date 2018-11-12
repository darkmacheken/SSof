package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.structure.Instruction;

public class Cmp extends Instruction {
	private String arg0;
	private String arg1;

	public Cmp(int position, String address, String arg0, String arg1) {
		super(position, address);
		this.arg0 = arg0;
		this.arg1 = arg1;
	}

	public String getArg0() {
		return arg0;
	}

	public String getArg1() {
		return arg1;
	}
}
