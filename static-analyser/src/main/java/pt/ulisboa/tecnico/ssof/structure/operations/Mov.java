package pt.ulisboa.tecnico.ssof.structure.operations;

import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Mov extends Instruction {
	private String destination;
	private String value;
	private String observations;

	public Mov(int position, String address, String destination, String value, String observations) {
		super(position, address);
		this.destination = destination;
		this.value = value;
		this.observations = observations;
	}

	public String getDestination() {
		return destination;
	}

	public String getValue() {
		return value;
	}
	
	public String getObservations() {
		return observations;
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visitMov(this);
	}
}
