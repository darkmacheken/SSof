package pt.ulisboa.tecnico.ssof.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.ssof.memory.Registers;
import pt.ulisboa.tecnico.ssof.memory.StackMemory;
import pt.ulisboa.tecnico.ssof.structure.Vulnerabilities;
import pt.ulisboa.tecnico.ssof.structure.Vulnerability;
import pt.ulisboa.tecnico.ssof.structure.VulnerableFunctions;
import pt.ulisboa.tecnico.ssof.structure.graph.BasicBlock;
import pt.ulisboa.tecnico.ssof.structure.operands.Register;
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

public class Executor implements Visitor {
    private static final Logger logger = Logger.getLogger(Executor.class);

    private final Map<String, Function> functions;
    private final Registers registers;
    private final StackMemory memory;

    private final List<Vulnerability> vulnerabilities;

    public Executor(Map<String, Function> functions){
        this.functions = functions;
        this.registers = new Registers();
        this.memory = new StackMemory();
        this.vulnerabilities = new ArrayList<>();

        memory.startStackFrame(functions.get("main"));
    }

    public Executor(Executor executor){
        this.functions = executor.functions;
        this.registers = new Registers(executor.registers);
        this.memory = new StackMemory(executor.memory);
        this.vulnerabilities = new ArrayList<>();
    }

    @Override
    public void visitBasicBlock(BasicBlock basicBlock) {
        basicBlock.getInstructions().forEach(instruction -> instruction.accept(this));

        switch(basicBlock.getAdjacencyList().size()){
            case 0:
                break;
            case 1:
                basicBlock.getAdjacencyList().get(0).accept(this);
                break;
            default:
                for (BasicBlock basicBlockl : basicBlock.getAdjacencyList()) {
                    Executor executor = new Executor(this);
                    basicBlockl.accept(executor);
                    this.vulnerabilities.addAll(executor.getVulnerabilities().getVulnerabilitiesList());
                }
        }
    }

    @Override
    public void visitProgram(Program program) {
        logger.error("Visit Program in Executor.");
    }

    @Override
    public void visitFunction(Function function) {
        logger.error("Visit Function in Executor.");
    }

    @Override
    public void visitAdd(Add add) {
        if(add.getFirstArgument() instanceof Register &&
                StringUtils.equals(((Register) add.getFirstArgument()).getName(),"rsp")){
            Long value = add.getSecondArgument().getValue(registers, memory);
            memory.allocateAndMap(Math.toIntExact(Math.abs(value)));
            registers.write("rsp", memory.getStackPointer());
        } else {
          Long valueFirst = add.getFirstArgument().getValue(registers, memory);
          Long valueSecond = add.getSecondArgument().getValue(registers, memory);
          add.getFirstArgument().setValue(registers, memory, valueFirst + valueSecond);
        }
    }

    @Override
    public void visitCall(Call call) {
        String name = call.getFunctionName();
        switch (name){
            case "<gets@plt>":
                vulnerabilities.addAll(
                        VulnerableFunctions.gets(registers, memory, call.getAddress()));
                break;
            case "<fgets@plt>":
                vulnerabilities.addAll(
                        VulnerableFunctions.fgets(registers, memory, call.getAddress()));
                break;
            case "<strcpy@plt>":
                vulnerabilities.addAll(
                        VulnerableFunctions.strcpy(registers, memory, call.getAddress()));
                break;
            case "<strncpy@plt>":
                vulnerabilities.addAll(
                        VulnerableFunctions.strncpy(registers, memory, call.getAddress()));
                break;
            case "<strcat@plt>":
                vulnerabilities.addAll(
                        VulnerableFunctions.strcat(registers, memory, call.getAddress()));
                break;
            case "<strncat@plt>":
                vulnerabilities.addAll(
                        VulnerableFunctions.strncat(registers, memory, call.getAddress()));
                break;
            case "<sprintf@plt>":
                break;
            case "<snprintf@plt>":
                break;
            case "<__isoc99_scanf@plt>":
            	vulnerabilities.addAll(
                        VulnerableFunctions.scanf(registers, memory, call.getAddress()));
                break;
            case "<__isoc99_fscanf@plt>":
            	vulnerabilities.addAll(
                        VulnerableFunctions.fscanf(registers, memory, call.getAddress()));
                break;
            case "<read@plt>":
                break;
            default:
                memory.startStackFrame(functions.get(name.substring(1, name.length() - 1)));
        }
    }

    @Override
    public void visitCmp(Cmp cmp) {
        //nothing to do here
    }

    @Override
    public void visitJe(Je je) {
        //nothing to do here
    }

    @Override
    public void visitJmp(Jmp jmp) {
        //nothing to do here
    }

    @Override
    public void visitJne(Jne jne) {
        //nothing to do here
    }

    @Override
    public void visitLea(Lea lea) {
        Long address = lea.getSecondArgument().getAddress(registers, memory);
        lea.getFirstArgument().setValue(registers, memory, address);
    }

    @Override
    public void visitLeave(Leave leave) {
        memory.deallocate();
        registers.write("rsp", memory.getStackPointer());
    }

    @Override
    public void visitMov(Mov mov) {
        Long value = mov.getSecondArgument().getValue(registers, memory);
        List<Vulnerability> vulns = mov.getFirstArgument().setValue(registers, memory, value).stream()
                .filter(Objects::nonNull)
                .distinct()
                .peek(vulnerability -> {
                    vulnerability.setAddress(mov.getAddress());
                    vulnerability.setOperation("mov");
                }).collect(Collectors.toList());

        this.vulnerabilities.addAll(vulns);
    }

    @Override
    public void visitNop(Nop nop) {
        //nothing to do here
    }

    @Override
    public void visitPush(Push push) {
        if(push.getValue() instanceof Register &&
           StringUtils.equals(((Register)push.getValue()).getName(), "rbp")){
            memory.pushRbp();
        } else {
            Long value = push.getValue().getValue(registers, memory);
            memory.push(value);
            memory.mapMemory();
        }
        registers.write("rsp", memory.getStackPointer());
    }

    @Override
    public void visitRet(Ret ret) {
        memory.endStackFrame();
        registers.write("rsp", memory.getStackPointer());
    }

    @Override
    public void visitSub(Sub sub) {
        if(sub.getFirstArgument() instanceof Register &&
           StringUtils.equals(((Register) sub.getFirstArgument()).getName(),"rsp")){
            Long value = sub.getSecondArgument().getValue(registers, memory);
            memory.allocateAndMap(Math.toIntExact(value));
            registers.write("rsp", memory.getStackPointer());
        } else {
            Long valueFirst = sub.getFirstArgument().getValue(registers, memory);
            Long valueSecond = sub.getSecondArgument().getValue(registers, memory);
            sub.getFirstArgument().setValue(registers, memory, valueFirst + valueSecond);
        }

    }

    @Override
    public void visitTest(Test test) {
        //nothing to do here
    }

    public Vulnerabilities getVulnerabilities(){
        List<Vulnerability> vulns = this.vulnerabilities.stream()
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        return new Vulnerabilities(vulns);
    }
}
