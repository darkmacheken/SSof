package pt.ulisboa.tecnico.ssof.structure.operations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.structure.Variable;
import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Function {
	private String name;
	private int numberInstructions;
	private List<Variable> variables;
	private List<Instruction> instructions;
	
	@JsonCreator
	public Function(@JsonProperty("Ninstructions") int numberInstructions, 
			@JsonProperty("variables") List<Variable> variables, 
			@JsonProperty("instructions") List<Instruction> instructions) {
		this.numberInstructions = numberInstructions;
		this.variables = variables;
		this.instructions = instructions;
	}
	
	public int getNumberInstructions() {
		return numberInstructions;
	}
		
	public List<Variable> getVariables() {
		return variables;
	}
	
	public List<Instruction> getInstructions() {
		return instructions;
	}

    public void accept(Visitor visitor){
        visitor.visitFunction(this);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
