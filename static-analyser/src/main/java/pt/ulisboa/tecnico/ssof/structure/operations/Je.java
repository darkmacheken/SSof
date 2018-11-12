package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.structure.Instruction;

public class Je extends Instruction {
	private String jumpAddress;

	public Je(int position, String address, String jumpAddress) {
		super(position, address);
		this.jumpAddress = jumpAddress;
	}

	public String getJumpAddress() {
		return jumpAddress;
	}
}
