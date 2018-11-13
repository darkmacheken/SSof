package pt.ulisboa.tecnico.ssof.visitor;

import pt.ulisboa.tecnico.ssof.structure.graph.BasicBlock;
import pt.ulisboa.tecnico.ssof.structure.operations.Function;
import pt.ulisboa.tecnico.ssof.structure.operations.Program;
import pt.ulisboa.tecnico.ssof.structure.operations.Add;
import pt.ulisboa.tecnico.ssof.structure.operations.Call;
import pt.ulisboa.tecnico.ssof.structure.operations.Cmp;
import pt.ulisboa.tecnico.ssof.structure.operations.Je;
import pt.ulisboa.tecnico.ssof.structure.operations.Jmp;
import pt.ulisboa.tecnico.ssof.structure.operations.Jne;
import pt.ulisboa.tecnico.ssof.structure.operations.Lea;
import pt.ulisboa.tecnico.ssof.structure.operations.Leave;
import pt.ulisboa.tecnico.ssof.structure.operations.Mov;
import pt.ulisboa.tecnico.ssof.structure.operations.Nop;
import pt.ulisboa.tecnico.ssof.structure.operations.Push;
import pt.ulisboa.tecnico.ssof.structure.operations.Ret;
import pt.ulisboa.tecnico.ssof.structure.operations.Sub;
import pt.ulisboa.tecnico.ssof.structure.operations.Test;

public interface Visitor {
    void visitBasicBlock(BasicBlock basicBlock);

    void visitProgram(Program program);

    void visitFunction(Function function);

    void visitAdd(Add add);

    void visitCall(Call call);

    void visitCmp(Cmp cmp);

    void visitJe(Je je);

    void visitJmp(Jmp jmp);

    void visitJne(Jne jne);

    void visitLea(Lea lea);

    void visitLeave(Leave leave);

    void visitMov(Mov mov);

    void visitNop(Nop nop);

    void visitPush(Push push);

    void visitRet(Ret ret);

    void visitSub(Sub sub);

    void visitTest(Test test);
}
