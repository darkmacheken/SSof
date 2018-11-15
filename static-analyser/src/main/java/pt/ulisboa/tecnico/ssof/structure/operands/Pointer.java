package pt.ulisboa.tecnico.ssof.structure.operands;

public class Pointer extends Operand {
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
