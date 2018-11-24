package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Call extends Instruction {
	private String functionName;
	private String functionAddress;

	public Call(int position, String address, String functionName, String functionAddress) {
		super(position, address);
		this.functionName = functionName;
		this.functionAddress = functionAddress;
	}

	public String getFunctionName() {
		return functionName;
	}

	public String getFunctionAddress() {
		return functionAddress;
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visitCall(this);
	}
}
