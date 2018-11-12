package pt.ulisboa.tecnico.ssof.structure;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Function {
	private int nIntructions;
	private List<Variable> variables;
	private List<Instruction> instructions;
	
	@JsonCreator
	public Function(@JsonProperty("Ninstructions") int nInstructions, 
			@JsonProperty("variables") List<Variable> variables, 
			@JsonProperty("instructions") List<Instruction> instructions) {
		this.nIntructions = nInstructions;
		this.variables = variables;
		this.instructions = instructions;
	}
	
	public int getnIntructions() {
		return nIntructions;
	}
		
	public List<Variable>  getVariables() {
		return variables;
	}
	
	public List<Instruction> getInstructions() {
		return instructions;
	}
}
