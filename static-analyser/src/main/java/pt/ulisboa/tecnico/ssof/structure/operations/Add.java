package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Add extends BinaryInstruction {

	public Add(int position, String address, String destination, String value) {
		super(position, address, destination, value);
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visitAdd(this);
	}
}
