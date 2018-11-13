package pt.ulisboa.tecnico.ssof.structure.graph;

import java.util.LinkedList;
import java.util.List;
import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class BasicBlock {
    private final List<BasicBlock> adjacencyList;

    private final int number;
    private final List<Instruction> instructions;

    // Address of the first instruction
    private String address;

    public BasicBlock(int number, String address) {
        this.adjacencyList = new LinkedList<>();
        this.number = number;
        this.address = address;
        this.instructions = new LinkedList<>();
    }

    public List<BasicBlock> getAdjacencyList() {
        return adjacencyList;
    }

    public int getNumber() {
        return number;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void addInstruction(Instruction instruction){
        this.instructions.add(instruction);
    }

    public String getAddress() {
        return address;
    }

    public void addAdjacencyBlock(BasicBlock basicBlock){
        this.adjacencyList.add(basicBlock);
    }

    public void accept(Visitor visitor){
        visitor.visitBasicBlock(this);
    }
}
