package pt.ulisboa.tecnico.ssof.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.ssof.structure.Instruction;
import pt.ulisboa.tecnico.ssof.structure.graph.BasicBlock;
import pt.ulisboa.tecnico.ssof.structure.operations.Add;
import pt.ulisboa.tecnico.ssof.structure.operations.Call;
import pt.ulisboa.tecnico.ssof.structure.operations.Cmp;
import pt.ulisboa.tecnico.ssof.structure.operations.Function;
import pt.ulisboa.tecnico.ssof.structure.operations.Je;
import pt.ulisboa.tecnico.ssof.structure.operations.Jmp;
import pt.ulisboa.tecnico.ssof.structure.operations.Jne;
import pt.ulisboa.tecnico.ssof.structure.operations.Lea;
import pt.ulisboa.tecnico.ssof.structure.operations.Leave;
import pt.ulisboa.tecnico.ssof.structure.operations.Mov;
import pt.ulisboa.tecnico.ssof.structure.operations.Nop;
import pt.ulisboa.tecnico.ssof.structure.operations.Program;
import pt.ulisboa.tecnico.ssof.structure.operations.Push;
import pt.ulisboa.tecnico.ssof.structure.operations.Ret;
import pt.ulisboa.tecnico.ssof.structure.operations.Sub;
import pt.ulisboa.tecnico.ssof.structure.operations.Test;

public class FlowGraph implements Visitor {
    private static final Logger logger = Logger.getLogger(FlowGraph.class);

    private final Map<String, BasicBlock> basicBlocks;
    private final BasicBlock mainBlockEntry;
    private final List<BasicBlock> basicBlocksList;

    private BasicBlock currentBlock;
    private Stack<BasicBlock> calleeBlock = new Stack<>();

    public FlowGraph(Map<String, BasicBlock> basicBlocks, BasicBlock mainBlockEntry) {
        this.basicBlocks = basicBlocks;
        this.basicBlocksList = new ArrayList<>(basicBlocks.values());
        this.mainBlockEntry = mainBlockEntry;
    }

    @Override
    public void visitBasicBlock(BasicBlock basicBlock) {
        this.currentBlock = basicBlock;
        Optional<Instruction> lastElement = basicBlock.getInstructions().stream()
                .reduce((first, second) -> second);

        lastElement.ifPresent(instruction -> instruction.accept(this));
    }

    @Override
    public void visitProgram(Program program) {
        logger.error("Visit Program in Flow Graph.");
    }

    @Override
    public void visitFunction(Function function) {
        logger.error("Visit Function in Flow Graph.");
    }

    @Override
    public void visitAdd(Add add) {
        logger.error("Visit Add in Flow Graph.");
    }

    @Override
    public void visitCall(Call call) {
        String callAddress = call.getFunctionAddress();
        if(basicBlocks.containsKey(callAddress)){
            BasicBlock callBlock = basicBlocks.get(callAddress);
            this.currentBlock.addAdjacencyBlock(callBlock);
            this.calleeBlock.push(this.currentBlock);
            callBlock.accept(this);
        } else {
            BasicBlock nextBlock = this.basicBlocksList.get(this.currentBlock.getNumber() + 1);
            this.currentBlock.addAdjacencyBlock(nextBlock);
            nextBlock.accept(this);
        }
    }

    @Override
    public void visitCmp(Cmp cmp) {
        logger.error("Visit Cmp in Flow Graph.");
    }

    @Override
    public void visitJe(Je je) {
        String jumpAddress = je.getJumpAddress();
        if(basicBlocks.containsKey(jumpAddress)){
            visitJumpConditional(jumpAddress);
        } else {
            logger.error("JNE address unknown.");
        }
    }

    @Override
    public void visitJmp(Jmp jmp) {
        String jumpAddress = jmp.getJumpAddress();
        if(basicBlocks.containsKey(jumpAddress)){
            BasicBlock jumpBlock = basicBlocks.get(jumpAddress);
            this.currentBlock.addAdjacencyBlock(jumpBlock);
            jumpBlock.accept(this);
        } else {
            logger.error("JMP address unknown.");
        }
    }

    @Override
    public void visitJne(Jne jne) {
        String jumpAddress = jne.getJumpAddress();
        if(basicBlocks.containsKey(jumpAddress)){
            visitJumpConditional(jumpAddress);
        } else {
            logger.error("JNE address unknown.");
        }

    }

    @Override
    public void visitLea(Lea lea) {
        logger.error("Visit Lea in Flow Graph.");
    }

    @Override
    public void visitLeave(Leave leave) {
        logger.error("Visit Leave in Flow Graph.");
    }

    @Override
    public void visitMov(Mov mov) {
        logger.error("Visit Mov in Flow Graph.");
    }

    @Override
    public void visitNop(Nop nop) {
        logger.error("Visit Nop in Flow Graph.");
    }

    @Override
    public void visitPush(Push push) {
        logger.error("Visit Push in Flow Graph.");
    }

    @Override
    public void visitRet(Ret ret) {
        if(!this.calleeBlock.empty()){
            BasicBlock caleeBlock = this.calleeBlock.pop();
            BasicBlock returnBlock = this.basicBlocksList.get(caleeBlock.getNumber() + 1);
            this.currentBlock.addAdjacencyBlock(returnBlock);
            returnBlock.accept(this);
        }
    }

    @Override
    public void visitSub(Sub sub) {
        logger.error("Visit Sub in Flow Graph.");
    }

    @Override
    public void visitTest(Test test) {
        logger.error("Visit Test in Flow Graph.");
    }

    public void generateGraph() {
        this.mainBlockEntry.accept(this);
    }

    private void visitJumpConditional(String jumpAddress) {
        BasicBlock jumpBlock = basicBlocks.get(jumpAddress);
        BasicBlock nextBlock = this.basicBlocksList.get(currentBlock.getNumber() + 1);
        this.currentBlock.addAdjacencyBlock(jumpBlock);
        this.currentBlock.addAdjacencyBlock(nextBlock);
        jumpBlock.accept(this);
        nextBlock.accept(this);
    }
}
