package pt.ulisboa.tecnico.ssof.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import pt.ulisboa.tecnico.ssof.structure.Vulnerability;
import pt.ulisboa.tecnico.ssof.structure.operations.Function;

public final class JsonUtils {
    private static final Logger logger = Logger.getLogger(JsonUtils.class);

    private JsonUtils(){}
    
    /**
     * This function receives a string containing the jsonObject and creates a map with the
     * existing functions, each one with the corresponding variables (List<Variable>) and
     * instructions (List<Instruction>).
     * @param jsonObject
     * @return map of the existing functions
     */
    public static Map<String,Function> parseJsonInput(String jsonObject) {
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
    
    /**
     * This function receives a string containing the desired fileName and the list of vulnerabilities
     * (List<Vulnerability>) and produces a .json file with the found vulnerabilities.
     * @param fileName
     * @param vulnerabilities
     */
    public static void parseJsonOutput(String fileName, List<Vulnerability> vulnerabilities) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        try {
            String jsonObject = objectMapper.writer(prettyPrinter).writeValueAsString(vulnerabilities);
            StringBuilder file = new StringBuilder();
            file.append(fileName).append(".output.json");
            Path path = Paths.get(file.toString()).toAbsolutePath();
            Files.write(path, jsonObject.getBytes());
        } catch (IOException e) {
            logger.fatal("Error parsing the json output.");
            System.exit(-1);
        }
    }
}
