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
 
    public InstructionDeserializer(Class<?> vc) { 
        super(vc); 
    }
    
	@Override
	public Instruction deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = jp.getCodec().readTree(jp);
		
		/* get all values from json object (node) */
		String operation = node.get("op").asText();
		int pos = (Integer) ((IntNode) node.get("pos")).numberValue();
        String address = node.get("address").asText();
        JsonNode argsNode = node.get("args");
        Map<String,String> args = new LinkedHashMap<String,String>();

        /*check if the operation has args*/
        if(argsNode != null) {
        	args = objectMapper.readValue(argsNode.toString(), new TypeReference<Map<String,String>>(){});
        }
       
       /* Instantiate class given the class name (operation) */
       switch (operation) {
       	case "push":
        	return new Push(pos, address, args.get("value"));
       	case "mov":
       		return new Mov(pos, address, args.get("dest"), args.get("value"));
       	case "lea":
       		return new Lea(pos, address, args.get("dest"), args.get("value"));
       	case "call":
       		return new Call(pos, address, args.get("fname"), args.get("address"));
       	case "nop":
       		return new Nop(pos, address);
       	case "add": 
       		return new Add(pos, address, args.get("dest"), args.get("value"));
    	case "sub":
       		return new Sub(pos, address, args.get("dest"), args.get("value"));
       	case "cmp":
       		return new Cmp(pos, address, args.get("arg0"), args.get("arg1"));
       	case "test":
       		return new Test(pos, address, args.get("arg0"), args.get("arg1"));
       	case "je":
       		return new Je(pos, address, args.get("address"));
       	case "jmp":
       		return new Jmp(pos, address, args.get("address"));
       	case "jne":
       		return new Jne(pos, address, args.get("address"));
    	case "leave":
       		return new Leave(pos, address);
       	case "ret":
       		return new Ret(pos, address);
        }
     
        return null;
	}

}
