package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Cmp extends Instruction {
	private String firstArgument ;
	private String secondArgument;

	public Cmp(int position, String address, String firstArgument , String secondArgument) {
		super(position, address);
		this.firstArgument  = firstArgument;
		this.secondArgument = secondArgument;
	}

	public String getFirstArgument () {
		return firstArgument ;
	}

	public String getSecondArgument() {
		return secondArgument;
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visitCmp(this);
	}
}
