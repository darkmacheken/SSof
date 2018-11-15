package pt.ulisboa.tecnico.ssof.structure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("variables")
public class Variable {
	private int bytes;
	private String type;
	private String name;
	private String address;
	
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
}