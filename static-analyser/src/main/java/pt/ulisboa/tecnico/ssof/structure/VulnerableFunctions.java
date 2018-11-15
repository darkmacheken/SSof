package pt.ulisboa.tecnico.ssof.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import pt.ulisboa.tecnico.ssof.memory.Registers;
import pt.ulisboa.tecnico.ssof.memory.StackMemory;

public final class VulnerableFunctions {
	private static final Logger logger = Logger.getLogger(VulnerableFunctions.class);

	
	public static List<Vulnerability> fgets(Registers registers, StackMemory stackMemory, String InstructionPointer) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		Optional<Variable> variable = stackMemory.getMappedVariable(registers.read("rdi"));
		Long size = registers.read("rsi");
		
		if(variable.isPresent()) {
			Vulnerability vulnerability;
			for(int i = 0; i < Math.toIntExact(size) - 1; i++) {
				vulnerability = stackMemory.writeByte(variable.get(), variable.get().getRelativeAddress() - i, 0xFFL);
				if (vulnerability != null) {
					vulnerabilities.add(vulnerability);
					if(vulnerability.getVulnerabilityType().equals("SCORRUPTION")) {
						break;
					}
				}
			}
			vulnerability = stackMemory.writeByte(variable.get(), variable.get().getRelativeAddress() - Math.toIntExact(size), 0x00L);
			if (vulnerability != null) {
				vulnerabilities.add(vulnerability);
			}
		} else {
			logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
			System.exit(-1);
		}	
		
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
		
		if(variable.isPresent()) {
			Vulnerability vulnerability;
			for(int i = 0; i < Math.toIntExact(size) - 1; i++) {
				vulnerability = stackMemory.writeByte(variable.get(), variable.get().getRelativeAddress() - i, 0xFFL);
				if (vulnerability != null) {
					vulnerabilities.add(vulnerability);
					if(vulnerability.getVulnerabilityType().equals("SCORRUPTION")) {
						break;
					}
				}
			}
		} else {
			logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
			System.exit(-1);
		}	
		
		return vulnerabilities.stream()
				.filter(Objects::nonNull)
		        .distinct()
		        .map(vuln -> {
		        	vuln.setFunctionName("gets"); 
					vuln.setAddress(InstructionPointer); 
					return vuln;
				}).collect(Collectors.toList());
	}
}
