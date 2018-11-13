package pt.ulisboa.tecnico.ssof.structure;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.log4j.Logger;

public class Registers {
	
	private static final Logger logger = Logger.getLogger(Registers.class);

	private final Map<String, Long> registers;
	private static final Map<String, MutablePair<String, Long>> registersMap = createRegistersMap();

	public Registers() {
		this.registers = new HashMap<>();
	}
	
	public static Map<String, MutablePair<String, Long>> createRegistersMap() {
		Map<String, MutablePair<String, Long>> registersMap = new HashMap<>();
		
		/* RAX */
		registersMap.put("rax", new MutablePair<String, Long>("rax", 0xFFFFFFFFL));
		registersMap.put("eax", new MutablePair<String, Long>("rax", 0x00FFFFFFL));
		registersMap.put("ax", new MutablePair<String, Long>("rax", 0x0000FFFFL));
		registersMap.put("al", new MutablePair<String, Long>("rax", 0x000000FFL));
		/* RBX */
		registersMap.put("rbx", new MutablePair<String, Long>("rbx", 0xFFFFFFFFL));
		registersMap.put("ebx", new MutablePair<String, Long>("rbx", 0x00FFFFFFL));
		registersMap.put("bx", new MutablePair<String, Long>("rbx", 0x0000FFFFL));
		registersMap.put("bl", new MutablePair<String, Long>("rbx", 0x000000FFL));
		/* RCX */
		registersMap.put("rcx", new MutablePair<String, Long>("rcx", 0xFFFFFFFFL));
		registersMap.put("ecx", new MutablePair<String, Long>("rcx", 0x00FFFFFFL));
		registersMap.put("cx", new MutablePair<String, Long>("rcx", 0x0000FFFFL));
		registersMap.put("cl", new MutablePair<String, Long>("rcx", 0x000000FFL));
		/* RDX */
		registersMap.put("rdx", new MutablePair<String, Long>("rdx", 0xFFFFFFFFL));
		registersMap.put("edx", new MutablePair<String, Long>("rdx", 0x00FFFFFFL));
		registersMap.put("dx", new MutablePair<String, Long>("rdx", 0x0000FFFFL));
		registersMap.put("dl", new MutablePair<String, Long>("rdx", 0x000000FFL));
		/* RSI */
		registersMap.put("rsi", new MutablePair<String, Long>("rsi", 0xFFFFFFFFL));
		registersMap.put("esi", new MutablePair<String, Long>("rsi", 0x00FFFFFFL));
		registersMap.put("si", new MutablePair<String, Long>("rsi", 0x0000FFFFL));
		registersMap.put("sil", new MutablePair<String, Long>("rsi", 0x000000FFL));
		/* RDI */
		registersMap.put("rdi", new MutablePair<String, Long>("rdi", 0xFFFFFFFFL));
		registersMap.put("edi", new MutablePair<String, Long>("rdi", 0x00FFFFFFL));
		registersMap.put("di", new MutablePair<String, Long>("rdi", 0x0000FFFFL));
		registersMap.put("dil", new MutablePair<String, Long>("rdi", 0x000000FFL));
		/* RBP */
		registersMap.put("rbp", new MutablePair<String, Long>("rbp", 0xFFFFFFFFL));
		registersMap.put("ebp", new MutablePair<String, Long>("rbp", 0x00FFFFFFL));
		registersMap.put("bp", new MutablePair<String, Long>("rbp", 0x0000FFFFL));
		registersMap.put("bpl", new MutablePair<String, Long>("rbp", 0x000000FFL));
		/* RSP */
		registersMap.put("rsp", new MutablePair<String, Long>("rsp", 0xFFFFFFFFL));
		registersMap.put("esp", new MutablePair<String, Long>("rsp", 0x00FFFFFFL));
		registersMap.put("sp", new MutablePair<String, Long>("rsp", 0x0000FFFFL));
		registersMap.put("spl", new MutablePair<String, Long>("rsp", 0x000000FFL));
		/* RIP */
		registersMap.put("rip", new MutablePair<String, Long>("rip", 0xFFFFFFFFL));
		registersMap.put("eip", new MutablePair<String, Long>("rip", 0x00FFFFFFL));
		/* R8 */
		registersMap.put("r8", new MutablePair<String, Long>("r8", 0xFFFFFFFFL));
		registersMap.put("r8d", new MutablePair<String, Long>("r8", 0x00FFFFFFL));
		registersMap.put("r8w", new MutablePair<String, Long>("r8", 0x0000FFFFL));
		registersMap.put("r8b", new MutablePair<String, Long>("r8", 0x000000FFL));
		/* R9 */
		registersMap.put("r9", new MutablePair<String, Long>("r9", 0xFFFFFFFFL));
		registersMap.put("r9d", new MutablePair<String, Long>("r9", 0x00FFFFFFL));
		registersMap.put("r9w", new MutablePair<String, Long>("r9", 0x0000FFFFL));
		registersMap.put("r9b", new MutablePair<String, Long>("r9", 0x000000FFL));
		/* R10 */
		registersMap.put("r10", new MutablePair<String, Long>("r10", 0xFFFFFFFFL));
		registersMap.put("r10d", new MutablePair<String, Long>("r10", 0x00FFFFFFL));
		registersMap.put("r10w", new MutablePair<String, Long>("r10", 0x0000FFFFL));
		registersMap.put("r10b", new MutablePair<String, Long>("r10", 0x000000FFL));
		/* R11 */
		registersMap.put("r11", new MutablePair<String, Long>("r11", 0xFFFFFFFFL));
		registersMap.put("r11d", new MutablePair<String, Long>("r11", 0x00FFFFFFL));
		registersMap.put("r11w", new MutablePair<String, Long>("r11", 0x0000FFFFL));
		registersMap.put("r11b", new MutablePair<String, Long>("r11", 0x000000FFL));
		/* R12 */
		registersMap.put("r12", new MutablePair<String, Long>("r12", 0xFFFFFFFFL));
		registersMap.put("r12d", new MutablePair<String, Long>("r12", 0x00FFFFFFL));
		registersMap.put("r12w", new MutablePair<String, Long>("r12", 0x0000FFFFL));
		registersMap.put("r12b", new MutablePair<String, Long>("r12", 0x000000FFL));
		/* R13 */
		registersMap.put("r13", new MutablePair<String, Long>("r13", 0xFFFFFFFFL));
		registersMap.put("r13d", new MutablePair<String, Long>("r13", 0x00FFFFFFL));
		registersMap.put("r13w", new MutablePair<String, Long>("r13", 0x0000FFFFL));
		registersMap.put("r13b", new MutablePair<String, Long>("r13", 0x000000FFL));
		/* R14 */
		registersMap.put("r14", new MutablePair<String, Long>("r14", 0xFFFFFFFFL));
		registersMap.put("r14d", new MutablePair<String, Long>("r14", 0x00FFFFFFL));
		registersMap.put("r14w", new MutablePair<String, Long>("r14", 0x0000FFFFL));
		registersMap.put("r14b", new MutablePair<String, Long>("r14", 0x000000FFL));
		/* R15 */
		registersMap.put("r15", new MutablePair<String, Long>("r15", 0xFFFFFFFFL));
		registersMap.put("r15d", new MutablePair<String, Long>("r15", 0x00FFFFFFL));
		registersMap.put("r15w", new MutablePair<String, Long>("r15", 0x0000FFFFL));
		registersMap.put("r15b", new MutablePair<String, Long>("r15", 0x000000FFL));
		
		return registersMap;
	}
	
	public void write(String register, Long value) {
		Long valueToWrite;
		MutablePair<String, Long> registerMapValue = registersMap.get(register);
		if(registerMapValue == null) {
			logger.error("Unable to get " + register + " from registerMap");
		}
		
		/* if register is 32 or 64 bits */
		if (registerMapValue.getRight().equals(0x00FFFFFFL) ||
				registerMapValue.getRight().equals(0xFFFFFFFFL)) {
			valueToWrite = registerMapValue.getRight() & value;
		} else { /* if register is 8 or 16 bits */
			Long registerValue = registers.get(registerMapValue.getLeft());
			/* value is the new value in the selected bits and the previous value in the other bits (msb) */
			valueToWrite = (registerMapValue.getRight() & value) + (~registerMapValue.getRight() & registerValue);
		}
		this.registers.put(registerMapValue.getLeft(), valueToWrite);
	}
	
	public Long read(String register) {
		MutablePair<String, Long> registerMapValue = registersMap.get(register);
		if(registerMapValue == null) {
			logger.error("Unable to get " + register + " from registerMap");
		}
		
		Long registerValue = registers.get(registerMapValue.getLeft());
		return registerMapValue.getRight() & registerValue;
	}
}




