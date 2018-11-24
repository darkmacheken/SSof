package pt.ulisboa.tecnico.ssof.structure.graph;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.tuple.MutablePair;
import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.structure.operations.Function;
import pt.ulisboa.tecnico.ssof.structure.operations.Program;
import pt.ulisboa.tecnico.ssof.visitor.FlowGraph;
import pt.ulisboa.tecnico.ssof.visitor.PickLeaders;

public class Graph{
    private final Map<String, BasicBlock> basicBlocks;
    private BasicBlock mainBlockEntry;

    public Graph(){
        this.basicBlocks = new TreeMap<>();
    }

    public void generateGraph(Program program) {
        Map<String, MutablePair<Boolean, Instruction>> instructionBlocks = new TreeMap<>();

        // All instructions are basic blocks at the beginning
        // Tree Map: key = Address => < Leader, Instruction >
        program.getFunctions().values().stream()
               .map(Function::getInstructions)
               .flatMap(List::stream)
               .forEach(instruction -> instructionBlocks.put(
                       instruction.getAddress(),
                       new MutablePair<Boolean, Instruction>(false, instruction)));

        // Need to pick the leaders. Leaders are set to true.
        // A leader is the first instruction of the block.
        PickLeaders pickLeaders = new PickLeaders(instructionBlocks);
        instructionBlocks.values().stream().forEach(pair -> pair.getRight().accept(pickLeaders));

        // The leaders are chosen, now each basic block goes from a leader to the next.
        int blockCounter = 0;
        BasicBlock currentBlock = null;
        for(Map.Entry<String, MutablePair<Boolean, Instruction>> entry: instructionBlocks.entrySet()){
            MutablePair<Boolean, Instruction> pair = entry.getValue();

            if(currentBlock != null && pair.getLeft()){
                this.addBasicBlock(currentBlock.getAddress(), currentBlock);
                currentBlock = new BasicBlock(blockCounter++, entry.getKey());
            }

            if(currentBlock == null && pair.getLeft()){
                currentBlock = new BasicBlock(blockCounter++, entry.getKey());
            }
            if(currentBlock != null){
                currentBlock.addInstruction(pair.getRight());
            }
        }
        // Add last block
        if(currentBlock != null){
            this.addBasicBlock(currentBlock.getAddress(), currentBlock);
        }

        // We got our Basic Blocks, now we just need to create the Flow Graph
        String mainAddress = program.getFunctions().get("main").getInstructions().get(0).getAddress();
        this.mainBlockEntry = this.basicBlocks.get(mainAddress);
        FlowGraph flowGraph = new FlowGraph(this.basicBlocks, this.mainBlockEntry);
        flowGraph.generateGraph();
    }

    public Map<String, BasicBlock> getBasicBlocks() {
        return basicBlocks;
    }

    public void addBasicBlock(String address, BasicBlock basicNode){
        basicBlocks.put(address, basicNode);
    }

    public BasicBlock getMainBlockEntry() {
        return mainBlockEntry;
    }
}
