package pt.ulisboa.tecnico.ssof.structure.operands;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.ssof.memory.Registers;
import pt.ulisboa.tecnico.ssof.memory.StackMemory;
import pt.ulisboa.tecnico.ssof.structure.Vulnerability;

public class Register implements Operand {
    private static final Logger logger = Logger.getLogger(Register.class);

	private String name;
	
	public Register(String name) {
		this.name = name;
	}

	@Override
	public Long getValue(Registers registers, StackMemory stackMemory) {
		return registers.read(this.name);
	}

	@Override
	public Long getAddress(Registers registers, StackMemory stackMemory) {
	    logger.error("GetAddress was called on register.");
		return 0L;
	}

    @Override
    public List<Vulnerability> setValue(Registers registers, StackMemory stackMemory, Long value) {
        registers.write(this.name, value);
        return new ArrayList<>();
    }

    public String getName() {
		return this.name;
	}
}