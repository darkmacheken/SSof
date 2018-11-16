package pt.ulisboa.tecnico.ssof.memory;

import pt.ulisboa.tecnico.ssof.structure.Variable;

class MemoryPosition {
    private Variable variable;
    private byte content;

    MemoryPosition(Variable variable, byte content) {
        this.variable = variable;
        this.content = content;
    }

    MemoryPosition(MemoryPosition memoryPosition){
        this.variable = memoryPosition.getVariable();
        this.content = memoryPosition.getContent();
    }

    Variable getVariable() {
        return variable;
    }

    void setVariable(Variable variable) {
        this.variable = variable;
    }

    byte getContent() {
        return content;
    }

    void setContent(byte content) {
        this.content = content;
    }
}
