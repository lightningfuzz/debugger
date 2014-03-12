/* Michael Santer
 * 908250517
 * 
 * LabelCode receives one argument, which is a label. 
 * This label is used for branch codes.
 * 
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.*;

public class LabelCode extends ByteCode {
    
    private String label;
    private int address;
    
    public LabelCode(){}
    
    public void init(Vector<String> args){
        label = args.get(0);
    }
    
    public void setAddress(int a){
        address = a;
    }
    
    public int getAddress(){
        return address;
    }
    
    public String getLabel(){
        return label;
    }
    
    //LabelCode does not make any changes to the VirtualMachine
    public void execute(VirtualMachine vm){}
    
    public String toString(){
        return ("LABEL" + label);
    }
}

