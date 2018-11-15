package pt.ulisboa.tecnico.ssof.structure;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import pt.ulisboa.tecnico.ssof.visitor.Visitor;

@JsonDeserialize(using = InstructionDeserializer.class)
public abstract class Instruction {
	private int position;
	private String address;
	
	public Instruction(int position, String address) {
		this.position = position;
		this.address = address;
	}
	
	public int getPosition() {
		return this.position;
	}

	public String getAddress() {
		return this.address;
	}

	public abstract void accept(Visitor visitor);
}