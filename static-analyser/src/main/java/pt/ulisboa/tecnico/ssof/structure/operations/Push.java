package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.structure.operands.Operand;
import pt.ulisboa.tecnico.ssof.utils.OperandsUtils;
import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Push extends Instruction {
	private Operand value;
	
	public Push(int position, String address, String value) {
		super(position, address);
		this.value = OperandsUtils.parseOperand(value);
	}

	public Operand getValue() {
		return value;
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visitPush(this);
	}
}
