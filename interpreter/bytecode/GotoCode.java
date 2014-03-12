/* Michael Santer
 * 908250517
 * 
 * GotoCode receives one argument which is a label.
 * It then braches to that label. (ie changes the pc)
 * 
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.*;

public class GotoCode extends ByteCode {
    
    private String label;
    private int address;
    
    public GotoCode(){}
    
    public void init(Vector<String> args){
        label = args.get(0);
    }
    
    public void setAddress(int a){
        address = a;
    }
    
    public String getLabel(){
        return label;
    }
    
    public void execute(VirtualMachine vm){
        vm.setPc(address);
    }
    
    public String toString(){
        return ("GOTO " + label);
    }
}

