/* Michael Santer
 * 908250517
 * 
 * CallCode has one argument that is a function label,
 * it transfers control to the indicated function
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class CallCode extends ByteCode {
    
    private String label;
    private int address;
    private String functionName;
    private int numOfArgs;
    private Vector<Integer> args;
    
    public CallCode(){}
    
    public void init(Vector<String> args){
        label = args.get(0);
        functionName = getFuncName(); // used for printing
    }
    
    public void setAddress(int a){
        address = a;
    }
    
    public String getLabel(){
        return label;
    }
    
    /**
     * 1. Gets data needed for dumping
     * 2. Save the return adress
     * 3. Passes control to function by changing the pc
     * @param vm 
     */
    public void execute(VirtualMachine vm){
        numOfArgs = vm.getNumOfArgs(); //used for printing
        args = vm.getArgsRunStack(numOfArgs); //save args for printing
        
        vm.saveReturnAddrs();
        vm.setPc(address);
    }
    
    public String toString(){
        String str = ("CALL " + label + "\t " + functionName + "(");
        if(numOfArgs > 0){
            str += args.get(0);
            for(int i=1; i<args.size(); i++){
                str += "," + args.get(i);
            }
        }
        return str + ")";
        
    }
    
    /**
     * Uses a stringTokenizer to get the base id of the function 
     * without the "<<n>>" to use for printing. 
     * @return String functionName
     */
    private String getFuncName(){
        StringTokenizer st = new StringTokenizer(label);
        return st.nextToken("<");
    }
}
