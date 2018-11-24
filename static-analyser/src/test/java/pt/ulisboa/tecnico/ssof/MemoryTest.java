package pt.ulisboa.tecnico.ssof;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pt.ulisboa.tecnico.ssof.memory.StackMemory;
import pt.ulisboa.tecnico.ssof.structure.operations.Function;

public class MemoryTest {
    private StackMemory memory;

    @Before
    public void setUp(){
        memory = new StackMemory();
        memory.startStackFrame(new Function(1,null,null));
        memory.pushRbp();
        memory.allocate(8);
    }

    @Test
    public void writeAndRead(){
        memory.writeValue(-8,8,25L);
        Long obtained = memory.readValue(-8,8);
        Assert.assertEquals(25L, (long)obtained);

    }
}
