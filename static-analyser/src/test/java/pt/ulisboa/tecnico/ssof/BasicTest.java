package pt.ulisboa.tecnico.ssof;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import pt.ulisboa.tecnico.ssof.utils.TestUtils;

public class BasicTest {
	
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
	
	@Test
	public void test01() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test01Name);
	}
	
	@Test
	public void test02() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test02Name);
	}
	
	@Test
	public void test03() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test03Name);
	}
	
	@Test
	public void test04() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test04Name);
	}
	
	@Test
	public void test05() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test05Name);
	}
	
	@Test
	public void test06() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test06Name);
	}
	
	@Test
	public void test07() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test07Name);
	}
	
	@Test
	public void test08() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test08Name);
	}
	
	@Test
	public void test09() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test09Name);
	}
	
	@Test
	public void test10() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test10Name);
	}
	
	@Test
	public void test11() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test11Name);
	}
	
	@Test
	public void test12() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test12Name);
	}
	
	@Test
	public void test13() throws IOException, JSONException, InterruptedException {
		TestUtils.doBasicTest(test13Name);
	}
}