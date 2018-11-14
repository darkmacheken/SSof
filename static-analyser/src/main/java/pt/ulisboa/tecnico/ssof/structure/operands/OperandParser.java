package pt.ulisboa.tecnico.ssof.structure.operands;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperandParser {
	
	public OperandParser() {}
	
	public Operand parseOperand(String operand) {
		Pattern pointerPattern = Pattern.compile("\\[.+\\]");
		Matcher pointerMatcher = pointerPattern.matcher(operand);
		Pattern valuePattern = Pattern.compile("0x[0-9a-f]+");
		Matcher valueMatcher = valuePattern.matcher(operand);
		//is it a pointer?
		if (pointerMatcher.find()) {
			String register;
			int offset = 0;
			String wordSize = "";
			
			String pointer = pointerMatcher.group(0);
			
			Pattern offsetSignPattern = Pattern.compile("\\+|-");
			Matcher offsetSignMatcher = offsetSignPattern.matcher(pointer);
			
			//if there is an offset
			if (offsetSignMatcher.find()) {
				String offsetSign = offsetSignMatcher.group(0);
				register = pointer.substring(1, pointer.indexOf(offsetSign));
				String offsetStr = pointer.substring(pointer.indexOf(offsetSign)+3, pointer.length()-1);
				
				if (offsetSign.equals("+")) {
					offset = Integer.parseInt(offsetStr, 16);
				} else {
					offset = -Integer.parseInt(offsetStr, 16);
				}
				
			//if there is no offset
			} else {
				register = pointer.substring(1, pointer.indexOf("]"));
			}
			
			Pattern wordSizePattern = Pattern.compile("BYTE|DWORD|QWORD|WORD");
			Matcher wordSizeMatcher = wordSizePattern.matcher(operand);
			if (wordSizeMatcher.find()) {
				wordSize = wordSizeMatcher.group(0);
			}
			return new Pointer(register, offset, wordSize);
		
		//is it a value?
		} else if (valueMatcher.find()) {
			String valueStr = valueMatcher.group(0).substring(2);
			
			//long value = Long.parseLong(valueStr, 16);
			long value = new BigInteger(valueStr, 16).longValue();
			return new Number(value);
		//if it's not a pointer nor a value, it must be a register
		} else {
			return new Register(operand);
		}
		
	}
}
