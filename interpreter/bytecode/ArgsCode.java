/* Michael Santer
 * 908250517
 * 
 * ArgsCode receives the number of arguments to pass to a function
 * and creates a new runtime stack frame. 
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class ArgsCode extends ByteCode {
    
    private int numOfArgs;
    
    public ArgsCode(){}
    
    public void init(Vector<String> args){
        numOfArgs = Integer.parseInt(args.get(0));
    }
    
    public void execute(VirtualMachine vm){
        vm.setNumOfArgs(numOfArgs); //to be used by CallCode's toString method
        
        vm.newRunStackFrameAt(numOfArgs);
    }
    
    public String toString(){
        return ("ARGS " + numOfArgs);
    }
}
