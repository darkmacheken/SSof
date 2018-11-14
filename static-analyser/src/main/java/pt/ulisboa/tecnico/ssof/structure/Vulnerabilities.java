package pt.ulisboa.tecnico.ssof.structure;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.ssof.utils.JsonUtils;

public class Vulnerabilities {
	private List<Vulnerability> vulnerabilitiesList;
	
	public Vulnerabilities() {
		vulnerabilitiesList = new ArrayList<>();
	}
	
	public void addVulnerabilities(List<Vulnerability> vulnerabilities) {
		this.vulnerabilitiesList.addAll(vulnerabilities);
	}
	
	public void addVulnerability(Vulnerability vulnerability) {
		this.vulnerabilitiesList.add(vulnerability);
	}
	
	public void parseOutput(String fileName) {
		JsonUtils.parseJsonOutput(fileName, this.vulnerabilitiesList);
	}
 }
