/* Michael Santer
 * 908250517
 * 
 * FalseBranchCode receives one argument that is a function label.
 * It then pops the runtime stack, and if the value is false (0) it
 * branches to that label (i.e. sets the pc to the address of the function)
 * 
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.*;

public class FalseBranchCode extends ByteCode {
    
    private String label;
    private int address;
    
    public FalseBranchCode(){}
    
    public void init(Vector<String> args){
        label = args.get(0);
    }
    
    public String getLabel(){
        return label;
    }
    
    public void setAddress(int a){
        address = a;
    }
    
    public void execute(VirtualMachine vm){
        if(vm.popRunStack()==0)
            vm.setPc(address);
    }
    
    public String toString(){
        return ("FALSEBRANCH " + label);
    }
}
