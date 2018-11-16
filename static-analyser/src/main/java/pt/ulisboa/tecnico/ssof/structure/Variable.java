package pt.ulisboa.tecnico.ssof.structure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import pt.ulisboa.tecnico.ssof.structure.operands.Pointer;
import pt.ulisboa.tecnico.ssof.utils.OperandsUtils;

@JsonRootName("variables")
public class Variable {
	private int bytes;
	private String type;
	private String name;
	private String address;

	public Variable() {}

	@JsonCreator
	public Variable(@JsonProperty("bytes") int bytes, 
			@JsonProperty("type") String type, 
			@JsonProperty("name") String name, 
			@JsonProperty("address") String address) {
		this.bytes = bytes;
		this.type = type;
		this.name = name;
		this.address = address;
	}
	
	public int getBytes() {
		return bytes;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getRelativeAddress() {
        Pointer pointer = (Pointer) OperandsUtils.parseOperand("[" + address + "]");
		return pointer.getOffset();
	}

	public void incrementBytes(){
		this.bytes += 1;
	}
}