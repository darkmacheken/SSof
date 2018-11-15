package pt.ulisboa.tecnico.ssof.memory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.ssof.structure.Variable;
import pt.ulisboa.tecnico.ssof.structure.Vulnerability;
import pt.ulisboa.tecnico.ssof.structure.operations.Function;

public class StackMemory {
    private static final Logger logger = Logger.getLogger(StackMemory.class);

    private LinkedList<MemoryPosition> memory;

    // Function frame
    private Stack<Function> calledFunctions;
    private Function currentFunction;

    // RBP
    private Stack<Integer> stackBasePointer;
    private int currentBasePointer;

    public StackMemory() {
        // memory is initialized with 8 bytes(initialized with 0xFF) of unmapped memory just to avoid IndexOutOfBound
        this.memory = new LinkedList<>();
        IntStream.range(0,8).forEach(i -> memory.addLast(new MemoryPosition(null, (byte) 0xFF)));

        this.calledFunctions = new Stack<>();
        this.stackBasePointer = new Stack<>();
    }

    /**
     * Returns the stack pointer index.
     * @return the stack pointer index.
     */
    public Long getStackPointer(){
        return (long) (memory.size() - 1);
    }

    /**
     * Sets a new stack frame to the received function.
     * 8 bytes are added with value 0xFF each to the stack with the variable being of type 'ret'
     * @param function of the new stack frame
     */
    public void startStackFrame(Function function){
        this.calledFunctions.push(function);
        this.currentFunction = function;
        Variable retVar = new Variable(8, "ret", "retAddress", "rbp+8");

        IntStream.range(0,8).forEach(i -> memory.addLast(new MemoryPosition(retVar, (byte) 0xFF)));
    }

    /**
     * Sets the previous stack frame. If there is no previous stack frame, it exits.
     * Current Function is set to the previous call. Ret address must be at the top of the stack.
     * 8 bytes are removed from the stack.
     */
    public void endStackFrame(){
        if(calledFunctions.empty() || stackBasePointer.empty()){
            logger.fatal("There is no previous stack frame. Exiting..");
            System.exit(-1);
        }

        // remove ret from stack (last 8 bytes)
        IntStream.range(0,8).forEach(i -> memory.removeLast());

        // POP current BP and set current  BP to previous
        stackBasePointer.pop();
        currentBasePointer = stackBasePointer.peek();

        // POP current function and set current function to previous
        calledFunctions.pop();
        currentFunction = calledFunctions.peek();
    }

    /**
     * Adds 8 bytes to the stack with value 0xFF each representing the previous RBP address.
     * Current base pointer is set to the top of the stack index.
     * @return the current RBP index
     */
    public Long pushRbp(){
        Variable basePointerVar = new Variable(8,"bp", "bpAddress", "rbp");

        IntStream.range(0,8).forEach(i -> memory.addLast(new MemoryPosition(basePointerVar, (byte) 0xFF)));

        currentBasePointer = Math.toIntExact(getStackPointer());
        stackBasePointer.push(currentBasePointer);
        return (long) currentBasePointer;
    }

    /**
     * Puts the value in the stack. The stack grows 8 positions (8 bytes)
     * @param value value to store
     * @return the new stack pointer index
     */
    public Long push(Long value){
        byte[] bytes = ByteBuffer.allocate(8).putLong(value).array();
        IntStream.range(0,8).forEach(i -> memory.addLast(new MemoryPosition(null, bytes[i])));
        return getStackPointer();
    }

    /**
     * Allocates unmapped numBytes in the stack (zeroed)
     * @param numBytes num of bytes to allocate
     * @return the new stack pointer index
     */
    public Long allocate(int numBytes){
        IntStream.range(0, numBytes).forEach(i -> memory.addLast(new MemoryPosition(null, (byte) 0x00)));
        return getStackPointer();
    }

    /**
     * Allocates mapped numBytes in the stack
     * @param numBytes num of bytes to allocate
     * @return the new stack pointer index
     * @throws IndexOutOfBoundsException if the current frame doesn't have enough allocated memory.
     */
    public Long allocateAndMap(int numBytes){
        Long stackPointer = allocate(numBytes);
        mapMemory();
        return stackPointer;
    }

    /**
     * Deallocate all the memory until current frame RBP inclusive.
     * @return the new stack pointer index
     */
    public Long deallocate(){
        while(getStackPointer() != currentBasePointer){
            memory.removeLast();
        }
        // remove RBP
        IntStream.range(0,8).forEach(i -> memory.removeLast());

        return getStackPointer();
    }

    /**
     * Maps the current stack frame's memory chunk according with the current function's variables.
     * @throws IndexOutOfBoundsException if the current frame doesn't have enough allocated memory.
     */
    public void mapMemory(){
        // Map variables
        for(Variable variable : currentFunction.getVariables()){
            int endIndex = currentBasePointer + variable.getRelativeAddress();
            int startIndex = endIndex - variable.getBytes();

            memory.subList(startIndex, endIndex + 1)
                  .forEach(memoryPosition -> memoryPosition.setVariable(variable));
        }

        // Map contiguous unmapped memory in the current stack
        int indexLastByteUnmapped = 0;
        Variable currentUnmappedVariable = new Variable();
        for(int i = memory.size() - 1; i > currentBasePointer ; i--){
            MemoryPosition memoryPosition = memory.get(i);
            if(memoryPosition.getVariable() == null){
               if(i + 1 != indexLastByteUnmapped){
                   currentUnmappedVariable = new Variable(1,"unmapped", "unmapped", "rbp-" + i);
               } else {
                   currentUnmappedVariable.incrementBytes();
               }
               indexLastByteUnmapped = i;
               memoryPosition.setVariable(currentUnmappedVariable);
           }
        }
    }

    /**
     * Reads the byte at relative position of current RBP
     * @param position is relative to the current RBP
     * @return the value stored in that position (max value is 255)
     * @throws IndexOutOfBoundsException if the position is not in memory.
     */
    public Long readByte(int position){
        return (long) memory.get(currentBasePointer + position).getContent();
    }

    /**
     * Reads a value starting in position until numBytes
     * @param position is relative to the current RBP
     * @param numBytes to read, only reads between 1 and 8 bytes inclusive.
     * @return the value
     */
    public Long readValue(int position, int numBytes){
        if(numBytes <= 0 || numBytes > 8){
            logger.error("You can only read between 1 and 8 bytes.");
            return readByte(position);
        }
        int endIndex = currentBasePointer + position;
        int startIndex = endIndex - numBytes;

        byte[] bytes = ArrayUtils.toPrimitive(
                memory.subList(startIndex, endIndex + 1).stream()
                        .map(MemoryPosition::getContent)
                        .toArray(Byte[]::new));

        return ByteBuffer.wrap(bytes).getLong();
    }

    /**
     * Writes the value in the memory appointed by position relatively to RBP if no SCORRUPTION occurs.
     * @param position is relative to the current RBP
     * @param value is the value to write
     * @return a vulnerability if there is any and null otherwise
     */
    public Vulnerability writeByte(int position, Long value){
        int index = currentBasePointer + position;
        // out of the current stack frame
        if(index <= currentBasePointer - 16 || index >= memory.size()) {
            String address = position < 0 ? "rbp-" + (-position) : "rbp+" + position;
            return new Vulnerability("SCORRUPTION", currentFunction.getName(), address);
        }

        MemoryPosition memoryPosition = memory.get(index);
        memoryPosition.setContent(value.byteValue());
        // unmapped
        if(StringUtils.equals(memoryPosition.getVariable().getType(), "unmapped")){
            return new Vulnerability("INVALIDACCS",
                    currentFunction.getName(),
                    memoryPosition.getVariable().getAddress());
        }

        // RBP overflow
        if(StringUtils.equals(memoryPosition.getVariable().getType(), "bp")){
            return new Vulnerability("RBPOVERFLOW", currentFunction.getName());
        }

        // Return overflow
        if(StringUtils.equals(memoryPosition.getVariable().getType(), "ret")){
            return new Vulnerability("RETOVERFLOW", currentFunction.getName());
        }
        return null;
    }

    /**
     * Writes the value in the memory appointed by position relatively to RBP if no SCORRUPTION occurs.
     * @param variable the variable that is supposed to write. Can't be null
     * @param position is relative to the current RBP
     * @param value is the value to write
     * @return a vulnerability if there is any and null otherwise
     */
    public Vulnerability writeByte(Variable variable, int position, Long value){
        Vulnerability vulnerability = writeByte(position, value);

        if(vulnerability == null){ // can be a var overflow
            int index = currentBasePointer + position;
            MemoryPosition memoryPosition = memory.get(index);

            if(!memoryPosition.getVariable().equals(variable)){
                vulnerability = new Vulnerability("VAROVERFLOW",
                        currentFunction.getName(),
                        variable.getName(),
                        memoryPosition.getVariable().getName());
            }
        } else {
            vulnerability.setOverflowVar(variable.getName());
        }
        return vulnerability;
    }

    /**
     * Writes the value in the memory appointed by position relatively to RBP if no SCORRUPTION occurs.
     * @param position is relative to the current RBP
     * @param numBytes number of bytes to write from the value
     * @param value is the value to write
     * @return a list with all founded vulnerabilities.
     */
    public List<Vulnerability> writeValue(int position, int numBytes, Long value){
        if(numBytes <= 0 || numBytes > 8){
            logger.error("You can only write between 1 and 8 bytes.");
            return Collections.singletonList(writeByte(position, value));
        }
        int index = currentBasePointer + position;
        byte[] bytes = Arrays.copyOfRange(ByteBuffer.allocate(8).putLong(value).array(), 8 - numBytes, 8);

        List<Vulnerability> vulnerabilities = new ArrayList<>();
        for (int i = index , j = numBytes; j > 0; i--, j--){
            vulnerabilities.add(writeByte(i, (long) bytes[j]));
        }

        // filter nulls and duplicates
        return vulnerabilities.stream()
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Returns the variable mapped in the memory position appointed by index.
     * @param index index of the memory position. Index is an absolute "address".
     * @return an optional with the mapped variable. The optional will be empty if the memory appointed by index
     * is unmapped.
     */
    public Optional<Variable> getMappedVariable(int index){
        if(index < 0 || index >= memory.size()){
            logger.error("Index out of memory.");
            return Optional.empty();
        }

        Variable variable = memory.get(index).getVariable();
        if(variable == null){
            return Optional.empty();
        } else {
            return Optional.of(variable);
        }
    }
}