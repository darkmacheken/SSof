package pt.ulisboa.tecnico.ssof.structure;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.ssof.utils.JsonUtils;

public class Vulnerabilities {
	private List<Vulnerability> vulnerabilitiesList;
	
	public Vulnerabilities() {
		vulnerabilitiesList = new ArrayList<>();
	}

	public List<Vulnerability> getVulnerabilitiesList() {
		return vulnerabilitiesList;
	}
	
	public void parseOutput() {
		JsonUtils.parseJsonOutput("fileName", this.vulnerabilitiesList);
	}
 }
