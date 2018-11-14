package pt.ulisboa.tecnico.ssof.visitor;

import java.util.Map;
import org.apache.commons.lang3.tuple.MutablePair;
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

public class PickLeaders implements Visitor {
    private static final Logger logger = Logger.getLogger(PickLeaders.class);

    private final Map<String, MutablePair<Boolean, Instruction>> instructionsBlocks;
    private boolean nextInstructionMustBeLeader = false;

    public PickLeaders(Map<String, MutablePair<Boolean, Instruction>> instructionBlocks) {
        this.instructionsBlocks = instructionBlocks;
    }

    @Override
    public void visitBasicBlock(BasicBlock basicBlock) {
        logger.error("Visit Basic Block in Pick Leaders.");
    }

    @Override
    public void visitProgram(Program program) {
        logger.error("Visit Program in Pick Leaders.");
    }

    @Override
    public void visitFunction(Function function) {
        logger.error("Visit Function in Pick Leaders.");
    }

    @Override
    public void visitAdd(Add add) {
        this.visitNormalInstruction(add);
    }

    @Override
    public void visitCall(Call call) {
        String callAddress = call.getFunctionAddress();
        if(this.instructionsBlocks.containsKey(callAddress)){
            this.instructionsBlocks.get(callAddress).setLeft(true);
        }
        this.nextInstructionMustBeLeader = true;
    }

    @Override
    public void visitCmp(Cmp cmp) {
        this.visitNormalInstruction(cmp);
    }

    @Override
    public void visitJe(Je je) {
        String jumpAddress = je.getJumpAddress();
        if(this.instructionsBlocks.containsKey(jumpAddress)){
            this.instructionsBlocks.get(jumpAddress).setLeft(true);
            this.nextInstructionMustBeLeader = true;
        } else {
            logger.error("JE jumps to an unknown place.");
        }
    }

    @Override
    public void visitJmp(Jmp jmp) {
        String jumpAddress = jmp.getJumpAddress();
        if(this.instructionsBlocks.containsKey(jumpAddress)){
            this.instructionsBlocks.get(jumpAddress).setLeft(true);
            this.nextInstructionMustBeLeader = true;
        } else {
            logger.error("JMP jumps to an unknown place.");
        }
    }

    @Override
    public void visitJne(Jne jne) {
        String jumpAddress = jne.getJumpAddress();
        if(this.instructionsBlocks.containsKey(jumpAddress)){
            this.instructionsBlocks.get(jumpAddress).setLeft(true);
            this.nextInstructionMustBeLeader = true;
        } else {
            logger.error("JNE jumps to an unknown place.");
        }
    }

    @Override
    public void visitLea(Lea lea) {
        this.visitNormalInstruction(lea);
    }

    @Override
    public void visitLeave(Leave leave) {
        this.visitNormalInstruction(leave);
    }

    @Override
    public void visitMov(Mov mov) {
        this.visitNormalInstruction(mov);
    }

    @Override
    public void visitNop(Nop nop) {
        this.visitNormalInstruction(nop);
    }

    @Override
    public void visitPush(Push push) {
        this.visitNormalInstruction(push);
    }

    @Override
    public void visitRet(Ret ret) {
        this.nextInstructionMustBeLeader = true;
    }

    @Override
    public void visitSub(Sub sub) {
        this.visitNormalInstruction(sub);
    }

    @Override
    public void visitTest(Test test) {
        this.visitNormalInstruction(test);
    }

    private void visitNormalInstruction(Instruction instruction){
        if (instruction.getPosition() == 0 || this.nextInstructionMustBeLeader){
            this.instructionsBlocks.get(instruction.getAddress()).setLeft(true);
            this.nextInstructionMustBeLeader = false;
        }
    }

}
