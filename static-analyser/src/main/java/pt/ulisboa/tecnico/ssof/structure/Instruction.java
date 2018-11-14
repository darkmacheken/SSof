package pt.ulisboa.tecnico.ssof.structure;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import pt.ulisboa.tecnico.ssof.structure.operands.OperandParser;
import pt.ulisboa.tecnico.ssof.visitor.Visitor;

@JsonDeserialize(using = InstructionDeserializer.class)
public abstract class Instruction {
	private int position;
	private String address;
	protected OperandParser operandParser;
	
	public Instruction(int position, String address) {
		this.position = position;
		this.address = address;
		this.operandParser = new OperandParser();
	}
	
	public int getPosition() {
		return position;
	}

	public String getAddress() {
		return address;
	}

	public abstract void accept(Visitor visitor);
}