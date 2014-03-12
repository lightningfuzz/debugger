/* Michael Santer
 * 908250517
 * 
 * LitCode can have 1 or 2 arguments.
 * If it has one argument (LIT n), it is pushing a literal value to the top
 * of the runtime stack.
 * If it has two arguments (LIT 0 i), it is initializing a variable. The first
 * argument is pushed to the runtime stack, and the second argument is the
 * id of the variable, which we save. 
 * 
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class LitCode extends ByteCode {
    
    private int value;
    protected String id;
    
    public LitCode(){}
    
    public void init(Vector<String> args){
        value = Integer.parseInt(args.get(0));
        if(args.size() == 2)
            id = args.get(1);
    }
    
    public void execute(VirtualMachine vm){
        vm.pushRunStack(value);
    }
    
    public String toString(){
        if (id != null)
            return ("LIT " + value + " "+id + "\t int "+ id);
        else
            return ("LIT " + value );
    }
}
