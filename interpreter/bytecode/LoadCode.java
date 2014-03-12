/* Michael Santer
 * 908250517
 * 
 * LoadCode pushes the value which is "offset" from the start of the frame to
 * the top of the runtime stack. The second argument is an id for the variable
 * that is being loaded. It is simply used as a comment. 
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class LoadCode extends ByteCode {
    
    private int offset;
    private String id;
    
    public LoadCode(){}
    
    public void init(Vector<String> args){
        offset = Integer.parseInt(args.get(0));
        id = args.get(1);
    }
    
    public void execute(VirtualMachine vm){
        vm.loadRunStack(offset);
    }
    
    public String toString(){
        return ("LOAD " + offset + " " + id + "\t <load " + id + ">");
    }
}
