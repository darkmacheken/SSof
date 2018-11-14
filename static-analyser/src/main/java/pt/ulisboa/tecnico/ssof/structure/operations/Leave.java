package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Leave extends Instruction {

	public Leave(int position, String address) {
		super(position, address);
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visitLeave(this);
	}
}
