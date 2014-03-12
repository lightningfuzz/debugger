/* Michael Santer
 * 908250517
 * 
 * DumpCode receives one arguement, either "ON" or "OFF"
 * and turns runtime dumping on or off respectively
 * 
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class DumpCode extends ByteCode {
    
    private boolean state;
    
    public DumpCode(){}
    
    public void init(Vector<String> args){
        if(args.get(0).equals("ON"))
            state = true;
        else if(args.get(0).equals("OFF"))
            state = false;
    }
    
    public void execute(VirtualMachine vm){
        vm.setDump(state);
    }
    
    public String toString(){
        return ("DUMP " + state);
    }
}
