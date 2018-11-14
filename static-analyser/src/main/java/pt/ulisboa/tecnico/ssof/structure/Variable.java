package pt.ulisboa.tecnico.ssof.structure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

@JsonRootName("variables")
public class Variable {
	private int bytes;
	private String type;
	private String name;
	private String address;
	// relative address to rbp (it has the opposite signal)
	private int relativeAddress;

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
		this.relativeAddress = - NumberUtils.toInt(StringUtils.remove(address, "rbp"), 1);
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
		return relativeAddress;
	}

	public void incrementBytes(){
		this.bytes += 1;
	}
}