package pt.ulisboa.tecnico.ssof.utils;

import java.io.InputStream;
import java.util.Scanner;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import pt.ulisboa.tecnico.ssof.StaticAnalyser;

public final class TestUtils {
	private TestUtils() {}
	
	public static void doBasicTest(String fileName) throws JSONException {
		doTest(fileName, "public_basic_tests");
	}
	
	public static void doAdvancedTest(String fileName) throws JSONException {
		doTest(fileName, "public_advanced_tests");
	}
	
	@SuppressWarnings("resource")
	private static void doTest(String fileName, String testFolder) throws JSONException {
		InputStream expectedIS = TestUtils.class.getResourceAsStream("/" + testFolder + "/" + fileName + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    String[] args = new String[1];
	    args[0] = "src/test/resources/" + testFolder + "/" + fileName + ".json";
	    StaticAnalyser.main(args);
	    
		InputStream resultIS = TestUtils.class.getResourceAsStream("/" + fileName + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
}
