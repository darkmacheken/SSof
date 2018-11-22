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

	private VulnerableFunctions(){}

	public static List<Vulnerability> gets(Registers registers, StackMemory stackMemory, String instructionPointer) {
		List<Vulnerability> vulnerabilities;
		Optional<Variable> variable = stackMemory.getMappedVariable(registers.read("rdi"));
		Long size = (long) Integer.MAX_VALUE;

		if(!variable.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
			System.exit(-1);
		}

		vulnerabilities = searchGetsVulnerabilities(stackMemory, variable.get(), size, false);
		
		return filterVulnerabilities(vulnerabilities, "gets", instructionPointer);
	}
	
	public static List<Vulnerability> fgets(Registers registers, StackMemory stackMemory, String instructionPointer) {
		List<Vulnerability> vulnerabilities;
		Optional<Variable> variable = stackMemory.getMappedVariable(registers.read("rdi"));
		Long size = registers.read("rsi");

		if(!variable.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
			System.exit(-1);
		}

		vulnerabilities = searchGetsVulnerabilities(stackMemory, variable.get(), size, false);

		return filterVulnerabilities(vulnerabilities, "fgets", instructionPointer);
	}

	public static List<Vulnerability> strcpy(Registers registers, StackMemory stackMemory, String instructionPointer) {
		List<Vulnerability> vulnerabilities;
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

		int n = strlen(stackMemory, variableSource.get()) + 1;
		vulnerabilities = strncpy(stackMemory,false,
                variableDestination.get(),
                variableDestination.get().getRelativeAddress(),
                variableSource.get().getRelativeAddress(),
                n);
		
		return filterVulnerabilities(vulnerabilities, "strcpy", instructionPointer);
	}

	public static List<Vulnerability> strncpy(Registers registers, StackMemory stackMemory, String instructionPointer) {
		List<Vulnerability> vulnerabilities;
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
		vulnerabilities = strncpy(stackMemory,false,
                variableDestination.get(),
                variableDestination.get().getRelativeAddress(),
                variableSource.get().getRelativeAddress(),
                Math.toIntExact(size));

		return filterVulnerabilities(vulnerabilities, "strncpy", instructionPointer);
	}

	public static List<Vulnerability> strcat(Registers registers, StackMemory stackMemory, String instructionPointer) {
		List<Vulnerability> vulnerabilities;
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

		int strlenDestination = strlen(stackMemory, variableDestination.get());
		int n = strlen(stackMemory, variableSource.get()) + 1;
		vulnerabilities = strncpy(stackMemory,false,
                variableDestination.get(),
                variableDestination.get().getRelativeAddress() + strlenDestination,
                variableSource.get().getRelativeAddress(),
                n);
		
		return filterVulnerabilities(vulnerabilities, "strcat", instructionPointer);
	}

	public static List<Vulnerability> strncat(Registers registers, StackMemory stackMemory, String instructionPointer) {
		List<Vulnerability> vulnerabilities;
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
		Long n = registers.read("rdx");
        int strlenDestination = strlen(stackMemory, variableDestination.get());
        vulnerabilities = strncpy(stackMemory, false,
                variableDestination.get(),
                variableDestination.get().getRelativeAddress() + strlenDestination,
                variableSource.get().getRelativeAddress(),
                Math.toIntExact(n));
		
		return filterVulnerabilities(vulnerabilities, "strncat", instructionPointer);
	}

    public static List<Vulnerability> read(Registers registers, StackMemory stackMemory, String instructionPointer) {
        List<Vulnerability> vulnerabilities;
        Optional<Variable> variable = stackMemory.getMappedVariable(registers.read("rsi"));
        Long size = registers.read("rdx");

        if(!variable.isPresent()) {
            logger.fatal("Variable in address " + registers.read("rsi") + " not found.");
            System.exit(-1);
        }

        vulnerabilities = searchGetsVulnerabilities(stackMemory, variable.get(), size, true);

        return filterVulnerabilities(vulnerabilities, "read", instructionPointer);
    }

	public static List<Vulnerability> scanf(Registers registers, StackMemory stackMemory, String instructionPointer) {
		List<Vulnerability> vulnerabilities;
		Optional<Variable> variable1 = stackMemory.getMappedVariable(registers.read("rsi"));
		Optional<Variable> variable2 = stackMemory.getMappedVariable(registers.read("rdx"));
				
		if(!variable1.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rsi") + " not found.");
			System.exit(-1);
		}
		Variable firstArgument = variable1.get();
		if(variable2.isPresent() && variable2.get().getName() != null) {
			firstArgument = variable2.get();
		}

		vulnerabilities = searchScanfVulnerabilities(stackMemory, firstArgument);

		return filterVulnerabilities(vulnerabilities, "__isoc99_scanf", instructionPointer);
	}
	
	public static List<Vulnerability> fscanf(Registers registers, StackMemory stackMemory, String instructionPointer) {
		List<Vulnerability> vulnerabilities;

		Optional<Variable> file = stackMemory.getMappedVariable(registers.read("rdi"));
		Optional<Variable> variable1 = stackMemory.getMappedVariable(registers.read("rdx"));
		Optional<Variable> variable2 = stackMemory.getMappedVariable(registers.read("rcx"));
		
		if(!file.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
			System.exit(-1);
		}
		
		if(!variable1.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rdx") + " not found.");
			System.exit(-1);
		}
		Variable firstArgument = variable1.get();
		if(variable2.isPresent() && variable2.get().getName() != null) {
			firstArgument = variable2.get();
		}

		vulnerabilities = searchScanfVulnerabilities(stackMemory, firstArgument);

		return filterVulnerabilities(vulnerabilities, "__isoc99_fscanf", instructionPointer);
	}

	public static List<Vulnerability> sprintf(Registers registers, StackMemory stackMemory, String instructionPointer) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		Optional<Variable> variableDest = stackMemory.getMappedVariable(registers.read("rdi"));
		Optional<Variable> variable1 = stackMemory.getMappedVariable(registers.read("rdx"));
		Optional<Variable> variable2 = stackMemory.getMappedVariable(registers.read("rcx"));
		
		if(!variableDest.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
			System.exit(-1);
		}
		if(!variable1.isPresent()) {
			logger.fatal("Variable in address " + registers.read("rdx") + " not found.");
			System.exit(-1);
		}

        int strlenVar1 = strlen(stackMemory, variable1.get());

        // copy first string
        vulnerabilities.addAll(strncpy(stackMemory, false,
                variableDest.get(),
                variableDest.get().getRelativeAddress(),
                variable1.get().getRelativeAddress(),
                strlenVar1));

        if(variable2.isPresent()){
		    int strlenVar2 = strlen(stackMemory, variable2.get());

		    // copy second string
            vulnerabilities.addAll(strncpy(stackMemory, false,
                    variableDest.get(),
                    variableDest.get().getRelativeAddress() + strlenVar1,
                    variable2.get().getRelativeAddress(),
                    strlenVar2));
        }

		return filterVulnerabilities(vulnerabilities, "sprintf", instructionPointer);
	}

    public static List<Vulnerability> snprintf(Registers registers, StackMemory stackMemory, String instructionPointer) {
        List<Vulnerability> vulnerabilities = new ArrayList<>();
        Optional<Variable> variableDest = stackMemory.getMappedVariable(registers.read("rdi"));
        Long size = registers.read("rsi");
        Optional<Variable> variable1 = stackMemory.getMappedVariable(registers.read("rcx"));
        Optional<Variable> variable2 = stackMemory.getMappedVariable(registers.read("r8"));

        if(!variableDest.isPresent()) {
            logger.fatal("Variable in address " + registers.read("rdi") + " not found.");
            System.exit(-1);
        }
        if(!variable1.isPresent()) {
            logger.fatal("Variable in address " + registers.read("rcx") + " not found.");
            System.exit(-1);
        }

        int strlenVar1 = strlen(stackMemory, variable1.get());

        // copy first string
        vulnerabilities.addAll(strncpy(stackMemory, true,
                variableDest.get(),
                variableDest.get().getRelativeAddress(),
                variable1.get().getRelativeAddress(),
                Math.toIntExact(size) - 1));

        if(variable2.isPresent() && (strlenVar1 < size)){
            int strlenVar2 = strlen(stackMemory, variable2.get());

            // copy second string
            vulnerabilities.addAll(strncpy(stackMemory, true,
                    variableDest.get(),
                    variableDest.get().getRelativeAddress() + strlenVar1,
                    variable2.get().getRelativeAddress(),
                    Math.toIntExact(size) - strlenVar2 - 1));
        }

        return filterVulnerabilities(vulnerabilities, "snprintf", instructionPointer);
    }

    private static List<Vulnerability> searchGetsVulnerabilities(StackMemory stackMemory, Variable variable, Long size,
                                                                 boolean read) {
        List<Vulnerability> vulnerabilities = new ArrayList<>();
        boolean scorruption = false;

        Vulnerability vulnerability;
        for(int i = 0; i < Math.toIntExact(size) - 1; i++) {
            vulnerability = stackMemory.writeByte(variable, variable.getRelativeAddress() + i, 0xFFL);
            if(vulnerability != null &&
                    StringUtils.equals(vulnerability.getVulnerabilityType(), "SCORRUPTION")) {
                scorruption = true;
                vulnerabilities.add(vulnerability);
                break;
            }
            vulnerabilities.add(vulnerability);
        }
        if(scorruption){
            return vulnerabilities;
        }

        if(read){
            vulnerability = stackMemory.writeByte(variable, variable.getRelativeAddress() + Math.toIntExact(size) - 1, 0xFFL);
        } else {
            vulnerability = stackMemory.writeByte(variable, variable.getRelativeAddress() + Math.toIntExact(size) - 1, 0x00L);
        }
        vulnerabilities.add(vulnerability);

        return vulnerabilities;
    }

    /**
     * This function behaves like the c strncpy
     * @param stackMemory the memory to perform the strncpy
     * @param appendNull it will write a null byte after the last write if true
     * @param variableDestination variable of destination (to check vulnerabilities)
     * @param positionDestination relative memory position to where start to copy
     * @param positionSource relative memory position from where start to copy
     * @param n number of bytes to write (if appendNull is true, it will write n + 1 bytes)
     * @return
     */
    private static List<Vulnerability> strncpy(StackMemory stackMemory, boolean appendNull, Variable variableDestination, int positionDestination, int positionSource, int n){
	    List<Vulnerability> vulnerabilities = new ArrayList<>();

	    Vulnerability vulnerability = null;
	    Long readValue;
	    do {
	        if(n==0 || (vulnerability != null && StringUtils.equals(vulnerability.getVulnerabilityType(), "SCORRUPTION"))){
	            return vulnerabilities;
            }

            readValue = stackMemory.readByte(positionSource);
	        vulnerability = stackMemory.writeByte(variableDestination, positionDestination, readValue);
	        positionSource++;
	        positionDestination++;
	        n--;
            vulnerabilities.add(vulnerability);
        } while(readValue != 0);

	    while(n != 0){
	        if(vulnerability != null && StringUtils.equals(vulnerability.getVulnerabilityType(), "SCORRUPTION")){
	            return vulnerabilities;
            }
            vulnerability = stackMemory.writeByte(variableDestination, positionDestination, 0x00L);
	        positionDestination++;
	        n--;
	        vulnerabilities.add(vulnerability);
        }
        if(appendNull){
            vulnerability = stackMemory.writeByte(variableDestination, positionDestination, 0x00L);
            vulnerabilities.add(vulnerability);
        }
        return vulnerabilities;
    }

	private static List<Vulnerability> searchScanfVulnerabilities (StackMemory stackMemory, Variable variable) {
		List<Vulnerability> vulnerabilities = new ArrayList<>();
		int size = Integer.MAX_VALUE;
		
		Vulnerability vulnerability;
		for(int i = 0; i < size; i++) {
			vulnerability = stackMemory.writeByte(variable, variable.getRelativeAddress() + i, 0xFFL);
			if(vulnerability != null &&
				StringUtils.equals(vulnerability.getVulnerabilityType(), "SCORRUPTION")) {
				vulnerabilities.add(vulnerability);
				break;
			}
			vulnerabilities.add(vulnerability);
		}
		return vulnerabilities;
	}

	/**
	 * This method returns the length of the string saved in the variable (until \0)
	 */
	private static int strlen(StackMemory stackMemory, Variable variable) {
		int size = 0;
		while( stackMemory.readByte(variable.getRelativeAddress() + size) != 0x00L) {
			size++;
		}
		return size;
	}
	
	/**
	 * This method returns the list of vulnerabilities without null objects and duplicates and sets the function name
	 * and instruction pointer for each vulnerability
	 */
	private static List<Vulnerability> filterVulnerabilities (List<Vulnerability> vulnerabilities, String functionName, 
    		String instructionPointer) {
    	return vulnerabilities.stream()
        .filter(Objects::nonNull)
        .distinct()
        .peek(vuln -> {
            vuln.setFunctionName(functionName);
            vuln.setAddress(instructionPointer);
        }).collect(Collectors.toList());
    }

}
