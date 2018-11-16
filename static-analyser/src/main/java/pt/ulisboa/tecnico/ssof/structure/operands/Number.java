package pt.ulisboa.tecnico.ssof.structure.operands;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.ssof.memory.Registers;
import pt.ulisboa.tecnico.ssof.memory.StackMemory;
import pt.ulisboa.tecnico.ssof.structure.Vulnerability;

public class Number implements Operand {
	private static final Logger logger = Logger.getLogger(Number.class);

	private long value;
	
	public Number(long value) {
		this.value = value;
	}

	@Override
	public Long getValue(Registers registers, StackMemory stackMemory) {
		return this.value;
	}

	@Override
	public Long getAddress(Registers registers, StackMemory stackMemory) {
		logger.error("GetAddress was called on number.");
		return 0L;
	}

    @Override
    public List<Vulnerability> setValue(Registers registers, StackMemory stackMemory, Long value) {
        logger.error("SetValue was called on number.");
        return new ArrayList<>();
    }
}
