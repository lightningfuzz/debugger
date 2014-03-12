/* Michael Santer
 * 908250517
 * 
 * WriteCode writes the value from the top of the runtime stack without
 * removing it. 
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class WriteCode extends ByteCode {
    
    public WriteCode(){}
    
    public void init(Vector<String> args){
        //no arguments
    }
    
    public void execute(VirtualMachine vm){
        System.out.println(vm.peekRunStack());
    }
    
    public String toString(){
        return ("WRITE");
    }
}
