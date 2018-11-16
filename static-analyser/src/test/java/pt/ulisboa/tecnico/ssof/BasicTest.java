package pt.ulisboa.tecnico.ssof;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import pt.ulisboa.tecnico.ssof.structure.Vulnerabilities;
import pt.ulisboa.tecnico.ssof.structure.Vulnerability;
import pt.ulisboa.tecnico.ssof.structure.graph.Graph;
import pt.ulisboa.tecnico.ssof.structure.operations.Program;
import pt.ulisboa.tecnico.ssof.utils.JsonUtils;

public class BasicTest {
	//vulns.parseOutput("src/test/resources/01_gets_all")
	
	private final String test01Name = "01_gets_all";
	private final String test02Name = "02_fgets_strcpy_ok";
	private final String test03Name = "03_fgets_strcpy_nok_varoverflow";
	private final String test04Name = "04_fgets_strcpy_nok_rbpoverflow";
	private final String test05Name = "05_fgets_strcpy_nok_retoverflow";
	private final String test06Name = "06_fgets_strncpy_ok";
	private final String test07Name = "07_fgets_strncpy_varoverflow";
	private final String test08Name = "08_fgets_strcat_ok";
	private final String test09Name = "09_fgets_strncat_ok";
	private final String test10Name = "10_fgets_strcat_all";
	private final String test11Name = "11_3_vars_ok";
	private final String test12Name = "12_3_vars_nok_all";
	private final String test13Name = "13_multiple_overflows";
	
	
	@Before
	public void setUp() {
		
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test01() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test01Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test01Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test01Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test02() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test02Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test02Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test02Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test03() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test03Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test03Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test03Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test04() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test04Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test04Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test04Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test05() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test05Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test05Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test05Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test06() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test06Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test06Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test06Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test07() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test07Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test07Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test07Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test08() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test08Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test08Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test08Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test09() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test09Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test09Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test09Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test10() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test10Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test10Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test10Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test11() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test11Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test11Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test11Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test12() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test12Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test12Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test12Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test13() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test13Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_basic_tests/" + test13Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test13Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
}