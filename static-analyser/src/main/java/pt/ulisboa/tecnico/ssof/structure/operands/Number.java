package pt.ulisboa.tecnico.ssof.structure.operands;

public class Number implements Operand {
	private long value;
	
	public Number(long value) {
		this.value = value;
	}

	public long getValue() {
		return this.value;
	}
}