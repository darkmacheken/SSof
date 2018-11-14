package pt.ulisboa.tecnico.ssof.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;
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
}
