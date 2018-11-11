package pt.ulisboa.tecnico.ssof.structure;

import java.util.List;

public class Function {
	private String name;
	private int nIntructions;
	private List<Variable> variables;
	private List<Instruction> instructions;
	
	public Function(String name, int nInstructions, 
			List<Variable> variables, List<Instruction> instructions) {
		this.name = name;
		this.nIntructions = nInstructions;
		this.variables = variables;
		this.instructions = instructions;
		
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getnIntructions() {
		return nIntructions;
	}
	
	public void setnIntructions(int nIntructions) {
		this.nIntructions = nIntructions;
	}
	
	public List<Variable> getVariables() {
		return variables;
	}
	
	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}
	
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}
	
	
	
}
