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
		
		searchGetsVulnerabilities(stackMemory, variable, size);
		
		return vulnerabilities.stream()
				.filter(Objects::nonNull)
		        .distinct()
		        .map(vuln -> {vuln.setFunctionName("fgets"); 
					vuln.setAddress(InstructionPointer); 
					return vuln;
				}).collect(Collectors.toList());
	}
		
	
	public static List<Vulnerability> gets(Registers registers, StackMemory stackMemory, String InstructionPointer) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		Optional<Variable> variable = stackMemory.getMappedVariable(registers.read("rdi"));
		Long size = Long.MAX_VALUE;
		
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
	
	private static List<Vulnerability> searchGetsVulnerabilities(StackMemory stackMemory, Optional<Variable> variable, Long size) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		
		Vulnerability vulnerability;
		for(int i = 0; i < Math.toIntExact(size) - 1; i++) {
			vulnerability = stackMemory.writeByte(variable.get(), variable.get().getRelativeAddress() - i, 0xFFL);
			vulnerabilities.add(vulnerability);
			if(vulnerability != null &&
				StringUtils.equals(vulnerability.getVulnerabilityType(), "SCORRUPTION")) {
				break;
			}
		}
		vulnerability = stackMemory.writeByte(variable.get(), variable.get().getRelativeAddress() - Math.toIntExact(size), 0x00L);
		vulnerabilities.add(vulnerability);
		
		return vulnerabilities;
	}
	
}
