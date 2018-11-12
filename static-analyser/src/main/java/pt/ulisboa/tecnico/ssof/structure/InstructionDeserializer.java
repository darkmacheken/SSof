package pt.ulisboa.tecnico.ssof.structure;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import pt.ulisboa.tecnico.ssof.structure.operations.Add;
import pt.ulisboa.tecnico.ssof.structure.operations.Call;
import pt.ulisboa.tecnico.ssof.structure.operations.Cmp;
import pt.ulisboa.tecnico.ssof.structure.operations.Je;
import pt.ulisboa.tecnico.ssof.structure.operations.Jmp;
import pt.ulisboa.tecnico.ssof.structure.operations.Jne;
import pt.ulisboa.tecnico.ssof.structure.operations.Lea;
import pt.ulisboa.tecnico.ssof.structure.operations.Leave;
import pt.ulisboa.tecnico.ssof.structure.operations.Mov;
import pt.ulisboa.tecnico.ssof.structure.operations.Nop;
import pt.ulisboa.tecnico.ssof.structure.operations.Push;
import pt.ulisboa.tecnico.ssof.structure.operations.Ret;
import pt.ulisboa.tecnico.ssof.structure.operations.Sub;
import pt.ulisboa.tecnico.ssof.structure.operations.Test;

public class InstructionDeserializer extends StdDeserializer<Instruction> {
	
	private static final long serialVersionUID = 2520537245178257647L;

	public InstructionDeserializer() { 
        this(null); 
    } 
 
    public InstructionDeserializer(Class<?> valueClass) { 
        super(valueClass); 
    }
    
	@Override
	public Instruction deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		
		/* get all values from json object (node) */
		String operation = node.get("op").asText();
		int position = (Integer) ((IntNode) node.get("pos")).numberValue();
        String address = node.get("address").asText();
        JsonNode argumentsNode = node.get("args");
        Map<String,String> arguments = new LinkedHashMap<>();

        /*check if the operation has args*/
        if(argumentsNode != null) {
        	arguments = objectMapper.readValue(argumentsNode.toString(), new TypeReference<Map<String,String>>(){});
        }
       
       /* Instantiate class given the class name (operation) */
       switch (operation) {
       	case "push":
        	return new Push(position, address, arguments.get("value"));
       	case "mov":
       		return new Mov(position, address, arguments.get("dest"), arguments.get("value"));
       	case "lea":
       		return new Lea(position, address, arguments.get("dest"), arguments.get("value"));
       	case "call":
       		return new Call(position, address, arguments.get("fnname"), arguments.get("address"));
       	case "nop":
       		return new Nop(position, address);
       	case "add": 
       		return new Add(position, address, arguments.get("dest"), arguments.get("value"));
    	case "sub":
       		return new Sub(position, address, arguments.get("dest"), arguments.get("value"));
       	case "cmp":
       		return new Cmp(position, address, arguments.get("arg0"), arguments.get("arg1"));
       	case "test":
       		return new Test(position, address, arguments.get("arg0"), arguments.get("arg1"));
       	case "je":
       		return new Je(position, address, arguments.get("address"));
       	case "jmp":
       		return new Jmp(position, address, arguments.get("address"));
       	case "jne":
       		return new Jne(position, address, arguments.get("address"));
    	case "leave":
       		return new Leave(position, address);
       	case "ret":
       		return new Ret(position, address);
       	default:
       		return null;
        }
	}

}
