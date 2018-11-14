package pt.ulisboa.tecnico.ssof.structure.operands;

public class Pointer extends Operand {
	private String registerName;
	private int offset;
	private String wordSize;
	
	public Pointer(String registerName, int offset) {
		this.registerName = registerName;
		this.offset = offset;
	}
	
	public Pointer(String registerName, int offset, String wordSize) {
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

	public String getWordSize() {
		return this.wordSize;
	}

	@Override
	protected Long getOperandValue() {
		// TODO Auto-generated method stub
		return null;
	}
}
