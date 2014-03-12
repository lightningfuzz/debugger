/* Michael Santer
 * 908250517
 * 
 * ReturnCode returns from the current function. 
 * This is done by popping the top value from the returnAddrs stack
 * and saving it into the pc.
 * Could have one argument that is used to comment which function we are
 * returning from.
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class ReturnCode extends ByteCode {
    
    private String label;
    private String functionName;
    protected int returnValue;
    
    public ReturnCode(){}
    
    public void init(Vector<String> args){
        if(args.size()>0){
            label = args.get(0);
            functionName = getFuncName();
        }
        
    }
    
    public void execute(VirtualMachine vm){
        int address;
        address = vm.popReturnAddrs();
        vm.setPc(address);
        vm.popFrameRunStack();
        returnValue = vm.peekRunStack(); //used for printing
    }
    
    public String toString(){
        if(label == null)
            return ("RETURN");
        else
            return ("RETURN " + label + "\t exit "+ functionName + ": " + returnValue);
    }
    
    /**
     * Uses a stringTokenizer to get the base id of the function 
     * without the "<<n>>" to use for printing. 
     * @return String
     */
    private String getFuncName(){
        StringTokenizer st = new StringTokenizer(label);
        return st.nextToken("<");
    }
}
