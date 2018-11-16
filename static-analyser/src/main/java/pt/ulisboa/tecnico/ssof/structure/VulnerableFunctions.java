package pt.ulisboa.tecnico.ssof.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import pt.ulisboa.tecnico.ssof.memory.Registers;
import pt.ulisboa.tecnico.ssof.memory.StackMemory;

public final class VulnerableFunctions {
	private static final Logger logger = Logger.getLogger(VulnerableFunctions.class);
	
	public static List<Vulnerability> fgets(Registers registers, StackMemory stackMemory, String InstructionPointer) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		Optional<Variable> variable = stackMemory.getMappedVariable(registers.read("rdi"));
		Long size = registers.read("rsi");
		
		if(!variable.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
			System.exit(-1);
		}
		
		vulnerabilities = searchGetsVulnerabilities(stackMemory, variable, size);
		
		return vulnerabilities.stream()
				.filter(Objects::nonNull)
		        .distinct()
		        .peek(vuln -> {vuln.setFunctionName("fgets"); 
					vuln.setAddress(InstructionPointer); 
				}).collect(Collectors.toList());
	}	
	
	public static List<Vulnerability> gets(Registers registers, StackMemory stackMemory, String InstructionPointer) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		Optional<Variable> variable = stackMemory.getMappedVariable(registers.read("rdi"));
		Long size = Long.valueOf(Integer.MAX_VALUE);
		
		if(!variable.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
			System.exit(-1);
		}
		
		vulnerabilities = searchGetsVulnerabilities(stackMemory, variable, size);
		
		return vulnerabilities.stream()
				.filter(Objects::nonNull)
		        .distinct()
		        .peek(vuln -> {
		        	vuln.setFunctionName("gets"); 
					vuln.setAddress(InstructionPointer); 
				}).collect(Collectors.toList());
	}
	
	public static List<Vulnerability> strcpy(Registers registers, StackMemory stackMemory, String InstructionPointer) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		Optional<Variable> variableDestination = stackMemory.getMappedVariable(registers.read("rdi"));
		Optional<Variable> variableSource = stackMemory.getMappedVariable(registers.read("rsi"));
		
		if(!variableDestination.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
			System.exit(-1);
		}
		if(!variableSource.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rsi") + " not found.");
			System.exit(-1);
		}
		int limit = variableSource.get().getBytes();
		vulnerabilities = searchStrcpyVulnerabilities(stackMemory, variableSource.get(), 
			variableDestination.get(), (long) limit);
		
		return vulnerabilities.stream()
				.filter(Objects::nonNull)
		        .distinct()
		        .peek(vuln -> {
		        	vuln.setFunctionName("strcpy"); 
					vuln.setAddress(InstructionPointer); 
				}).collect(Collectors.toList());
	}
	
	public static List<Vulnerability> strncpy(Registers registers, StackMemory stackMemory, String InstructionPointer) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		Optional<Variable> variableDestination = stackMemory.getMappedVariable(registers.read("rdi"));
		Optional<Variable> variableSource = stackMemory.getMappedVariable(registers.read("rsi"));
		
		if(!variableDestination.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
			System.exit(-1);
		}
		if(!variableSource.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rsi") + " not found.");
			System.exit(-1);
		}
		Long size = registers.read("rdx");
		vulnerabilities = searchStrcpyVulnerabilities(stackMemory, variableSource.get(), 
			variableDestination.get(), size);
		
		return vulnerabilities.stream()
				.filter(Objects::nonNull)
		        .distinct()
		        .peek(vuln -> {
		        	vuln.setFunctionName("strncpy"); 
					vuln.setAddress(InstructionPointer); 
				}).collect(Collectors.toList());
	}
	
	private static List<Vulnerability> searchStrcpyVulnerabilities(StackMemory stackMemory, 
			Variable variableSource, Variable variableDestination, Long limit) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		/* contentSize stores the content of the sourceVariable to copy */
		int contentSize = Integer.min(variableSource.getBytes(), Math.toIntExact(limit));
		
		Vulnerability vulnerability;
		int i;
		for(i = 0; i < contentSize; i++) {
			Long content = stackMemory.readByte(variableSource.getRelativeAddress() + i);
			vulnerability = stackMemory.writeByte(variableDestination, 
				variableDestination.getRelativeAddress() + i, content);
			vulnerabilities.add(vulnerability);
			if(vulnerability != null &&
				StringUtils.equals(vulnerability.getVulnerabilityType(), "SCORRUPTION")) {
				break;
			}
		}
		/*If the length of sourceVariable is less than n, strncpy() writes additional null bytes to dest 
		 * to ensure that a total of n bytes are written. In strcpy, the n is equal to the sourceVariable size
		 * so this loop is not performed. */
		for( ; i < Math.toIntExact(limit); i++) {
			vulnerability = stackMemory.writeByte(variableDestination, 
					variableDestination.getRelativeAddress() + i, 0x00L);
				vulnerabilities.add(vulnerability);
				if(vulnerability != null &&
					StringUtils.equals(vulnerability.getVulnerabilityType(), "SCORRUPTION")) {
					break;
				}
		}
		return vulnerabilities;
	}
	
	private static List<Vulnerability> searchGetsVulnerabilities(StackMemory stackMemory, Optional<Variable> variable, Long size) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		boolean scorruption = false;
		
		Vulnerability vulnerability;
		for(int i = 0; i < Math.toIntExact(size) - 1; i++) {
			vulnerability = stackMemory.writeByte(variable.get(), variable.get().getRelativeAddress() + i, 0xFFL);
			if(vulnerability != null &&
				StringUtils.equals(vulnerability.getVulnerabilityType(), "SCORRUPTION")) {
				scorruption = true;
 				vulnerabilities.add(vulnerability);
				break;
			}
			vulnerabilities.add(vulnerability);
		}
		if(scorruption) {
 			return vulnerabilities;
 		}
		vulnerability = stackMemory.writeByte(variable.get(), variable.get().getRelativeAddress() - Math.toIntExact(size), 0x00L);
		vulnerabilities.add(vulnerability);
		
		return vulnerabilities;
	}
}
