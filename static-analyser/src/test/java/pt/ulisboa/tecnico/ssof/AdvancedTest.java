package pt.ulisboa.tecnico.ssof;

import java.io.IOException;
import org.json.JSONException;
import org.junit.Test;
import pt.ulisboa.tecnico.ssof.utils.TestUtils;

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
	private final String test16Name = "41_ifs";
	
	@Test
	public void test21() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test01Name);
	}
	
	@Test
	public void test22() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test02Name);
	}
	
	@Test
	public void test23() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test03Name);
	}
	
	@Test
	public void test24() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test04Name);
	}
	
	@Test
	public void test25() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test05Name);
	}
	
	@Test
	public void test26() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test06Name);
	}
	
	@Test
	public void test27() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test07Name);
	}
	
	@Test
	public void test28() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test08Name);
	}
	
	@Test
	public void test29() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test09Name);
	}
	
	@Test
	public void test30() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test10Name);
	}
	
	@Test
	public void test31() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test11Name);
	}
	
	@Test
	public void test32() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test12Name);
	}
	
	@Test
	public void test33() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test13Name);
	}
	
	@Test
	public void test34() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test14Name);
	}
	
	@Test
	public void test35() throws IOException, JSONException, InterruptedException {
		TestUtils.doAdvancedTest(test15Name);
	}
	
	@Test
	public void test41() throws IOException, JSONException, InterruptedException {
	    String[] args = new String[1];
	    args[0] = "src/test/resources/" + "public_advanced_tests" + "/" + test16Name + ".json";
	    StaticAnalyser.main(args);
	}
}