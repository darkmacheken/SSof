package pt.ulisboa.tecnico.ssof.structure;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import java.util.stream.Collectors;
import pt.ulisboa.tecnico.ssof.utils.JsonUtils;

public class Vulnerabilities {
	private List<Vulnerability> vulnerabilitiesList;
	
	public Vulnerabilities() {
		vulnerabilitiesList = new ArrayList<>();
	}

	public Vulnerabilities(List<Vulnerability> vulnerabilities){
        vulnerabilitiesList = new ArrayList<>();
        vulnerabilitiesList.addAll(vulnerabilities);
    }

	public void addVulnerabilities(List<Vulnerability> vulnerabilities) {
		this.vulnerabilitiesList.addAll(vulnerabilities);
	}

	public void addVulnerability(Vulnerability vulnerability) {
		this.vulnerabilitiesList.add(vulnerability);
	}

    public List<Vulnerability> getVulnerabilitiesList() {
        return vulnerabilitiesList;
    }

    public void parseOutput(String fileName) {
		JsonUtils.parseJsonOutput(fileName, this.vulnerabilitiesList);
	}
 }
