package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Test extends BinaryInstruction {

	public Test(int position, String address, String firstArgument, String secondArgument) {
		super(position, address, firstArgument, secondArgument);
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visitTest(this);
	}
}