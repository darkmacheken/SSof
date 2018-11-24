package pt.ulisboa.tecnico.ssof.structure.operands;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.ssof.memory.Registers;
import pt.ulisboa.tecnico.ssof.memory.StackMemory;
import pt.ulisboa.tecnico.ssof.structure.Vulnerability;

public class Pointer implements Operand {
	private static final Logger logger = Logger.getLogger(Pointer.class);

	private String registerName;
	private int offset;
	private int wordSize;
	
	public Pointer(String registerName, int offset) {
		this.registerName = registerName;
		this.offset = offset;
	}
	
	public Pointer(String registerName, int offset, int wordSize) {
		this.registerName = registerName;
		this.offset = offset;
		this.wordSize = wordSize;
	}

	@Override
	public Long getValue(Registers registers, StackMemory stackMemory) {
	    if(StringUtils.equals(this.registerName, "rbp")){
            return stackMemory.readValue(this.offset, 8);
        } else {
	        logger.warn("GetValue called in pointer with different register name of rbp.");
	        return 0L;
        }
	}

	@Override
	public Long getAddress(Registers registers, StackMemory stackMemory) {
		return stackMemory.getIndex(this.offset);
	}

    @Override
    public List<Vulnerability> setValue(Registers registers, StackMemory stackMemory, Long value) {
        if(StringUtils.equals(this.registerName, "rbp")){
            return stackMemory.writeValue(this.offset, this.wordSize, value);
        } else {
            logger.warn("SetValue called in pointer with different register name of rbp.");
            return new ArrayList<>();
        }

    }

    public String getRegisterName() {
		return this.registerName;
	}

	public int getOffset() {
		return this.offset;
	}

	public int getWordSize() {
		return this.wordSize;
	}
}
