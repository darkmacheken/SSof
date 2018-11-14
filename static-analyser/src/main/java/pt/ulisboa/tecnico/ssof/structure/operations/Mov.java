package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Mov extends BinaryInstruction {
	private String observations;

	public Mov(int position, String address, String destination, String value, String observations) {
		super(position, address, destination, value);
		this.observations = observations;
	}
	
	public String getObservations() {
		return observations;
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visitMov(this);
	}
}
