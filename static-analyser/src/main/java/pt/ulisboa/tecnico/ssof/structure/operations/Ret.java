package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Ret extends Instruction {

	public Ret(int position, String address) {
		super(position, address);
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visitRet(this);
	}
}
