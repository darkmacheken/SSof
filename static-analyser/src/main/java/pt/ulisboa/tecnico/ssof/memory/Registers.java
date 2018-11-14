package pt.ulisboa.tecnico.ssof.memory;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.log4j.Logger;

public class Registers {
	private static final Logger logger = Logger.getLogger(Registers.class);
	private static final Long LSB_8 =  0x00000000000000FFL;
	private static final Long LSB_16 = 0x000000000000FFFFL;
	private static final Long LSB_32 = 0x00000000FFFFFFFFL;
	private static final Long LSB_64 = 0xFFFFFFFFFFFFFFFFL;

	private final Map<String, Long> registersBank;
	private static final Map<String, MutablePair<String, Long>> registersMap = createRegistersMap();

	public Registers() {
		this.registersBank = new HashMap<>();
		initializeRegisters();
	}
	
	public void write(String register, Long value) {
		Long valueToWrite;
		MutablePair<String, Long> registerMapValue = registersMap.get(register);
		if(registerMapValue == null) {
			logger.fatal("Unable to get " + register + " from registerMap");
			System.exit(-1);
		}
		
		/* if register is 32 or 64 bits */
		if (registerMapValue.getRight().equals(LSB_32) ||
				registerMapValue.getRight().equals(LSB_64)) {
			valueToWrite = registerMapValue.getRight() & value;
		} else { /* if register is 8 or 16 bits */
			Long registerValue = registersBank.get(registerMapValue.getLeft());
			/* value is the new value in the selected bits and the previous value in the other bits (msb) */
			valueToWrite = (registerMapValue.getRight() & value) + (~registerMapValue.getRight() & registerValue);
		}
		this.registersBank.put(registerMapValue.getLeft(), valueToWrite);
	}
	
	public Long read(String register) {
		MutablePair<String, Long> registerMapValue = registersMap.get(register);
		if(registerMapValue == null) {
			logger.fatal("Unable to get " + register + " from registerMap");
			System.exit(-1);
		}
		
		Long registerValue = registersBank.get(registerMapValue.getLeft());
		return registerMapValue.getRight() & registerValue;
	}
	
	private void initializeRegisters() {
		registersBank.put("rax", 0L);
		registersBank.put("rbx", 0L);
		registersBank.put("rcx", 0L);
		registersBank.put("rdx", 0L);
		registersBank.put("rsi", 0L);
		registersBank.put("rdi", 0L);
		registersBank.put("rbp", 0L);
		registersBank.put("rsp", 0L);
		registersBank.put("rip", 0L);
		registersBank.put("r8", 0L);
		registersBank.put("r9", 0L);
		registersBank.put("r10", 0L);
		registersBank.put("r11", 0L);
		registersBank.put("r12", 0L);
		registersBank.put("r13", 0L);
		registersBank.put("r14", 0L);
		registersBank.put("r15", 0L);
	}
	
	private static Map<String, MutablePair<String, Long>> createRegistersMap() {
		Map<String, MutablePair<String, Long>> registersMap = new HashMap<>();
		
		/* RAX */
		registersMap.put("rax", new MutablePair<>("rax", LSB_64));
		registersMap.put("eax", new MutablePair<>("rax", LSB_32));
		registersMap.put("ax", new MutablePair<>("rax", LSB_16));
		registersMap.put("al", new MutablePair<>("rax", LSB_8));
		/* RBX */
		registersMap.put("rbx", new MutablePair<>("rbx", LSB_64));
		registersMap.put("ebx", new MutablePair<>("rbx", LSB_32));
		registersMap.put("bx", new MutablePair<>("rbx", LSB_16));
		registersMap.put("bl", new MutablePair<>("rbx", LSB_8));
		/* RCX */
		registersMap.put("rcx", new MutablePair<>("rcx", LSB_64));
		registersMap.put("ecx", new MutablePair<>("rcx", LSB_32));
		registersMap.put("cx", new MutablePair<>("rcx", LSB_16));
		registersMap.put("cl", new MutablePair<>("rcx", LSB_8));
		/* RDX */
		registersMap.put("rdx", new MutablePair<>("rdx", LSB_64));
		registersMap.put("edx", new MutablePair<>("rdx", LSB_32));
		registersMap.put("dx", new MutablePair<>("rdx", LSB_16));
		registersMap.put("dl", new MutablePair<>("rdx", LSB_8));
		/* RSI */
		registersMap.put("rsi", new MutablePair<>("rsi", LSB_64));
		registersMap.put("esi", new MutablePair<>("rsi", LSB_32));
		registersMap.put("si", new MutablePair<>("rsi", LSB_16));
		registersMap.put("sil", new MutablePair<>("rsi", LSB_8));
		/* RDI */
		registersMap.put("rdi", new MutablePair<>("rdi", LSB_64));
		registersMap.put("edi", new MutablePair<>("rdi", LSB_32));
		registersMap.put("di", new MutablePair<>("rdi", LSB_16));
		registersMap.put("dil", new MutablePair<>("rdi", LSB_8));
		/* RBP */
		registersMap.put("rbp", new MutablePair<>("rbp", LSB_64));
		registersMap.put("ebp", new MutablePair<>("rbp", LSB_32));
		registersMap.put("bp", new MutablePair<>("rbp", LSB_16));
		registersMap.put("bpl", new MutablePair<>("rbp", LSB_8));
		/* RSP */
		registersMap.put("rsp", new MutablePair<>("rsp", LSB_64));
		registersMap.put("esp", new MutablePair<>("rsp", LSB_32));
		registersMap.put("sp", new MutablePair<>("rsp", LSB_16));
		registersMap.put("spl", new MutablePair<>("rsp", LSB_8));
		/* RIP */
		registersMap.put("rip", new MutablePair<>("rip", LSB_64));
		registersMap.put("eip", new MutablePair<>("rip", LSB_32));
		/* R8 */
		registersMap.put("r8", new MutablePair<>("r8", LSB_64));
		registersMap.put("r8d", new MutablePair<>("r8", LSB_32));
		registersMap.put("r8w", new MutablePair<>("r8", LSB_16));
		registersMap.put("r8b", new MutablePair<>("r8", LSB_8));
		/* R9 */
		registersMap.put("r9", new MutablePair<>("r9", LSB_64));
		registersMap.put("r9d", new MutablePair<>("r9", LSB_32));
		registersMap.put("r9w", new MutablePair<>("r9", LSB_16));
		registersMap.put("r9b", new MutablePair<>("r9", LSB_8));
		/* R10 */
		registersMap.put("r10", new MutablePair<>("r10", LSB_64));
		registersMap.put("r10d", new MutablePair<>("r10", LSB_32));
		registersMap.put("r10w", new MutablePair<>("r10", LSB_16));
		registersMap.put("r10b", new MutablePair<>("r10", LSB_8));
		/* R11 */
		registersMap.put("r11", new MutablePair<>("r11", LSB_64));
		registersMap.put("r11d", new MutablePair<>("r11", LSB_32));
		registersMap.put("r11w", new MutablePair<>("r11", LSB_16));
		registersMap.put("r11b", new MutablePair<>("r11", LSB_8));
		/* R12 */
		registersMap.put("r12", new MutablePair<>("r12", LSB_64));
		registersMap.put("r12d", new MutablePair<>("r12", LSB_32));
		registersMap.put("r12w", new MutablePair<>("r12", LSB_16));
		registersMap.put("r12b", new MutablePair<>("r12", LSB_8));
		/* R13 */
		registersMap.put("r13", new MutablePair<>("r13", LSB_64));
		registersMap.put("r13d", new MutablePair<>("r13", LSB_32));
		registersMap.put("r13w", new MutablePair<>("r13", LSB_16));
		registersMap.put("r13b", new MutablePair<>("r13", LSB_8));
		/* R14 */
		registersMap.put("r14", new MutablePair<>("r14", LSB_64));
		registersMap.put("r14d", new MutablePair<>("r14", LSB_32));
		registersMap.put("r14w", new MutablePair<>("r14", LSB_16));
		registersMap.put("r14b", new MutablePair<>("r14", LSB_8));
		/* R15 */
		registersMap.put("r15", new MutablePair<>("r15", LSB_64));
		registersMap.put("r15d", new MutablePair<>("r15", LSB_32));
		registersMap.put("r15w", new MutablePair<>("r15", LSB_16));
		registersMap.put("r15b", new MutablePair<>("r15", LSB_8));
		
		return registersMap;
	}
}