package pt.ulisboa.tecnico.ssof;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pt.ulisboa.tecnico.ssof.structure.Function;


public class StaticAnalyser {
	
	private static final Logger logger = Logger.getLogger(StaticAnalyser.class);
	
    public static void main( String[] args ) {
		if(args.length != 1) {
			logger.fatal("One parameter is expected but " + args.length + " were given.");
	        System.exit(-1);
		}

		StringBuilder jsonObject = new StringBuilder();
		String fileName = args[0];
	    try {
	    	Path path = Paths.get(fileName).toAbsolutePath();
	        jsonObject.append(new String(Files.readAllBytes(path)));
	    } catch (IOException e) {
	        logger.fatal("The file: " + fileName + " doesn't exists.");
	        System.exit(-1);
	    }
	    Map<String,Function> functions = StaticAnalyser.parseJsonInput(jsonObject.toString());
    }

	/**
	 * This function receives a string containing the jsonObject and creates a map with the
	 * existing functions, each one with the corresponding variables (List<Variable>) and 
	 * instructions (List<Instruction>).
	 * @param jsonObject
	 * @return map of the existing functions
	 */
	private static Map<String,Function> parseJsonInput(String jsonObject) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,Function> functions = new LinkedHashMap<>();
		try {
			functions = objectMapper.readValue(jsonObject, new TypeReference<Map<String,Function>>(){});
		} catch (IOException e) {
			logger.fatal("Error parsing the json.");
	        System.exit(-1);
		} 
		return functions;
	}
}
