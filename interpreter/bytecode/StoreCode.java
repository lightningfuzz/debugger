/* Michael Santer
 * 908250517
 * 
 * StoreCode has two arguments, an integer for the offset, and a variable id
 * that indicates what variable is being stored.
 * It then calls the store method which pops the top of the runtime stack, then 
 * stores the value into the "offset" from the start of the frame. 
 * 
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class StoreCode extends ByteCode {
    
    private int offset;
    private String id;
    private int storedValue;
    
    public StoreCode(){}
    
    public void init(Vector<String> args){
        offset = Integer.parseInt(args.get(0));
        id = args.get(1);
    }
    
    public void execute(VirtualMachine vm){
        //save the value to be stored(top of stack) for printing later. 
        storedValue = vm.peekRunStack();
        
        vm.storeRunStack(offset);
    }
    
    public String toString(){
        return ("STORE " + offset + " " + id + "\t " + id + "=" + storedValue);
    }
}
