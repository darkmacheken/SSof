package pt.ulisboa.tecnico.ssof;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import pt.ulisboa.tecnico.ssof.structure.Vulnerabilities;
import pt.ulisboa.tecnico.ssof.structure.graph.Graph;
import pt.ulisboa.tecnico.ssof.structure.operations.Function;
import pt.ulisboa.tecnico.ssof.structure.operations.Program;
import pt.ulisboa.tecnico.ssof.utils.JsonUtils;
import pt.ulisboa.tecnico.ssof.visitor.Executor;

public class AdvancedTest {
	
	private final String test01Name = "21_fgets_strcpy_nok_invalidaccs";
	private final String test02Name = "22_fgets_strcpy_nok_scorruption";
	private final String test03Name = "23_fgets_fun_main_nok";
	private final String test04Name = "24_fgets_fun_ok";
	private final String test05Name = "25_fgets_fun_rbpoverflow";
	private final String test06Name = "26_fgets_fun_retoverflow";
	private final String test07Name = "27_fgets_fun_stack";
	private final String test08Name = "28_scanf_nok";
	private final String test09Name = "29_fscanf_nok";
	private final String test10Name = "30_fgets_sprintf_ok";
	private final String test11Name = "31_fgets_snprintf_ok";
	private final String test12Name = "32_read_nok";
	private final String test13Name = "33_fgets_strcpy_direct_access_ok";
	private final String test14Name = "34_fgets_direct_access_invalidacc";
	private final String test15Name = "35_fgets_direct_access_ok";
	
	@SuppressWarnings("resource")
	@Test
	public void test21() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test01Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test01Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test01Name);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test01Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test22() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test02Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test02Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test02Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test02Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test23() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test03Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test03Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test03Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test03Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test24() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test04Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test04Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test04Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test04Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test25() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test05Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test05Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test05Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test05Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test26() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test06Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test06Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test06Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test06Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test27() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test07Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test07Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test07Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test07Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test28() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test08Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test08Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test08Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test08Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test29() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test09Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test09Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test09Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test09Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test30() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test10Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test10Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test10Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test10Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test31() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test11Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test11Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test11Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test11Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test32() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test12Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test12Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test12Name);
	    
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test12Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test33() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test13Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test13Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test13Name);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test13Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test34() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test14Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test14Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test14Name);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test14Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test35() throws IOException, JSONException, InterruptedException {
		
		InputStream expectedIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test15Name + ".output.json");
		Scanner expectedScanner = new Scanner(expectedIS).useDelimiter("\\A");
	    String expectedJsonData = expectedScanner.hasNext() ? expectedScanner.next() : "";
	    
	    InputStream testIS = this.getClass().getResourceAsStream("/public_advanced_tests/" + test15Name + ".json");
		Scanner testScanner = new Scanner(testIS).useDelimiter("\\A");
	    String testInput = testScanner.hasNext() ? testScanner.next() : "";
	    
	    StringBuilder jsonObject = new StringBuilder();
	    jsonObject.append(testInput);
	    Map<String, Function> functions = JsonUtils.parseJsonInput(jsonObject.toString());
        Program program = new Program(functions);

	    Graph graph = new Graph();
	    graph.generateGraph(program);

        Executor executor = new Executor(functions);
        graph.getMainBlockEntry().accept(executor);

        Vulnerabilities vulnerabilities = executor.getVulnerabilities();
        vulnerabilities.parseOutput("src/test/resources/" + test15Name);
	    
		InputStream resultIS = this.getClass().getResourceAsStream("/" + test15Name + ".output.json");
		Scanner resultScanner = new Scanner(resultIS).useDelimiter("\\A");
	    String resultJsonData = resultScanner.hasNext() ? resultScanner.next() : "";

	    JSONAssert.assertEquals(expectedJsonData, resultJsonData, JSONCompareMode.NON_EXTENSIBLE);
	}
}