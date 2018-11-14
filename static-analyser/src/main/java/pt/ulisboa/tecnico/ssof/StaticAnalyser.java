package pt.ulisboa.tecnico.ssof;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.ssof.structure.graph.Graph;
import pt.ulisboa.tecnico.ssof.structure.operations.Program;
import pt.ulisboa.tecnico.ssof.utils.JsonUtils;


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
	    Program program = new Program(JsonUtils.parseJsonInput(jsonObject.toString()));
	    new Graph().generateGraph(program);
    }

}
