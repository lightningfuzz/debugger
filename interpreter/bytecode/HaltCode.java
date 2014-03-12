/* Michael Santer
 * 908250517
 * 
 * HaltCode stops execution of the program.
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class HaltCode extends ByteCode {
    
    public HaltCode(){}
    
    public void init(Vector<String> args){
        //no arguments
    }
    
    public void execute(VirtualMachine vm){
        vm.setIsRunning(false);
    }
    
    public String toString(){
        return ("HALT");
    }
}
