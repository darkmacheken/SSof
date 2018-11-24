package pt.ulisboa.tecnico.ssof.structure.operands;

import java.util.List;
import pt.ulisboa.tecnico.ssof.memory.Registers;
import pt.ulisboa.tecnico.ssof.memory.StackMemory;
import pt.ulisboa.tecnico.ssof.structure.Vulnerability;

public interface Operand {
    Long getValue(Registers registers, StackMemory stackMemory);

    Long getAddress(Registers registers, StackMemory stackMemory);

    List<Vulnerability> setValue(Registers registers, StackMemory stackMemory, Long value);
}
