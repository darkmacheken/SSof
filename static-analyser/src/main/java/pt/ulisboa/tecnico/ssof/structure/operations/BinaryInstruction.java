package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.structure.operands.Operand;

public abstract class BinaryInstruction extends Instruction {
	private Operand firstArgument;
	private Operand secondArgument;
	
	public BinaryInstruction(int position, String address, String firstArgument, String secondArgument) {
		super(position, address);
		this.firstArgument = this.operandParser.parseOperand(firstArgument);
		this.secondArgument = this.operandParser.parseOperand(secondArgument);
	}

	public Operand getFirstArgument() {
		return this.firstArgument;
	}

	public Operand getSecondArgument() {
		return this.secondArgument;
	}
}
