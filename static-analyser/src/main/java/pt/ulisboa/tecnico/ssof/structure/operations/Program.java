package pt.ulisboa.tecnico.ssof.structure.operations;

import java.util.Map;
import pt.ulisboa.tecnico.ssof.visitor.Visitor;

public class Program {
    private final Map<String, Function> functions;

    public Program(Map<String, Function> functions) {
        this.functions = functions;
        this.functions.forEach((functionName, function) -> function.setName(functionName));
    }

    public Map<String, Function> getFunctions() {
        return functions;
    }

    public void accept(Visitor visitor) {
        visitor.visitProgram(this);
    }
}
