/* Michael Santer
 * 908250517
 * 
 * PopCode pops a given number of levels from the runtime stack.
 * It has one argument. 
 * 
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.*;

public class PopCode extends ByteCode {
    
    protected int numberOfPops;
    
    public PopCode(){}
    
    public void init(Vector<String> args){
        numberOfPops = Integer.parseInt(args.get(0));
    }
    
    public void execute(VirtualMachine vm){
        for(int i=0; i<numberOfPops; i++){
            vm.popRunStack();
        }    
    }
    
    public String toString(){
        return ("POP " + numberOfPops);
    }
}
