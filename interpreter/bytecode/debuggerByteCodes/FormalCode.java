
package interpreter.bytecode.debuggerByteCodes;
import interpreter.bytecode.*;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.*;

/** Michael Santer
 *  908250517
 * 
 * FormalCode adds a variable id and offset to the current environmentRecord.
 * 
 * 
 */
public class FormalCode extends ByteCode {
    private String id;
    private int offset;
    
    public FormalCode(){}
    
    public void init(Vector<String> args){
        id = args.get(0);
        offset = Integer.parseInt(args.get(1));
    }
    
    public void execute(VirtualMachine vm){
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.addEnvironmentVariable(id, offset);
    }
    
    public String toString(){
        return "FORMAL";
    }
}
