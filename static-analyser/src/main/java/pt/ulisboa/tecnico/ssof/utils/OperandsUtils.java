package pt.ulisboa.tecnico.ssof.utils;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pt.ulisboa.tecnico.ssof.structure.operands.Number;
import pt.ulisboa.tecnico.ssof.structure.operands.Operand;
import pt.ulisboa.tecnico.ssof.structure.operands.Pointer;
import pt.ulisboa.tecnico.ssof.structure.operands.Register;

public final class OperandsUtils {
	
	private OperandsUtils() {}

	/**
	 * Transforms a strings representing an operand to an object Operand
	 * @param operand is the operand string
	 * @return the operand object represented in parameter operand
	 */
	public static Operand parseOperand(String operand) {
		Pattern pointerPattern = Pattern.compile("\\[.+\\]");
		Matcher pointerMatcher = pointerPattern.matcher(operand);
		Pattern valuePattern = Pattern.compile("0x[0-9a-f]+");
		Matcher valueMatcher = valuePattern.matcher(operand);
		//is it a pointer?
		if (pointerMatcher.find()) {
			return doPointerOperand(operand, pointerMatcher);
		
		//is it a value?
		} else if (valueMatcher.find()) {
			String valueStr = valueMatcher.group(0).substring(2);
			long value = new BigInteger(valueStr, 16).longValue();
			return new Number(value);
		//if it's not a pointer nor a value, it must be a register
		} else {
			return new Register(operand);
		}
		
	}

	private static Operand doPointerOperand(String operand, Matcher pointerMatcher) {
		String register;
		int offset = 0;
		int wordSize = 1;
		
		String pointer = pointerMatcher.group(0);
		
		Pattern offsetSignPattern = Pattern.compile("[+-]");
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
			register = pointer.substring(1, pointer.indexOf(']'));
		}
		
		Pattern wordSizePattern = Pattern.compile("BYTE|WORD|DWORD|QWORD");
		Matcher wordSizeMatcher = wordSizePattern.matcher(operand);
		if (wordSizeMatcher.find()) {
			String wordSizeStr = wordSizeMatcher.group(0);
			if (wordSizeStr.equals("BYTE")) {
				wordSize = 1;
				
			} else if (wordSizeStr.equals("WORD")) {
				wordSize = 2;
				
			} else if (wordSizeStr.equals("DWORD")) {
				wordSize = 4;
				
			} else if (wordSizeStr.equals("QWORD")) {
				wordSize = 8;
			}
			
		}
		return new Pointer(register, offset, wordSize);
	}
}
